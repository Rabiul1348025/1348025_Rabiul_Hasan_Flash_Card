package contiqo.co.flashcards;

import android.provider.BaseColumns;

public final class FlashCardContract {

    private FlashCardContract(){

    }
/*helps to set the parameters for for the flashcard table and helps in editing or altering the table from one point*/
    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME="flash_card_questions";
        public static  final String COLUMN_QUESTION="questions";
        public static  final String COLUMN_OPTION1="option1";
        public static  final String COLUMN_OPTION2="option2";
        public static  final String COLUMN_OPTION3="option3";
        public static  final String COLUMN_ANSWER="answer";
    }
}
