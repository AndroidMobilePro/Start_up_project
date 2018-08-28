package com.textonphoto.photoeditor.quotecreator.brush;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.textonphoto.photoeditor.quotecreator.R;

public class BrushFragment extends Fragment {

    private OnBrushListener onBrushListener;

    public BrushFragment() {
        // Required empty public constructor
    }

    public static BrushFragment newInstance() {
        BrushFragment fragment = new BrushFragment();
        return fragment;
    }

    public void setBrushListener(OnBrushListener onBrushListener) {
        this.onBrushListener = onBrushListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_brush, container, false);
        return view;
    }


    static public interface OnBrushListener {
        void onBrushChoose();
    }
}
