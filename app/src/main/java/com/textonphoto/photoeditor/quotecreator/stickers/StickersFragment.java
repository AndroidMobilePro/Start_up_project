package com.textonphoto.photoeditor.quotecreator.stickers;

import android.graphics.Bitmap;
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

public class StickersFragment extends Fragment implements StickerAdapter.OnStickerClickListener {

    private OnStickersListener onStickersListener;


    private RecyclerView stickerRecyclerView;
    private StickerAdapter stickerAdapter;

    private ImageView showMore;

    public StickersFragment() {
        // Required empty public constructor
    }

    public static StickersFragment newInstance() {
        StickersFragment fragment = new StickersFragment();
        return fragment;
    }

    public void setStickersListener(OnStickersListener onStickersListener) {
        this.onStickersListener = onStickersListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stickers, container, false);

        stickerAdapter = new StickerAdapter(getActivity());
        stickerRecyclerView = view.findViewById(R.id.sticker_recycler_view);
        LinearLayoutManager layoutEditManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        stickerRecyclerView.setLayoutManager(layoutEditManager);
        stickerRecyclerView.setHasFixedSize(true);

        stickerAdapter.setStickerListener(this);
        //This listener will change the text fonts when clicked on any fonts
        stickerRecyclerView.setAdapter(stickerAdapter);

        showMore = view.findViewById(R.id.showMore);
        showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "show more", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onStickerClick(Bitmap bitmap) {
        if (onStickersListener != null) {
            onStickersListener.onStickerClick(bitmap);
        }
    }


    static public interface OnStickersListener {
        void onStickerClick(Bitmap bitmap);
    }
}
