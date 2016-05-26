package com.nathanpickard.events;

import static android.provider.BaseColumns._ID;
import static com.nathanpickard.events.Constants.TABLE_NAME;
import static com.nathanpickard.events.Constants.TIME;
import static com.nathanpickard.events.Constants.TITLE;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EventsData events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        events = new EventsData(this);
        try {
            addEvent("Hello Android!");
            Cursor cursor = getEvents();
            showEvents(cursor);
        } finally {
            events.close();
        }
    }


    private static String[] FROM = { _ID, TIME, TITLE, };
    private static String ORDER_BY = TIME + " DESC";
    private Cursor getEvents() {
        // Perform a managed query. The Activity will handle closing
        // and re-query the cursor when needed.
        SQLiteDatabase db = events.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, FROM, null, null, null,
                null, ORDER_BY);
        startManagingCursor(cursor);
        return cursor;
    }

    private void addEvent(String string) {
        // Insert a new record into the Events data source.
        // You would do something similar for delete and update.
        SQLiteDatabase db = events.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TIME, System.currentTimeMillis());
        values.put(TITLE, string);
        db.insertOrThrow(TABLE_NAME, null, values);
    }
}
