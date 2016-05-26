package com.nathanpickard.events;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.nathanpickard.events.Constants.TABLE_NAME;
import static com.nathanpickard.events.Constants.TIME;
import static com.nathanpickard.events.Constants.TITLE;


public class EventsData extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "events.db";
    public static final int DATABASE_VERSION = 1;

    /** Create a helper object for the Events database */
    public EventsData(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + db + " (" + _ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TIME
                    + "INTEGER," + TITLE + " TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
