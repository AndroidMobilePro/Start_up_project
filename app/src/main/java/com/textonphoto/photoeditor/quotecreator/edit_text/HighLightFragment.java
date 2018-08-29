package com.textonphoto.photoeditor.quotecreator.edit_text;

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

public class HighLightFragment extends Fragment {

    private RecyclerView backgroundRecyclerView;
    private FormatAdapter backgroudAdapter;
    private ImageView showMore;

    private OnBackgroundListener onBackgroundListener;

    public HighLightFragment() {
        // Required empty public constructor
    }

    public static HighLightFragment newInstance() {
        HighLightFragment fragment = new HighLightFragment();
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
        View view = inflater.inflate(R.layout.fragment_edit_text_highlight, container, false);
        backgroudAdapter = new FormatAdapter(getActivity());

        backgroundRecyclerView = view.findViewById(R.id.background_recycler_view);
        LinearLayoutManager layoutEditManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        backgroundRecyclerView.setLayoutManager(layoutEditManager);
        backgroundRecyclerView.setHasFixedSize(true);
        //This listener will change the text fonts when clicked on any fonts
        backgroundRecyclerView.setAdapter(backgroudAdapter);

        showMore = view.findViewById(R.id.showMore);
        showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "show more", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


    static public interface OnBackgroundListener {
        void onBackgroundChoose();
    }
}
