package ladsoft.roulette.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import ladsoft.roulette.R;
import ladsoft.roulette.entity.PlaceHistory;

/**
 * ListAdapter for history list - for DB-less test purposes.
 */
public class PlaceHistoryListAdapter extends BaseAdapter{
    public static final String ARG_PARAM_WEEKDAYS = "0";

    private Context mContext;
    private Calendar mCalendar;
    private List<PlaceHistory> mPlaceHistoryList;
    private String[] mArrayWeekday;


    public PlaceHistoryListAdapter(Context context, List<PlaceHistory> placeHistoryList, Bundle extras) {
        this.mContext = context;
        this.mPlaceHistoryList = placeHistoryList;
        this.mCalendar = Calendar.getInstance();

        if(extras != null){
            this.mArrayWeekday = extras.getStringArray(this.ARG_PARAM_WEEKDAYS);
        }

    }

    @Override
    public int getCount() {
        return mPlaceHistoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPlaceHistoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_result_history, null);

        TextView dayWeekTextView = (TextView) view.findViewById(R.id.fragment_result_dayweek_text);
        TextView dateView = (TextView) view.findViewById(R.id.fragment_result_date_text);
        TextView placeTextView = (TextView) view.findViewById(R.id.fragment_result_place_text);

        PlaceHistory placeHistory = mPlaceHistoryList.get(position);

        mCalendar.setTime(placeHistory.getDate());

        String weekday = mArrayWeekday[(mCalendar.get(Calendar.DAY_OF_WEEK) - 1)];

        dayWeekTextView.setText(weekday);
        placeTextView.setText(placeHistory.getPlace());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
        dateView.setText(sdf.format(mCalendar.getTime()));

        return view;
    }

    public List<PlaceHistory> getAllItems()
    {
        return mPlaceHistoryList;
    }

}
