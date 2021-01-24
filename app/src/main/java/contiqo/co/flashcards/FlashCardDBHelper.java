/*
* this is the the database class where the a dababase is created ddata is inserted, queried, updated and deleted
* */
package contiqo.co.flashcards;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import contiqo.co.flashcards.FlashCardContract.*;
/*
* The above are the imported libraries that are needed for the appp to work well*/
public class FlashCardDBHelper extends SQLiteOpenHelper {
    /* initialization of variables*/
    private static final String DATABASE_NAME = "flashcards.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    public FlashCardDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        /* creates the database when the application is installed
        * */
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /* this method drops the table on every instance of the application installation if it already exists*/
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }
    private void fillQuestionsTable() {
        /*
        * this methods lists all the questions that are to be added to the flashcard database table and prepares them to
        * be added
        * */
        Question q1= new Question( " What is your age?","22", "30","26",1 );
        addQuestion( q1 );

        Question q2= new Question( " What is your name?","R Hasan", "John Doe","Jim Rose",1 );
        addQuestion( q2 );
        Question q3= new Question( " What is your email address?","Johndoe@gmail.com", "abdulahkey@gmail.com","mirroes123@mail.com",2 );
        addQuestion( q3 );
        Question q4= new Question( " What is your locality?","New York", "London","Paris",3 );
        addQuestion( q4 );
        Question q5= new Question( " Do you go to college?","No", "Yes","Maybe",2 );
        addQuestion( q5 );
        Question q6= new Question( " What is your favourite color?","Green", "Red","Blue",2 );
        addQuestion( q6 );
        Question q7= new Question( " What is your phone number?","0718071593", "0747071593","0776826114",1 );
        addQuestion( q7 );
        Question q8= new Question( " What is your major?","Computer Science", "Psychology","Education",1 );
        addQuestion( q8 );
        Question q9= new Question( " Among water, juice and energy drinks, which one are you likely to take on a hot day?","Water", "Juice","Energy Drink",2 );
        addQuestion( q9 );
        Question q10= new Question( " Which is your favorite programming language?","Java", "PHP","Python",3 );
        addQuestion( q10 );
        Question q11= new Question( " What level are you in Python programing?","Novice", "Intermediary","Advanced",1 );
        addQuestion( q11 );
        Question q12= new Question( " Are you planning to continue learning the other languages?","Yes", "No","Maybe",1 );
        addQuestion( q12 );

    }
    private void addQuestion(Question question) {
        /*
         * this methods adds the flashcard questions to the database table
         * */
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER, question.getAnswerNR());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }
    public List<Question> getAllQuestions() {
        /*
        * this methods queries the database to lst all the questions
        * */
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNR(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}