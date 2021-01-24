package contiqo.co.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class AddCard extends AppCompatActivity {
    private Button add;
    @Override
    /* used to add new cards*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_card );
        Toast toast = Toast.makeText(getApplicationContext(), " ADD CARDS HERE", Toast.LENGTH_LONG );
        toast.show();

    }

    private void addCard(){

    }
}
