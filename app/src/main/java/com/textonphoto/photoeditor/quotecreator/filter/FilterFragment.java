package com.textonphoto.photoeditor.quotecreator.filter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.textonphoto.photoeditor.quotecreator.R;
import com.textonphoto.photoeditor.quotecreator.views.PhotoFilter;

public class FilterFragment extends Fragment implements FilterListener {

    private OnFilterListener onFilterListener;

    private RecyclerView filterRecyclerView;
    private FilterViewAdapter filterAdapter;
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
        filterAdapter = new FilterViewAdapter(getActivity(), this);
        filterRecyclerView = view.findViewById(R.id.filter_recycler_view);
        LinearLayoutManager layoutEditManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        filterRecyclerView.setLayoutManager(layoutEditManager);
        filterRecyclerView.setHasFixedSize(true);
        //This listener will change the text fonts when clicked on any fonts
        filterRecyclerView.setAdapter(filterAdapter);
        return view;
    }

    @Override
    public void onFilterSelected(PhotoFilter photoFilter) {
        if (onFilterListener != null) {
            onFilterListener.onFilterChoose(photoFilter);
        }
    }


    public interface OnFilterListener {
        void onFilterChoose(PhotoFilter photoFilter);
    }
}
