package ladsoft.roulette.fragment;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import ladsoft.roulette.R;
import ladsoft.roulette.adapter.PlaceHistoryRecyclerViewCursorAdapter;
import ladsoft.roulette.contentprovider.RouletteContentProvider;
import ladsoft.roulette.database.table.PlaceHistoryTable;
import ladsoft.roulette.manager.DatabaseManager;

/**
 * Fragment for history list tab.
 */
public class Tab2 extends Fragment {

    protected PlaceHistoryRecyclerViewCursorAdapter mCursorAdapter;
    private Activity mActivity;

    private ListView mListViewHistory;
    private RecyclerView mRecyclerViewHistory;
    private Cursor mCursor;

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
        //View view = inflater.inflate(R.layout.tab_fragment_2, container, false);

        View view = inflater.inflate(R.layout.place_history_list, container, false);

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
        //mListViewHistory = (ListView) view.findViewById(R.id.fragment_history_listview);
        mRecyclerViewHistory = (RecyclerView) view;

        //mCursorAdapter = new PlaceHistoryCursorAdapter(mActivity, null, true);

        getLM();

//        mCursorAdapter = new PlaceHistoryRecyclerViewCursorAdapter(getActivity(), null);
//
//        //mListViewHistory.setAdapter(mCursorAdapter);
//        mRecyclerViewHistory.setAdapter(mCursorAdapter);
        mLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        this.setRecyclerViewLayoutManager(mLayoutManagerType);
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
                //mCursorAdapter.swapCursor(data);

                if(mCursorAdapter == null) {
                    mCursorAdapter = new PlaceHistoryRecyclerViewCursorAdapter(getActivity(), data);

                    //mListViewHistory.setAdapter(mCursorAdapter);
                    mRecyclerViewHistory.setAdapter(mCursorAdapter);
                } else {
                    mCursorAdapter.swapCursor(data);
                }
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                //mCursorAdapter.swapCursor(null);

                if(mCursorAdapter == null) {
                    mCursorAdapter = new PlaceHistoryRecyclerViewCursorAdapter(getActivity(), null);

                    //mListViewHistory.setAdapter(mCursorAdapter);
                    mRecyclerViewHistory.setAdapter(mCursorAdapter);
                } else {
                    mCursorAdapter.swapCursor(null);
                }
            }
        });
    }

    private static final int SPAN_COUNT = 2;
    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER
        , LINEAR_LAYOUT_MANAGER
    }
    private LayoutManagerType mLayoutManagerType;
    private RecyclerView.LayoutManager mLayoutManager;
    private void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        if(mRecyclerViewHistory.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerViewHistory.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();

        }

        switch(layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(this.getActivity(), SPAN_COUNT);
                mLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;

            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(this.getActivity());
                mLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;

            default:
                mLayoutManager = new LinearLayoutManager(this.getActivity());
                mLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        }

        mRecyclerViewHistory.setLayoutManager(mLayoutManager);
        mRecyclerViewHistory.scrollToPosition(scrollPosition);
    }

}
