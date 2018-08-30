package com.textonphoto.photoeditor.quotecreator.emoji;

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

public class EmojiFragment extends Fragment implements EmojiAdapter.OnEmojiClickListener {

    private OnEmojiListener onEmojiListener;

    private RecyclerView emojiRecyclerView;
    private EmojiAdapter emojiAdapter;

    private ImageView showMore;

    public EmojiFragment() {
        // Required empty public constructor
    }

    public static EmojiFragment newInstance() {
        EmojiFragment fragment = new EmojiFragment();
        return fragment;
    }

    public void setOnEmojiListener(OnEmojiListener onEmojiListener) {
        this.onEmojiListener = onEmojiListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_emoji, container, false);

        emojiAdapter = new EmojiAdapter(getActivity());
        emojiRecyclerView = view.findViewById(R.id.emoji_recycler_view);
        LinearLayoutManager layoutEditManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        emojiRecyclerView.setLayoutManager(layoutEditManager);
        emojiRecyclerView.setHasFixedSize(true);
        emojiAdapter.setEmojiListener(this);
        //This listener will change the text fonts when clicked on any fonts
        emojiRecyclerView.setAdapter(emojiAdapter);

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
    public void onEmojiClick(String textContent) {
        if (onEmojiListener != null) {
            onEmojiListener.onEmojiClick(textContent);
        }
    }


    static public interface OnEmojiListener {
        void onEmojiClick(String textContent);
    }
}
