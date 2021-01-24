package contiqo.co.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Help extends AppCompatActivity {

    @Override
    /*gives help options to the user when selected
    * not yet implemented*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_help );

        Toast toast = Toast.makeText(getApplicationContext(), " welcome to the help page", Toast.LENGTH_LONG );
        toast.show();

    }
}
