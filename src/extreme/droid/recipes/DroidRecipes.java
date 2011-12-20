package extreme.droid.recipes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import android.webkit.WebView;
import android.webkit.WebViewClient;

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
import android.widget.EditText;

//OnKeyListener
import android.view.View.OnKeyListener;
import android.view.KeyEvent;
import 	android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import android.view.View.OnClickListener;
import 	android.widget.Button;
import java.lang.String;
import	android.widget.SimpleCursorAdapter;
import 	android.content.Intent;
import android.app.ListActivity;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
////////////////////////////////////////////
import android.widget.AutoCompleteTextView;

public class DroidRecipes extends Activity
{

//////////////////////////
	public Button searchTextBtn;
  	protected EditText searchText;
	protected EditText edittext;
        protected SQLiteDatabase db;
        protected Cursor cur;
        protected ListAdapter adapter;
        protected ListView employeeList;
	protected WebView webview;
	protected ArrayAdapter<String> adapter2;
	protected AutoCompleteTextView recipesList;
///////////////////////////////////////////////////

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	
	  db = (new DataBaseHelper(this)).getWritableDatabase();
	///////////////////////////////////////////////////////////

	       setContentView(R.layout.main);

	   employeeList = (ListView) findViewById (R.id.list);

	 webview = (WebView) findViewById(R.id.MyWebview);

	loadHtmlData(getApplicationContext().getString(R.string.mystring));

	edittext = (EditText) findViewById(R.id.edittext);
/////////////////////////////////
	String[] COUNTRIES = new String[] {"walk","testsw"};
	
	 recipesList = (AutoCompleteTextView) findViewById(R.id.list_autocomplete);
	    adapter2 = new ArrayAdapter<String>(this, R.layout.list_item, COUNTRIES);
	    recipesList.setAdapter(adapter2);

/////////////////////////////////////////////////////////////////////////////
		final Button	searchTextBtn = (Button) findViewById(R.id.searchtext);

		employeeList.setOnItemClickListener(
			new OnItemClickListener()
			{

			    @Override
			    public void onItemClick(AdapterView<?> arg0, View view,
				    int position, long id) {

				
				toastTxt("You pressed the icon and text!"+id);
				Cursor cur = (Cursor) adapter.getItem(position);

				String selected_id = cur.getString(cur.getColumnIndex("_id"));
				String txt = cur.getString(cur.getColumnIndex("title"));
				loadHtmlData(selected_id+" "+txt);
			    }   
			}       
		);
//////////////////////////////////////////////////
		recipesList.setOnItemClickListener(
			new OnItemClickListener()
			{

			    @Override
			    public void onItemClick(AdapterView<?> arg0, View view,
				    int position, long id) {
				toastTxt("Position "+id+" "+position);
				
				
			    }   
			}       
		);
//////////////////////////////////////////////////////////////////////

/*
		edittext.setOnKeyListener(new OnKeyListener() {
		    public boolean onKey(View v, int keyCode, KeyEvent event) {
			// If the event is a key-down event on the "enter" button
			if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
			    (keyCode == KeyEvent.KEYCODE_ENTER)) {
			  // Perform action on key press
				String txt =  edittext.getText().toString();
				toastTxt(txt);
				loadHtmlData(txt);
			  return true;
			}
			return false;
		    }
		});
*/

/*
		searchTextBtn.setOnClickListener(new OnClickListener() { public void onClick (View v){ 
			String txt = edittext.getText().toString();
		}});
*/

////////////////////////////////////////////////////////////////////////////////////////
    }
/*
	public String getResourcesValue(String txt) {
		return this.getResources().getString(txt);
	}
*/

	public void toastTxt(String txt) {
		Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
	}

	public void loadHtmlData(String info) {

		String summary = "<html><body>";
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
        }
        return true;

    }


    public void searchEmploy(View view) {

			String txt = edittext.getText().toString();
				Log.i("SEARCHING","search for "+txt);
			
			toastTxt("You searched for "+txt); 
				Cursor curi = db.rawQuery("SELECT _id FROM recipes",null);
			
			 cur = db.rawQuery("SELECT _id, firstName, lastName, title FROM recipes WHERE firstName || ' ' || lastName LIKE ?", 
                                                new String[]{"%" + txt + "%"});
			    adapter = new SimpleCursorAdapter(
                                DroidRecipes.this, 
                                R.layout.employee_list_item, 
                                cur, 
                                new String[] {"firstName","lastName","title"}, 
                                new int[] {R.id.firstName,R.id.lastName,R.id.title});
			
               		 employeeList.setAdapter(adapter);

			String ids = "";

			Integer counts = cur.getCount();
			 if (counts > 0) {

				Integer x = 0;
				String[] RECIPES = new String[counts];
			    while (cur.moveToNext()) {
				ids+= cur.getString(cur.getColumnIndex("firstName"));
				RECIPES[x] = cur.getString(cur.getColumnIndex("firstName"))+" "+cur.getString(cur.getColumnIndex("lastName"));
				x++;
		 	        }
				
				 adapter2 = new ArrayAdapter<String>(this, R.layout.list_item, RECIPES);
				    recipesList.setAdapter(adapter2);

			    }
			
			  
			String summary="walk re walk"+ids+curi.getCount();
			loadHtmlData(summary);

		
    }


}
