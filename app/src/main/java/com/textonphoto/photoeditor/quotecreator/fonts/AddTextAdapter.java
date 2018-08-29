package com.textonphoto.photoeditor.quotecreator.fonts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.textonphoto.photoeditor.quotecreator.R;

import java.util.ArrayList;
import java.util.List;

public class AddTextAdapter extends RecyclerView.Adapter<AddTextAdapter.ViewHolder> {

    private Context context;
    private List<String> list = new ArrayList<>();
    private EditViewListener mEditViewListener;

    public AddTextAdapter(Context context) {
        this.context = context;
        list.add("FORMAT");
        list.add("FONT");
        list.add("TEXT SIZE");
        list.add("COLOR");
        list.add("SHADOW");
        list.add("STROKE");
        list.add("HIGHLIGHT");
        list.add("SPACING");
        list.add("BLUR");
    }

    public AddTextAdapter(Context context, EditViewListener editViewListener) {
        this(context);
        this.mEditViewListener = editViewListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_edit_text_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String textContent = list.get(position);
        holder.mTxtName.setText(textContent);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (textContent) {
                    case "FORMAT":
                        mEditViewListener.onFormatListener();
                        break;
                    case "FONT":
                        mEditViewListener.onFontListener();
                        break;
                    case "TEXT SIZE":
                        mEditViewListener.onTextSizeListener();
                        break;
                    case "COLOR":
                        mEditViewListener.onColorListener();
                        break;
                    case "SHADOW":
                        mEditViewListener.onShadowListener();
                        break;
                    case "STROKE":
                        mEditViewListener.onStrokeListener();
                        break;
                    case "HIGHLIGHT":
                        mEditViewListener.onHighLightListener();
                        break;
                    case "SPACING":
                        mEditViewListener.onSpacingListener();
                        break;
                    case "BLUR":
                        mEditViewListener.onBlurListener();
                        break;
                    default:
                        mEditViewListener.onFormatListener();
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTxtName;
        View mView;

        ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imgView);
            mTxtName = itemView.findViewById(R.id.txtName);
            mView = itemView.findViewById(R.id.viewParent);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
        }
    }


    public interface EditViewListener {
        void onFormatListener();

        void onFontListener();

        void onTextSizeListener();

        void onColorListener();

        void onShadowListener();

        void onStrokeListener();

        void onHighLightListener();

        void onSpacingListener();

        void onBlurListener();

    }
}
