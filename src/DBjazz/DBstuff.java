package DBjazz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
public class DBstuff{
	
	public static final String KEY_ROWID = "_id";
    public static final String KEY_NUM = "_id";
    public static final String KEY_DATE = "date";
    public static final String KEY_RESULTS = "results";
    
    
    private static final String DB_NAME = "cdiagno";
    private static final String DB_TABLE = "saved";
    private static final int DB_VERSION = 1;
    String results[];
    private DBhandler chandler;
    private Context c;
    private SQLiteDatabase  cdb;
	
private static class DBhandler extends SQLiteOpenHelper {
      
	
       
       public DBhandler(Context context){
   		super(context, DB_NAME, null, DB_VERSION);
   		// TODO Auto-generated constructor stub
   	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE " + DB_TABLE + " (" +
		KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
	    KEY_DATE + " TEXT NOT NULL, " + 
		KEY_RESULTS + " TEXT NOT NULL "+ " )"
		);
		System.out.println("adddedd ddbbbb!!!" );
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS" + DB_TABLE);
		onCreate(db);
	}
}
public DBstuff(Context k){
	
	c = k;
}
public DBstuff open(){
	chandler = new DBhandler(c);
	cdb = chandler.getWritableDatabase();
	return this;
}
public void close(){
	chandler.close();
}
public void createEntry(String date, String illness) {
	// TODO Auto-generated method stub
	ContentValues cv = new ContentValues();
	cv.put(KEY_DATE, date);
	cv.put(KEY_RESULTS, illness);
	
	cdb.insert(DB_TABLE, null, cv);
	System.out.println("inserted");
}
public  String[] getIllness(String d) {
	// TODO Auto-generated method stub
	String[] columns = new String[]{KEY_DATE,KEY_RESULTS};
	System.out.println("enteredill");
	Cursor c = cdb.query(DB_TABLE, columns,null,null, null, null, null);
	if(c.getCount()==0){
		results = null;
	}
	else{
	int dex =c.getColumnIndex(KEY_RESULTS);
	int dex1 =c.getColumnIndex(KEY_DATE);
	results = new String[10];
	
	for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
		
		if( c.getString(dex1).equals(d)){
		results[c.getPosition()]= c.getString(dex);
		System.out.println(c.getString(dex));
		
		
		}
	}}
	return results;
}
public  String[] getDates() {
	// TODO Auto-generated method stub
	String[] columns = new String[]{KEY_DATE};
	System.out.println("entereddd");
	Cursor c = cdb.query(DB_TABLE, columns, null, null, null, null, null);
	// int dex =c.getColumnIndex(KEY_DATE);
	if(c.getCount()==0){
		results = null;
	}
	else{
	c.moveToFirst();
	
	results = new String[c.getCount()];
	while(!c.isAfterLast()){
		results[c.getPosition()]= c.getString(c.getColumnIndex(KEY_DATE));
		
	c.moveToNext();
	}
	System.out.println("gottteenn");
	c.close();
	}
	return results;
	
}
}
