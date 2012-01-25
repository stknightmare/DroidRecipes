package extreme.droid.recipes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "recipes.db";
        private static final int DATABASE_VERSION = 1;
	public static final String tbl = "recipes";
	
	

        public DataBaseHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

                String sql = "CREATE TABLE IF NOT EXISTS recipes (" +
                                                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +                                                 
                                                "title TEXT, " +              
                                                
						"recipe TEXT " +
                                                ")";
                db.execSQL(sql);
                
                ContentValues values = new ContentValues();

                String title = "title";

                values.put("title", "Arnaki");
		values.put("recipe", "arnaki frikase");
                db.insert(tbl, title, values);

             
                values.put("title", "mpamies");
             	values.put("recipe", "mpamies giaxni");
                db.insert(tbl, title, values);

             
                values.put("title", "arnaki");
             	values.put("recipe", "arnaki galaktos");
                db.insert(tbl, title, values);

                values.put("title", "arnaki");
             	values.put("recipe", "arnaki sth gastra");
		db.insert(tbl, title, values);

                values.put("title", "batraxopodara");
                values.put("recipe", "batraxopodara sto fourno");
                db.insert(tbl, title, values);
                
                values.put("title", "batraxopodara");
               	values.put("recipe", "batraxopodara sto tsoukali");
                db.insert(tbl, title, values);

              
                values.put("title", "batraxopodara");
              	values.put("recipe", "batraxopodara sto container");
                db.insert(tbl, title, values);

                values.put("title", "arnaki");
              	values.put("recipe", "arnaki fournou");
                db.insert(tbl, title, values);

                values.put("title", "arnaki");
              	values.put("recipe", "kai allo arnaki fournou");
                db.insert(tbl, title, values);

		values.put("title", "arnaki gastras");
              	values.put("recipe", "kai allo arnaki fournou gastras");
                db.insert(tbl, title, values);
	
		values.put("title", "arnaki gastras kalo");
              	values.put("recipe", "kai allo arnaki fournou gastras kalh geush");
                db.insert(tbl, title, values);

		values.put("title", "αρνάκι γάστρας καλό");
              	values.put("recipe", "αρνάκι γάστρας καλό, καλή γεύση.");
                db.insert(tbl, title, values);
                
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		Log.i("DATABASES","ov "+oldVersion+" nv "+newVersion);

                for (int i = oldVersion+1; i <= newVersion; i++) {
		Log.i("DATABASES","Updating to" + i);
			  switch (i) {
					
            			case 2:
					
					upgradeToVersion2(db);
					break;	
				/*case 3:
					
					upgradeToVersion3(db);
					break;	
				case 4:
					
					upgradeToVersion2(db);
					break;	*/
			}
			
		}

		// db.execSQL("DROP TABLE IF EXISTS recipes");
               // onCreate(db);
        }

//////////////////////////////
///version2
public void upgradeToVersion2(SQLiteDatabase db) {
		 db.execSQL("CREATE TABLE fortnights ( " +
						"first_day DATE PRIMARY KEY" +
						")");
		Log.i("UPGRADE","create");
}

/////////////////////////////////////////////////
        
}

