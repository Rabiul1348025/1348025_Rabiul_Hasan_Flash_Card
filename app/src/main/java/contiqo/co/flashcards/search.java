package contiqo.co.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class search extends AppCompatActivity {
// the method searches for flashcards when implemented
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search );
        Toast toast = Toast.makeText(getApplicationContext(), " welcome to the help page", Toast.LENGTH_LONG );
        toast.show();
    }
}
