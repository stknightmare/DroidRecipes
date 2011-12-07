package navarino.snavi;
 
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
import 	android.widget.SimpleCursorAdapter;
import android.view.View.OnClickListener;
import 	android.widget.Button;
import java.lang.String;

public class SNavi extends Activity {

//////////////////////////
	public Button searchTextBtn;
  	protected EditText searchText;
	protected EditText edittext;
        protected SQLiteDatabase db;
        protected Cursor cursor;
        protected ListAdapter adapter;
        protected ListView employeeList;

///////////////////////////////////////////////////

	WebView webview;

/*  myTextView = (TextView) this.findViewById(R.id.mytextview);
      myTextView.setText(Html.fromHtml(getResources().getString(R.string.mystring)));
*/
	
    /** Called when the activity is first created. */
       

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

  db = (new DataBaseHelper(this)).getWritableDatabase();
   
//final EditText	searchText = (EditText) findViewById(R.id.edittext);


     

////////////////////////////////////////////



   employeeList = (ListView) findViewById (R.id.list);

/////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////

       setContentView(R.layout.main);


	 WebView webview = (WebView) findViewById(R.id.MyWebview);
	 String summary = "<html><body>";

	summary+=getResources().getString(R.string.mystring);
	summary+="</body></html>";




  //  ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, employees);
    //    ListView employeeList = (ListView) findViewById(R.id.list);
      //  employeeList.setAdapter(adapter);


	 webview.loadData(summary, "text/html", "utf-8");
/////////////////////////////////////////////////////////

final EditText edittext = (EditText) findViewById(R.id.edittext);
final Button	searchTextBtn = (Button) findViewById(R.id.searchtext);
edittext.setOnKeyListener(new OnKeyListener() {
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        // If the event is a key-down event on the "enter" button
        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
            (keyCode == KeyEvent.KEYCODE_ENTER)) {
          // Perform action on key press
          Toast.makeText(SNavi.this, edittext.getText(), Toast.LENGTH_SHORT).show();
          return true;
        }
        return false;
    }
});


searchTextBtn.setOnClickListener(new OnClickListener() { public void onClick (View v){ 

	Toast.makeText(SNavi.this, "You pressed the Search button!"+edittext.getText().toString(), Toast.LENGTH_LONG).show();

	String x = "55";

}});


////////////////////////////////////////////////////////////////////////////////////////
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
		Toast.makeText(this, "You pressed the icon!", Toast.LENGTH_LONG).show();
                break;
            case R.id.text:     
		Toast.makeText(this, "You pressed the text!", Toast.LENGTH_LONG).show();
                break;
            case R.id.icontext: 
			Toast.makeText(this, "You pressed the icon and text!", Toast.LENGTH_LONG).show();
                        break;
        }
        return true;
    }

/*
    public void searchEmploy(View view) {

	Toast.makeText(this, "You pressed the Search button!"+edittext.getText().toString(), Toast.LENGTH_LONG).show();
        // || is the concatenation operation in SQLite
		
		




               cursor = db.rawQuery("SELECT _id, firstName, lastName, title FROM employee WHERE firstName || ' ' || lastName LIKE ?", 
                                                new String[]{"%" + searchText.getText().toString() + "%"});
                adapter = new SimpleCursorAdapter(
                                this, 
                                R.layout.employee_list_item, 
                                cursor, 
                                new String[] {"firstName", "lastName", "title"}, 
                                new int[] {R.id.firstName, R.id.lastName, R.id.title});
                employeeList.setAdapter(adapter);

		
    }

*/
}





