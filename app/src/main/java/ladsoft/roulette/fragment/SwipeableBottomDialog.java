package ladsoft.roulette.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ladsoft.roulette.R;

public abstract class SwipeableBottomDialog extends DialogFragment {

    private ViewGroup rootView;
    private ListView listView;
    private int DISMISS_TRIGGER_VIEW_HEIGHT_PX = 1;
    private Object displayMetrics;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.BottomDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.bottom_dialog_fragment, container);
        listView = (ListView) rootView.findViewById(R.id.bottom_dialog_list);

        List<View> views = new ArrayList<>();
        views.add(getDismissTriggerView());
        views.add(getInvisibleSpacingView());
        views.add(getContentView(inflater, container, savedInstanceState));
        BottomDialogListAdapter adapter = new BottomDialogListAdapter(views);
        listView.setAdapter(adapter);



        //return super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public View getDismissTriggerView() {
        return makeDummyView(ViewGroup.LayoutParams.MATCH_PARENT, DISMISS_TRIGGER_VIEW_HEIGHT_PX);
    }


    public View getInvisibleSpacingView() {
        return makeDummyView(ViewGroup.LayoutParams.MATCH_PARENT, dialogHeightPx());
    }

    public abstract View getContentView(LayoutInflater inflater, ViewGroup container,
                                        Bundle savedInstanceState);

    private View makeDummyView(int width, int height) {
        View view = new View(getActivity());
        view.setLayoutParams(new ListView.LayoutParams(width, height));
        view.setClickable(true);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    dismiss();
                }
                return true;
            }
        });

        return view;
    }

    private int dialogWidthPx() {
        return Math.min(getDisplayMetrics().widthPixels,
                (int) getResources().getDimension(R.dimen.swipeableDialogMaxWidth));
    }
    
    private int dialogHeightPx() {
        return getDisplayMetrics().heightPixels - getStatusBarHeightPx(getActivity());
    }

    private int getStatusBarHeightPx(Activity activity) {
        int id = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");

        TypedValue typedValue = new TypedValue();
        getResources().getValue(id, typedValue, true);

        float dps = TypedValue.complexToFloat(typedValue.data);
        float dpToPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dps, getResources().getDisplayMetrics());

        int height = Math.round(dpToPx);

        return height;
    }

    public DisplayMetrics getDisplayMetrics() {
        return getResources().getDisplayMetrics();
    }

    private class BottomDialogListAdapter extends BaseAdapter {
        private final List<View> views;

        public BottomDialogListAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public Object getItem(int position) {
            return views.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return views.get(position);
        }
    }

}
