package contiqo.co.flashcards;

import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;
/* this is the splash activity that loads first when the users run the app*/
public class Splash extends AwesomeSplash {

   /*method initializes the loading animations that display the welcome message to the user*/
    @Override
    public void initSplash(ConfigSplash configSplash) {
        ActionBar actionBar= getSupportActionBar();
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );

        configSplash.setBackgroundColor( R.color.colorPrimary );
        configSplash.setAnimCircularRevealDuration( 3000 );
        configSplash.setRevealFlagX( Flags.REVEAL_LEFT );
        configSplash.setRevealFlagX( Flags.REVEAL_BOTTOM );

        configSplash.setTitleSplash( "Welcome to my Flashcards App" );
        configSplash.setTitleTextColor( R.color.white );
        configSplash.setTitleTextSize( 25f );
        configSplash.setAnimTitleDuration( 3000 );
        configSplash.setAnimTitleTechnique( Techniques.FlipInX );


    }
/* when the animation ends the main activity is called by this method*/
    @Override
    public void animationsFinished() {
        startActivity( new Intent( Splash.this, MainActivity.class ) );
    }
}
