package ladsoft.roulette.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import ladsoft.roulette.R;
import ladsoft.roulette.adapter.PlaceHistoryListAdapter;
import ladsoft.roulette.entity.PlaceHistory;
import ladsoft.roulette.fragment.ResultDialog;

//TODO: Make app work with PlaceHistory entity class
public class MainActivity extends ActionBarActivity implements ResultDialog.OnFragmentInteractionListener {
    @Override
    public void onFragmentInteraction(Bundle bundle) {
        mArrayHistory.add((PlaceHistory) bundle.getSerializable(ResultDialog.ARG_PARAM_PLACEHISTORY));
        mTxtViewResult.setText(bundle.getString(ResultDialog.ARG_PARAM_PLACE));
        mListAdapter.notifyDataSetChanged();
    }

    private Button mButtonRun;
    private TextView mTxtViewResult;
    private ListView mListViewHistory;
    private BaseAdapter mListAdapter;
    
    private Resources mResources;
    private String mRandomResult;
    private Calendar mCalendar;
    private ArrayList<String> mArrayPlace;
    private ArrayList<PlaceHistory> mArrayHistory;
    private String[] mArrayWeekday;
    private String mDayWeek;
    private PlaceHistory mPlaceHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mResources = getResources();
        mCalendar = Calendar.getInstance();
        mArrayHistory = new ArrayList<PlaceHistory>();
        mArrayPlace = new ArrayList<String>(Arrays.asList(mResources.getStringArray(R.array.places)));
        mArrayWeekday = mResources.getStringArray(R.array.weekdays);
        initViews();
    }

    private void initViews() {
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        mButtonRun = (Button) this.findViewById(R.id.main_btn_run);
        mTxtViewResult = (TextView) this.findViewById(R.id.main_txtview_result);
        mListViewHistory = (ListView) this.findViewById(R.id.main_listview_history);
        mDayWeek = mArrayWeekday[(mCalendar.get(Calendar.DAY_OF_WEEK) - 1)];

        mButtonRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlaceHistory = new PlaceHistory();

                mRandomResult = mArrayPlace.get(randomize() - 1);

                mPlaceHistory.setPlace(mRandomResult);
                mPlaceHistory.setDate(new Date());

                Bundle bundle = new Bundle();
                bundle.putSerializable(ResultDialog.ARG_PARAM_PLACEHISTORY , mPlaceHistory);
                bundle.putStringArray(ResultDialog.ARG_PARAM_WEEKDAYS, mArrayWeekday);
                bundle.putString(ResultDialog.ARG_PARAM_PLACE, mRandomResult);
               // bundle.putString(ResultDialog.ARG_PARAM_WEEKDAY, mDayWeek);

                showResultDialog(bundle);

                mButtonRun.setText(mResources.getString(R.string.text_btn_runagain));
            }
        });

        Bundle extras = new Bundle();
        extras.putStringArray(PlaceHistoryListAdapter.ARG_PARAM_WEEKDAYS, mArrayWeekday);

        mListAdapter = new PlaceHistoryListAdapter(this, mArrayHistory, extras);

        mListViewHistory.setAdapter(mListAdapter);
    }


    private void showResultDialog(Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ResultDialog resultDialog = new ResultDialog();
        resultDialog.setArguments(bundle);
        resultDialog.show(fragmentManager, "show");
    }

    private int randomize()
    {
        int min = 1;
        int max = mArrayPlace.size();;

        Random r = new Random();
        int result = r.nextInt(max - min + 1) + min;

        return result;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
