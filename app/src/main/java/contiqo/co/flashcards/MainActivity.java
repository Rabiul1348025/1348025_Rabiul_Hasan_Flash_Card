package contiqo.co.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFFRERENCES = "sharedPrefs";
    public static final String HIGHSCORE = "keyHighscore";
    private TextView textViewHighscore;
    private int highscore;
    private ImageButton mImageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* buttons are activated using their ids in this section to add functionality to them*/
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        textViewHighscore = findViewById(R.id.text_view_highscore);
        loadHighscore();
        /*the start quiz button loads the quiz activity*/
        Button startQuiz= findViewById( R.id.button_start_flashcard );
        startQuiz.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz();
            }
        } );
        // loads the add card activity
        Button add=findViewById( R.id.button_add_card );

        add.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCard();
            }
        } );
    }
    /* statts the quiz when selected*/
    private void startQuiz(){

        Intent intent= new Intent( MainActivity.this, FlashCard.class);
        startActivityForResult( intent, REQUEST_CODE_QUIZ );
    }
    /*the method loads the menu and displays its items*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_flashcard:
                addCard();
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
        Intent intent= new Intent( MainActivity.this,Help.class);
        startActivityForResult( intent, REQUEST_CODE_QUIZ );
    }
    private void search(){
        Intent intent= new Intent( MainActivity.this,search.class);
        startActivityForResult( intent, REQUEST_CODE_QUIZ );
    }
    private void settings(){
        Intent intent= new Intent( MainActivity.this,Settings.class);
        startActivityForResult( intent, REQUEST_CODE_QUIZ );
    }
    private void about(){
        Intent intent= new Intent( MainActivity.this,About.class);
        startActivityForResult( intent, REQUEST_CODE_QUIZ );
    }
    private void addCard(){
        Intent intent= new Intent( MainActivity.this,AddCard.class);
        startActivityForResult( intent, REQUEST_CODE_QUIZ );
    }
    private void removeCard(){
        Intent intent= new Intent( MainActivity.this,Remove.class);
        startActivityForResult( intent, REQUEST_CODE_QUIZ );
    }
    private void edit(){
        Intent intent= new Intent( MainActivity.this,edit.class);
        startActivityForResult( intent, REQUEST_CODE_QUIZ );
    }
    private void archive(){
        Intent intent= new Intent( MainActivity.this,Archive.class);
        startActivityForResult( intent, REQUEST_CODE_QUIZ );
    }

    /* the method updates the highscore at the end of the quiz and displays the score on the main activity page*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra( FlashCard.EXTRA_SCORE, 0);
                if (score > highscore) {
                    updateHighscore(score);
                }
            }
        }
    }
    /* the method sets the highscore which is displayed*/
    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences( SHARED_PREFFRERENCES, MODE_PRIVATE);
        highscore = prefs.getInt( HIGHSCORE, 0);
        textViewHighscore.setText("Highscore: " + highscore);
    }
    //updates the high score
    private void updateHighscore(int highscoreNew) {
        highscore = highscoreNew;
        textViewHighscore.setText("Highscore: " + highscore);
        SharedPreferences prefs = getSharedPreferences( SHARED_PREFFRERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt( HIGHSCORE, highscore);
        editor.apply();
    }
    //the method sets the menu on the activity page
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;


    }
    //method shows the popup menu on the flashcards
   public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        popup.setOnMenuItemClickListener(this);
        inflater.inflate(R.menu.popup_menu, popup.getMenu());
        popup.show();



    }

//the methods sets the functionality for the selected menu items
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.add_flashcard:
                addCard();
                return true;

            case R.id.archive_flashcard:
                archive();
                return true;
            case R.id.remove:
                removeCard();
                return true;
            case R.id.edit:
                edit();
                return true;
            case R.id.search_bar:
                search();
                return true;
            default:

                return super.onOptionsItemSelected(menuItem);

        }

    }
}
