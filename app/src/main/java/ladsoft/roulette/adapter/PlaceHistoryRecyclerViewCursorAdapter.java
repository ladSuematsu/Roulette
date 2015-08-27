package ladsoft.roulette.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ladsoft.roulette.R;
import ladsoft.roulette.adapter.base.CursorRecyclerViewAdapter;
import ladsoft.roulette.database.table.PlaceHistoryTable;

/**
 * Created by suematsu on 8/27/15.
 */
public class PlaceHistoryRecyclerViewCursorAdapter extends CursorRecyclerViewAdapter<PlaceHistoryRecyclerViewCursorAdapter.ViewHolder>{

    private final String[] mArrayWeekDay;
    private Calendar mCalendar;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dayWeekTextView;
        public TextView dateView;
        public TextView placeTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            dayWeekTextView = (TextView) itemView.findViewById(R.id.fragment_result_dayweek_text);
            dateView = (TextView) itemView.findViewById(R.id.fragment_result_date_text);
            placeTextView = (TextView) itemView.findViewById(R.id.fragment_result_place_text);
        }

    }

    public PlaceHistoryRecyclerViewCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);

        mArrayWeekDay = context.getResources().getStringArray(R.array.weekdays);
        mCalendar = Calendar.getInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_result_history, parent, false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");

        Date date = new Date(cursor.getLong(cursor.getColumnIndex(PlaceHistoryTable.SORT_DATE)));
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(date);

        String weekDay = mArrayWeekDay[mCalendar.get(Calendar.DAY_OF_WEEK) - 1];

        viewHolder.placeTextView.setText(cursor.getString(cursor.getColumnIndex(PlaceHistoryTable.PLACE_NAME)));
        viewHolder.dayWeekTextView.setText(dateFormat.format(date.getTime()));
        viewHolder.dateView.setText(weekDay);
    }
}
