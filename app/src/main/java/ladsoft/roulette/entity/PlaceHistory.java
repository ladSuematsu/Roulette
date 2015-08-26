package ladsoft.roulette.entity;

import android.database.Cursor;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import ladsoft.roulette.database.table.PlaceHistoryTable;

/**
 * Place history model class
 */
public class PlaceHistory implements Serializable {

    private long id;
    private Date date;
    private long timestamp;
    private String place;

    // Default empty constructor.
    public PlaceHistory() {}

    public PlaceHistory(final Cursor cursor) {
        this.id = cursor.getLong(cursor.getColumnIndex(PlaceHistoryTable.ID));
        this.timestamp = cursor.getLong( cursor.getColumnIndex(PlaceHistoryTable.SORT_DATE) );
        this.place = cursor.getString(cursor.getColumnIndex(PlaceHistoryTable.PLACE_NAME));
    }


    public Date getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.timestamp);
        return this.date;
    }
    public void setDate(Date date) { this.date = date; }

    public String getPlace() { return this.place; }
    public void setPlace(String place) { this.place = place; }
}
