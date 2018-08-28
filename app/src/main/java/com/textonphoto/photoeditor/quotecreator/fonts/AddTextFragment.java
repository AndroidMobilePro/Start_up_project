package com.textonphoto.photoeditor.quotecreator.fonts;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.textonphoto.photoeditor.quotecreator.R;

public class AddTextFragment extends Fragment implements AddTextAdapter.EditViewListener {

    private RecyclerView addTextRecyclerView;
    private AddTextAdapter mAddTextAdapter;
    private Typeface mTypeface;
    private OnFontListener onFontListener;

    public AddTextFragment() {
        // Required empty public constructor
    }

    public static AddTextFragment newInstance() {
        AddTextFragment addTextFragment = new AddTextFragment();
        return addTextFragment;
    }

    public void setOnFontListener(OnFontListener onFontListener) {
        this.onFontListener = onFontListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAddTextAdapter = new AddTextAdapter(getActivity(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_text, container, false);
        addTextRecyclerView = view.findViewById(R.id.add_text_recycler_view);
        LinearLayoutManager layoutEditManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        addTextRecyclerView.setLayoutManager(layoutEditManager);
        addTextRecyclerView.setHasFixedSize(true);
        //This listener will change the text fonts when clicked on any fonts
        addTextRecyclerView.setAdapter(mAddTextAdapter);
        return view;
    }

    @Override
    public void onFormatListener() {

    }

    @Override
    public void onFontListener() {

    }

    @Override
    public void onTextSizeListener() {

    }

    @Override
    public void onColorListener() {

    }

    @Override
    public void onShadowListener() {

    }

    @Override
    public void onStrokeListener() {

    }

    @Override
    public void onHighLightListener() {

    }

    @Override
    public void onSpacingListener() {

    }

    @Override
    public void onBlurListener() {

    }

    static public interface OnFontListener {
        void onFontChoose(Typeface typeface);
    }

}
