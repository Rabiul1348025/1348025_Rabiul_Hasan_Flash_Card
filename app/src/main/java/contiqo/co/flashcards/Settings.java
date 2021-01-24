package contiqo.co.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
// the method allows users to change app settings when implemented
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_settings );

        Toast toast = Toast.makeText(getApplicationContext(), " welcome to the settings page", Toast.LENGTH_LONG );
        toast.show();
    }
}
