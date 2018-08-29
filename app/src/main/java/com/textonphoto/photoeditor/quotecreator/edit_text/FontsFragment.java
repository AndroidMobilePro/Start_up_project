package com.textonphoto.photoeditor.quotecreator.edit_text;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.textonphoto.photoeditor.quotecreator.R;
import com.textonphoto.photoeditor.quotecreator.constants.Contants;

public class FontsFragment extends Fragment {

    private RecyclerView fontTextRecyclerView;
    private ImageView showMore;
    private FontsAdapter fontsAdapter;
    private Typeface mTypeface;
    private OnFontListener onFontListener;
    private FontsBSFragment fontsBSFragment;

    public FontsFragment() {
        // Required empty public constructor
    }

    public static FontsFragment newInstance() {
        FontsFragment fragment = new FontsFragment();
        return fragment;
    }

    public void setOnFontListener(OnFontListener onFontListener) {
        this.onFontListener = onFontListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fonts, container, false);
        fontsAdapter = new FontsAdapter(getActivity());
        //This listener will change the text fonts when clicked on any fonts from picker
        fontsAdapter.setFontsPickerListener(new FontsAdapter.OnTextPickerClickListener() {
            @Override
            public void onTextPickerClickListener(String textContent) {
                mTypeface = Typeface.createFromAsset(getActivity().getAssets(),
                        Contants.folderFontPath + textContent);
                if (onFontListener != null) {
                    onFontListener.onFontChoose(mTypeface);
                }
            }
        });
        fontTextRecyclerView = view.findViewById(R.id.font_text_recycler_view);
        LinearLayoutManager layoutEditManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        fontTextRecyclerView.setLayoutManager(layoutEditManager);
        fontTextRecyclerView.setHasFixedSize(true);
        //This listener will change the text fonts when clicked on any fonts
        fontTextRecyclerView.setAdapter(fontsAdapter);

        fontsBSFragment = new FontsBSFragment();
        showMore = view.findViewById(R.id.showMore);
        showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fontsBSFragment.show(getActivity().getSupportFragmentManager(), fontsBSFragment.getTag());
                fontsBSFragment.setFontListener(new FontsBSFragment.FontsListener() {
                    @Override
                    public void onFontClick(String fonts) {
                        mTypeface = Typeface.createFromAsset(getActivity().getAssets(),
                                Contants.folderFontPath + fonts);
                    }
                });
            }
        });

        return view;
    }

    static public interface OnFontListener {
        void onFontChoose(Typeface typeface);
    }

}
