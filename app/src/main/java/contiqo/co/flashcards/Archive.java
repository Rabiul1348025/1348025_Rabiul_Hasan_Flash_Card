package contiqo.co.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Archive extends AppCompatActivity {

    @Override
    /*archives cards when selected*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_archive );

        Toast toast = Toast.makeText(getApplicationContext(), " ARCHIVE CARDS HERE", Toast.LENGTH_LONG );
        toast.show();
    }
}
