package ladsoft.roulette.fragment;

import android.app.Activity;
import android.database.Cursor;
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

import ladsoft.roulette.R;
import ladsoft.roulette.adapter.PlaceHistoryCursorAdapter;
import ladsoft.roulette.contentprovider.RouletteContentProvider;
import ladsoft.roulette.database.table.PlaceHistoryTable;
import ladsoft.roulette.manager.DatabaseManager;

/**
 * Fragment for history list tab.
 */
public class Tab2 extends Fragment {

    protected CursorAdapter mCursorAdapter;
    private Activity mActivity;

    private ListView mListViewHistory;

    public Tab2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab_fragment_2, container, false);

        initViews(view);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mActivity = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initViews(View view) {
        mListViewHistory = (ListView) view.findViewById(R.id.fragment_history_listview);

        mCursorAdapter = new PlaceHistoryCursorAdapter(mActivity, null, true);

        getLM();

        mListViewHistory.setAdapter(mCursorAdapter);
    }

    /**
     * Ints loaderManager for fragment's CursorAdapter
     */
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

}
