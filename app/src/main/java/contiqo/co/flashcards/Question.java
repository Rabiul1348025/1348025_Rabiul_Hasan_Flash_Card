package contiqo.co.flashcards;

public class Question {
    /*
    * initialization of the variables needed
    * */
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private int answer;

    public Question(){}

    public Question(String question, String option1, String option2, String option3, int answerNR) {
        /*
        * this class helps to get select the questions and get the answers that the user has selected
        * it helps to determine if the answer is correct
        * it gets the correct answer
        * */
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answer = answerNR;
    }

    public String getQuestion() {
        /*it gets the question*/
        return question;
    }

    public void setQuestion(String question) {
        /* it sets the question*/
        this.question = question;
    }

    public String getOption1() {
        /*it gets the first option for the answer*/
        return option1;
    }

    public void setOption1(String option1) {
        /* it sets option 1 if selected*/
        this.option1 = option1;
    }

    public String getOption2() {
        /*it gets option 2 for the answer*/
        return option2;
    }

    public void setOption2(String option2) {
        /* it sets option 2 if selected*/
        this.option2 = option2;
    }

    public String getOption3() {
        /*it gets option 3 for the answer*/
        return option3;
    }

    public void setOption3(String option3) {
        /* it sets option 3 if selected*/
        this.option3 = option3;
    }

    public int getAnswerNR() {
        /*it gets the correct answer*/
        return answer;
    }

    public void setAnswerNR(int answerNR) {
        /* it sets the correct answer to be displayed after the timer has stopped*/
        this.answer = answerNR;
    }
}
