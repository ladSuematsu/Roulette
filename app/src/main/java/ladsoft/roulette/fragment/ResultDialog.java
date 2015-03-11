package ladsoft.roulette.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import ladsoft.roulette.R;
import ladsoft.roulette.entity.PlaceHistory;

public class ResultDialog extends DialogFragment{
    private Resources mResources;
    private TextView mResultText;


//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PARAM_PLACE = "0";
    public static final String ARG_PARAM_WEEKDAY = "1";
    public static final String ARG_PARAM_PLACEHISTORY = "2";
    public static final String ARG_PARAM_WEEKDAYS = "3";

    // TODO: Rename and change types of parameters
    private String mPlace;
    private String mWeekday;
    private PlaceHistory mPlaceHistory;

    private OnFragmentInteractionListener mListener;

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param Parameter 1.
//     * @return A new instance of fragment ResultDialog.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static ResultDialog newInstance(String param) {
//        ResultDialog fragment = new ResultDialog();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM, param);
//        fragment.setArguments(args);
//        return fragment;
//    }

    public ResultDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPlace = getArguments().getString(ARG_PARAM_PLACE);
            mWeekday = getArguments().getString(ARG_PARAM_WEEKDAY);
            mPlaceHistory = (PlaceHistory) getArguments().getSerializable(ARG_PARAM_PLACEHISTORY);
        }
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Custom dialog building.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mResources = getResources();

        View view = inflater.inflate(R.layout.fragment_result, null);
        mResultText = (TextView) view.findViewById(R.id.fragment_result_text);
        mResultText.setText(mPlaceHistory.getPlace());

        builder.setView(view)
            .setNegativeButton(R.string.message_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            })
            .setPositiveButton(R.string.message_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mListener.onFragmentInteraction(getArguments());
                }
            });


        //return super.onCreateDialog(savedInstanceState);
        return builder.create();
    }

    /**
     * Callback interface.
     */
    public static interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Bundle bundle);
    }

}