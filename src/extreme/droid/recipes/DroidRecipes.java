package extreme.droid.recipes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import android.webkit.WebView;
//import android.webkit.WebViewClient;

//Menu
import android.view.Menu;
import	android.view.MenuItem;
//MenuInflater
import	android.view.MenuInflater;

//Toast
import android.widget.Toast;

//Viewjoh
import 	android.view.View;
//EditText
//import android.widget.EditText;

//OnKeyListener
//import android.view.View.OnKeyListener;
//import android.view.KeyEvent;
import 	android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import  android.view.View.OnClickListener;
import 	android.widget.Button;
import  java.lang.String;
import	android.widget.SimpleCursorAdapter;
import 	android.content.Intent;
//import  android.app.ListActivity;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
////////////////////////////////////////////
import android.widget.AutoCompleteTextView;
////////////////preferences
import android.preference.PreferenceManager;
import android.content.SharedPreferences;

public class DroidRecipes extends Activity
{

//////////////////////////
  	//protected EditText searchText;

        protected SQLiteDatabase db;
        protected Cursor cur;
        protected ListAdapter adapter;
        protected ListView employeeList;
	protected WebView webview;
	protected ArrayAdapter<String> adapter2;
	protected AutoCompleteTextView recipesList;
	public String[] RECIPESCONTENT;
///////////////////////////////////////////////////

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	
	  db = (new DataBaseHelper(this)).getWritableDatabase();
	///////////////////////////////////////////////////////////
	SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
	//String            enabled = sharedPrefs.getString("EnablePreferences", null);
	String            enabled2 = sharedPrefs.getString("Name", null);
	String            enabled3 = sharedPrefs.getString("DayOfWeek", null);
	//toastTxt(enabled+" "+enabled2+" "+enabled3);
	toastTxt(enabled2+" "+enabled3);
	       setContentView(R.layout.main);

	   employeeList = (ListView) findViewById (R.id.list);

	 webview = (WebView) findViewById(R.id.MyWebview);

	loadHtmlData(getApplicationContext().getString(R.string.mystring));

/////////////////////////////////
	String[] RECIPES = new String[] {"toursi","piklakia"};
	
	 recipesList = (AutoCompleteTextView) findViewById(R.id.list_autocomplete);
	    adapter2 = new ArrayAdapter<String>(this, R.layout.list_item, RECIPES);
	    recipesList.setAdapter(adapter2);

/////////////////////////////////////////////////////////////////////////////

		employeeList.setOnItemClickListener(
			new OnItemClickListener()
			{

			    @Override
			    public void onItemClick(AdapterView<?> arg0, View view,
				    int position, long id) {

				Cursor cur = (Cursor) adapter.getItem(position);

				//String selected_id = cur.getString(cur.getColumnIndex("_id"));
				String txt = cur.getString(cur.getColumnIndex("title"));
				String recipe = cur.getString(cur.getColumnIndex("recipe"));
				//loadHtmlData(" <b>"+txt+"</b><br />"+recipe);
				loadRecipeData(txt,recipe);
			    }   
			}       
		);
///////////////////////////////////////////////////////////
		recipesList.setOnItemClickListener(
			new OnItemClickListener()
			{

			    @Override
			    public void onItemClick(AdapterView<?> arg0, View view,
				    int position, long id) {

				loadHtmlData(RECIPESCONTENT[position]);
				
			    }   
			}       
		);

//////////////////////////////////////////////////////////////////////

/*
		searchTextBtn.setOnClickListener(new OnClickListener() { public void onClick (View v){ 
			String txt = edittext.getText().toString();
		}});
*/

////////////////////////////////////////////////////////////////////////////////////////
    }
	public void toastTxt(String txt) {
		Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
	}
	
	public void loadRecipeData(String title,String recipe) {
		
		loadHtmlData(" <b>"+title+"</b><br />"+recipe);
	}

	public void loadHtmlData(String info) {

		String summary = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /></head><body>";
		summary+=info;
		summary+="</body></html>";
		webview.loadData(summary, "text/html", "utf-8");

	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.icon:   
		toastTxt("You pressed the icon!");  
                break;
            case R.id.text:     
		toastTxt("You pressed the text!");  
                break;
            case R.id.icontext: 
		toastTxt("You pressed the icon and text!");  
		break;
	    case R.id.prefs: 
		showPreferences();  
		break;
        }
        return true;

    }

	public void updateRecipesList(String txt) {

			cur = db.rawQuery("SELECT _id, title,recipe FROM recipes WHERE title LIKE ?", 
                                               new String[]{"%" + txt + "%"});

			  adapter = new SimpleCursorAdapter(
                                DroidRecipes.this, 
                                R.layout.recipe_list_item, 
                                cur, 
                                new String[] {"title","recipe"}, 
                                new int[] {R.id.title,R.id.recipe});
			
               		 employeeList.setAdapter(adapter);

			Integer counts = cur.getCount();
			 if (counts > 0) {

				Integer x = 0;
				String[] RECIPES = new String[counts];
				RECIPESCONTENT = new String[counts];

			    while (cur.moveToNext()) {
						
						
						RECIPES[x] = cur.getString(cur.getColumnIndex("title"));
						RECIPESCONTENT[x] = cur.getString(cur.getColumnIndex("recipe"));
				
						x++;
		 	        }
				
				 adapter2 = new ArrayAdapter<String>(this, R.layout.list_item, RECIPES);
				    recipesList.setAdapter(adapter2);
			}
	}

	public void updateRecipe(View view) {

			String txt = recipesList.getText().toString();
			
			this.updateRecipesList(txt);

			toastTxt("You searched for "+txt); 

	}

    public boolean showPreferences(){
        Intent intent = new Intent(this, DroidRecipesPreferences.class);
        startActivity(intent);
        return true;

    }

}
