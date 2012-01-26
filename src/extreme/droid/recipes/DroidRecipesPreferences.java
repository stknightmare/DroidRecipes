package extreme.droid.recipes;

import android.app.Activity;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.MenuInflater;
//import android.widget.Button;
import android.preference.PreferenceActivity;


public class DroidRecipesPreferences extends PreferenceActivity
{
    @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.layout.preference);

        }
}
