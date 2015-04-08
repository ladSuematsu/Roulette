package ladsoft.roulette.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import ladsoft.roulette.R;
import ladsoft.roulette.adapter.PlaceHistoryCursorAdapter;
import ladsoft.roulette.adapter.PlaceHistoryListAdapter;
import ladsoft.roulette.contentprovider.RouletteContentProvider;
import ladsoft.roulette.database.table.PlaceHistoryTable;
import ladsoft.roulette.entity.PlaceHistory;
import ladsoft.roulette.manager.DatabaseManager;

public class Tab2
    extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    private Activity mActivity;

    private ListView mListViewHistory;
    protected CursorAdapter mCursorAdapter;
//
    private Resources mResources;
    private Calendar mCalendar;
    private ArrayList<String> mArrayPlace;
    private ArrayList<PlaceHistory> mArrayHistory;
    private String[] mArrayWeekday;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab2 newInstance(String param1, String param2) {
        Tab2 fragment = new Tab2();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    public Tab2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // init variables
        mResources = getResources();
        mCalendar = Calendar.getInstance();
        mArrayHistory = new ArrayList<PlaceHistory>();
        mArrayPlace = new ArrayList<String>(Arrays.asList(mResources.getStringArray(R.array.places)));
        mArrayWeekday = mResources.getStringArray(R.array.weekdays);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab_fragment_2, container, false);

        initViews(view);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
////            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mActivity = activity;

        try {
//            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    private void initViews(View view) {
        mListViewHistory = (ListView) view.findViewById(R.id.fragment_history_listview);

        Bundle extras = new Bundle();
        extras.putStringArray(PlaceHistoryListAdapter.ARG_PARAM_WEEKDAYS, mArrayWeekday);

        mCursorAdapter = new PlaceHistoryCursorAdapter(mActivity, null, true);

        getLM();

        mListViewHistory.setAdapter(mCursorAdapter);
    }

    private void getLM()
    {
        LoaderManager loaderManager = getLoaderManager();

        loaderManager.initLoader(0, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            DatabaseManager mDataBaseManager;

            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                mDataBaseManager = new DatabaseManager(mActivity);

                CursorLoader cursorLoader =
                        mDataBaseManager.query(RouletteContentProvider.PLACE_HISTORY_CONTENT_URI
                                , PlaceHistoryTable.ALL_COLUMNS
                                , null
                                , null
                                , null);

                return cursorLoader;
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                mCursorAdapter.swapCursor(data);
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                mCursorAdapter.swapCursor(null);
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//    }

}
