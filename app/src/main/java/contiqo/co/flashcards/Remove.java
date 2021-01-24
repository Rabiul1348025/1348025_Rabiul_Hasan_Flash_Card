package contiqo.co.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Remove extends AppCompatActivity {
// the methods removes a flashcard when implemented
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_remove );

        Toast toast = Toast.makeText(getApplicationContext(), " REMOVE CARDS", Toast.LENGTH_LONG );
        toast.show();
    }
}
