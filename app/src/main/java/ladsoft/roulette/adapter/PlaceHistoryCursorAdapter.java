package ladsoft.roulette.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ladsoft.roulette.R;
import ladsoft.roulette.database.table.PlaceHistoryTable;

/**
 * Created by suematsu on 4/1/15.
 */
public class PlaceHistoryCursorAdapter extends CursorAdapter {

    private Calendar mCalendar;
    private String[] mArrayWeekDay;

    public PlaceHistoryCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        mArrayWeekDay = context.getResources().getStringArray(R.array.weekdays);
        mCalendar = Calendar.getInstance();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View fragment = inflater.inflate(R.layout.fragment_result_history, parent, false);

        return fragment;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView placeText = (TextView) view.findViewById(R.id.fragment_result_place_text);
        TextView dateText = (TextView) view.findViewById(R.id.fragment_result_date_text);
        TextView weekText = (TextView) view.findViewById(R.id.fragment_result_dayweek_text);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");

        Date date = new Date(cursor.getLong(cursor.getColumnIndex(PlaceHistoryTable.SORT_DATE)));
        mCalendar.setTime(date);
        String weekDay = mArrayWeekDay[mCalendar.get(Calendar.DAY_OF_WEEK) - 1];

        placeText.setText(cursor.getString(cursor.getColumnIndex(PlaceHistoryTable.PLACE_NAME)));
        dateText.setText(dateFormat.format(date.getTime()));
        weekText.setText(weekDay);
    }
}
