package ladsoft.roulette.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;
import java.util.Date;

import ladsoft.roulette.database.table.PlaceHistoryTable;

/**
 * SQLiteOpenHelper implementation.
 */
public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table creation
        db.execSQL(PlaceHistoryTable.SQL_CREATE);

        // Mock data
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);

        String sqlMock1 = "INSERT INTO " + PlaceHistoryTable.TABLE_NAME + " VALUES(2, 'Muqueca', '" + calendar.getTime().getTime() +"')";
        db.execSQL(sqlMock1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

////Optional
//    @Override
//    public void onOpen(SQLiteDatabase db) {
//        super.onOpen(db);
//    }
}
