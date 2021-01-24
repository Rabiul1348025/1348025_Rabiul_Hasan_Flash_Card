package contiqo.co.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class edit extends AppCompatActivity {

    @Override
    /* helps a user to edit flashcards*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_edit );

        Toast toast = Toast.makeText(getApplicationContext(), " EDIT CARDS HERE", Toast.LENGTH_LONG );
        toast.show();
    }
}
