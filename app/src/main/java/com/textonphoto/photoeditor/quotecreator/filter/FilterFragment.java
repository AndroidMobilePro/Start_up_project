package com.textonphoto.photoeditor.quotecreator.filter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.textonphoto.photoeditor.quotecreator.R;

public class FilterFragment extends Fragment {

    private OnFilterListener onFilterListener;

    private RecyclerView filterRecyclerView;
    private FilterAdapter filterAdapter;
    public FilterFragment() {
        // Required empty public constructor
    }

    public static FilterFragment newInstance() {
        FilterFragment fragment = new FilterFragment();
        return fragment;
    }

    public void setFilterListener(OnFilterListener onFilterListener) {
        this.onFilterListener = onFilterListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        filterAdapter = new FilterAdapter(getActivity());
        filterRecyclerView = view.findViewById(R.id.filter_recycler_view);
        LinearLayoutManager layoutEditManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        filterRecyclerView.setLayoutManager(layoutEditManager);
        filterRecyclerView.setHasFixedSize(true);
        //This listener will change the text fonts when clicked on any fonts
        filterRecyclerView.setAdapter(filterAdapter);
        return view;
    }


    static public interface OnFilterListener {
        void onFilterChoose();
    }
}
