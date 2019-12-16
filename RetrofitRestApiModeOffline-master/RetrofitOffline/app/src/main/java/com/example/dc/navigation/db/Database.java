package com.example.dc.navigation.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dc.navigation.models.Icon;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "db_icon";
	private static final int DATABASE_VERSION = 1;

	private static final String TABLE_NAME_ICON = "table_icon";
	private static final String TABLE_NAME_PLACES = "table_placeS";
	private static final String TABLE_NAME_PUBLIC_SERVICES = "table_public_services";

	private static Database database;
	private Context ctx;
	private SQLiteDatabase sqlDb;


	private Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.ctx = context;
		sqlDb = this.getWritableDatabase();
	}


	public static synchronized Database getInstance(Context ctx){
		if(database == null) {
            database = new Database(ctx);
        }
		return database;
	}

	public SQLiteDatabase getDatabase(){
		return this.sqlDb;
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		String createTableEvents = "CREATE TABLE " + TABLE_NAME_ICON
                + "("
                + " id_icon INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " title1 TEXT, "
                + " title2 TEXT, "
				+ " url_image TEXT "
                + ");";

        String createTablePublicService = "CREATE TABLE " + TABLE_NAME_PUBLIC_SERVICES
                + "("
                + "id_public_service INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " title TEXT, "
                + " address TEXT, "
                + " time TEXT, "
                + " date TEXT, "
                + " site TEXT "
                +");";


        db.execSQL(createTableEvents);
        db.execSQL(createTablePublicService);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ICON);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PUBLIC_SERVICES);
        }
        onCreate(db);
	}

	public ArrayList<Icon> listAllIcon() {
		ArrayList<Icon> list = new ArrayList<Icon>();

		String query = "SELECT  * FROM " + TABLE_NAME_ICON;
		Cursor cursor = sqlDb.rawQuery(query, null);

		while(cursor.moveToNext()){
			int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_icon")));
			String title1 = cursor.getString(cursor.getColumnIndex("title1"));
            String title2 = cursor.getString(cursor.getColumnIndex("title2"));
            String urlImageEvent = cursor.getString(cursor.getColumnIndex("url_image"));

			Icon d = new Icon(title1, title2, urlImageEvent);
			list.add(d);
		}
		cursor.close();
		return list;
	}


    public void insertIcon(Icon event) {
        ContentValues val = new ContentValues();
		val.put("title1", event.getName());
		val.put("title2", event.getDesc());
		val.put("url_image", event.getUrlImage());
        long result = sqlDb.insert(TABLE_NAME_ICON, null, val);


    }

    public void cleanTable() {
		String delete = "DELETE FROM "+ TABLE_NAME_ICON;
		sqlDb.execSQL(delete);
	}

}
