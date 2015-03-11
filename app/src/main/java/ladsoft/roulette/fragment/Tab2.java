package ladsoft.roulette.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ladsoft.roulette.R;

public class Tab2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    private Activity mActivity;

//    private Button mButtonRun;
//    private TextView mTxtViewResult;
//    private ListView mListViewHistory;
//    private BaseAdapter mListAdapter;
//
//    private Resources mResources;
//    private String mRandomResult;
//    private Calendar mCalendar;
//    private ArrayList<String> mArrayPlace;
//    private ArrayList<PlaceHistory> mArrayHistory;
//    private String[] mArrayWeekday;
//    private String mDayWeek;
//    private PlaceHistory mPlaceHistory;

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
//        mResources = getResources();
//        mCalendar = Calendar.getInstance();
//        mArrayHistory = new ArrayList<PlaceHistory>();
//        mArrayPlace = new ArrayList<String>(Arrays.asList(mResources.getStringArray(R.array.places)));
//        mArrayWeekday = mResources.getStringArray(R.array.weekdays);
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

//        mButtonRun = (Button) view.findViewById(R.id.tab1_btn_run);
//        mTxtViewResult = (TextView) view.findViewById(R.id.tab1_txtview_result);
       // mListViewHistory = (ListView) view.findViewById(R.id.main_listview_history);
//        mDayWeek = mArrayWeekday[(mCalendar.get(Calendar.DAY_OF_WEEK) - 1)];

//        mButtonRun.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPlaceHistory = new PlaceHistory();
//
//                mRandomResult = mArrayPlace.get(randomize() - 1);
//
//                mPlaceHistory.setPlace(mRandomResult);
//                mPlaceHistory.setDate(new Date());
//
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(ResultDialog.ARG_PARAM_PLACEHISTORY , mPlaceHistory);
//                bundle.putStringArray(ResultDialog.ARG_PARAM_WEEKDAYS, mArrayWeekday);
//                bundle.putString(ResultDialog.ARG_PARAM_PLACE, mRandomResult);
//                // bundle.putString(ResultDialog.ARG_PARAM_WEEKDAY, mDayWeek);
//
//                showResultDialog(bundle);
//
//                mButtonRun.setText(mResources.getString(R.string.text_btn_runagain));
//            }
//        });

       // Bundle extras = new Bundle();
       // extras.putStringArray(PlaceHistoryListAdapter.ARG_PARAM_WEEKDAYS, mArrayWeekday);

       // mListAdapter = new PlaceHistoryListAdapter(mActivity, mArrayHistory, extras);

     //   mListViewHistory.setAdapter(mListAdapter);
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
