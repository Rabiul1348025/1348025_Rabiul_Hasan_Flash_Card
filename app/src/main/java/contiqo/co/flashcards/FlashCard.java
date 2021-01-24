package contiqo.co.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class FlashCard extends AppCompatActivity {
/*
* the variables are initialized here
* */
    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLISECONDS = 30000;
    private static final int REQUEST_CODE =1;

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton radio1;
    private RadioButton radio2;
    private RadioButton radio3;
    private Button buttonForNext;

    private ColorStateList textColorDefaultRadioButton;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer Timer;
    private long timeLeftInMillis;

    private List<Question>questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;
    private long backPressedTime;
    private Button shuffle;
    private Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_quiz );
        /*
        * the code in this section is used to get contents from the xml file by using the id reference so that the java
        * file can use them in the program
        * it gets the textviews for the questions and radio buttons for the options
        * */
        textViewQuestion= findViewById( R.id.text_view_question );
        textViewScore= findViewById( R.id.text_view_score );
        textViewQuestionCount=findViewById( R.id.text_view_question_count );
        textViewCountDown= findViewById( R.id.text_view_countdown );
        rbGroup= findViewById( R.id.radio_group );
        radio1= findViewById( R.id.radio_button1 );
        radio2= findViewById( R.id.radio_button2 );
        radio3= findViewById( R.id.radio_button3 );
        buttonForNext =findViewById( R.id.button_confirm_next );
        shuffle=findViewById( R.id.button_shuffle );
        reset= findViewById( R.id.button_reset );


        textColorDefaultRadioButton = radio1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();

        /* the code below helps is getting the flashcard questions*/
        FlashCardDBHelper dbHelper= new FlashCardDBHelper( this );
        questionList= dbHelper.getAllQuestions();
        questionCountTotal= questionList.size();

        /* the shuffle button shuffles the questions in no particular order*/
        shuffle.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.shuffle( questionList );
                showNextQuestion();
            }
        } );
        Collections.shuffle( questionList );
        /* the method shows the next question after shuffling*/
        showNextQuestion();

        /*
        * the reset button helps to clear the radio button group of any selections
        * */
        reset.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbGroup.clearCheck();
            }
        } );
        //checks if there is no option selected
        buttonForNext.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!answered){
                    if (radio1.isChecked() || radio2.isChecked() || radio3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText( FlashCard.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();
                }
            }
        } );


    }
    //loads the next question
    private void showNextQuestion(){
        radio1.setTextColor( textColorDefaultRadioButton );
        radio2.setTextColor( textColorDefaultRadioButton );
        radio3.setTextColor( textColorDefaultRadioButton );
        rbGroup.clearCheck();
        /*
        * the question counter checks the number of questions that have been answered and compares them them
        * to the total number of questions that are available to determine how many are remaining
        * */
        if(questionCounter< questionCountTotal){
            currentQuestion=questionList.get( questionCounter );
            textViewQuestion.setText( currentQuestion.getQuestion() );
            radio1.setText( currentQuestion.getOption1() );
            radio2.setText( currentQuestion.getOption2() );
            radio3.setText( currentQuestion.getOption3() );
            questionCounter++;
            /*the code below displays the total number of questions and those that have been answered*/
            textViewQuestionCount.setText( "Question: "+ questionCounter+ "/" + questionCountTotal );
            answered= false;

            timeLeftInMillis = COUNTDOWN_IN_MILLISECONDS;
            CountDown();
        }else{
            /*
            * if there number of questions answered equals the total number of questions then the user ends the quiz
            * */
            endQuiz();
        }

    }
    //the timer function which starts the countdown when a new question is displayed
    private void CountDown() {
        Timer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }
    //updates the countdown and changes color when 10 seconds and below are remaining
    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format( Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textViewCountDown.setText(timeFormatted);
        if (timeLeftInMillis < 10000) {
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }
    //checks the selected answer if it is correct
    private void checkAnswer(){
        answered=true;

        Timer.cancel();

        RadioButton rbSelected= findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;
        if (answerNr == currentQuestion.getAnswerNR()) {
            score++;
            textViewScore.setText("Score: " + score);
        }
        showSolution();
    }
    /* when the timer stops the method below displays the correct
     answer in green color while the wrongs questions are displayed in red color
     */
    private void showSolution() {
        radio1.setTextColor( Color.RED);
        radio2.setTextColor(Color.RED);
        radio3.setTextColor(Color.RED);
        switch (currentQuestion.getAnswerNR()) {
            case 1:
                radio1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                radio2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                radio3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is correct");
                break;
        }
        /* checks if all the questions have been answered
        * if not the 'next' button is show to the user
        * if all questions have been answered the 'finish' button is displayed to the user*/
        if (questionCounter < questionCountTotal) {
            buttonForNext.setText("Next");
        } else {
            buttonForNext.setText("Finish");
        }
    }
    /*
    * this methods ends the quiz
    * */
    private  void endQuiz(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
    @Override

    /* this method is used to go back to the main app page and to exit the app */
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            endQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Timer != null) {
            Timer.cancel();
        }
    }
    @Override
    /* this methods adds a menu to the flashcard activity*/
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;


    }
    @Override
    /* the method calls the function of the selected menu items*/
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.settings:
                settings();
                return true;
            case R.id.help:
                help();
                return true;
            case R.id.about:
                about();
                return true;
            case R.id.search_bar:
                search();
                return true;
            default:

                return super.onOptionsItemSelected(item);

        }
    }
    /* the methods below are used for the menu items
    * they help to load the functions of the menu items*/
    private void help(){
        Intent intent= new Intent( FlashCard.this,Help.class);
        startActivityForResult( intent, REQUEST_CODE );
    }
    private void search(){
        Intent intent= new Intent( FlashCard.this,search.class);
        startActivityForResult( intent, REQUEST_CODE );
    }
    private void settings(){
        Intent intent= new Intent( FlashCard.this,Settings.class);
        startActivityForResult( intent, REQUEST_CODE );
    }
    private void about(){
        Intent intent= new Intent( FlashCard.this,About.class);
        startActivityForResult( intent, REQUEST_CODE );
    }
}
