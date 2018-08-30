package com.textonphoto.photoeditor.quotecreator.decorate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.textonphoto.photoeditor.quotecreator.R;
import com.textonphoto.photoeditor.quotecreator.background.BackgroudAdapter;

public class DecorateFragment extends Fragment {

    private RecyclerView rvConstraintTools;
    private BackgroudAdapter backgroudAdapter;

    private OnBackgroundListener onBackgroundListener;

    public DecorateFragment() {
        // Required empty public constructor
    }

    public static DecorateFragment newInstance() {
        DecorateFragment fragment = new DecorateFragment();
        return fragment;
    }

    public void setBackgroudListener(OnBackgroundListener onBackgroundListener) {
        this.onBackgroundListener = onBackgroundListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_decorate, container, false);
        backgroudAdapter = new BackgroudAdapter(getActivity());

        rvConstraintTools = view.findViewById(R.id.rvConstraintTools);
        LinearLayoutManager layoutEditManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvConstraintTools.setLayoutManager(layoutEditManager);
        rvConstraintTools.setHasFixedSize(true);
        //This listener will change the text fonts when clicked on any fonts
        rvConstraintTools.setAdapter(backgroudAdapter);

        return view;
    }


    static public interface OnBackgroundListener {
        void onBackgroundChoose();
    }
}
