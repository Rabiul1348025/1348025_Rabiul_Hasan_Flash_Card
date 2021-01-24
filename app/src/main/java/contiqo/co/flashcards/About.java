package contiqo.co.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class About extends AppCompatActivity {

    @Override
    /*lists what the app is abou when selected from the menu options*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_about );
        Toast toast = Toast.makeText(getApplicationContext(), " welcome to the about page", Toast.LENGTH_LONG );
        toast.show();
    }
}
