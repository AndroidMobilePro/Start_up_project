package com.textonphoto.photoeditor.quotecreator.stickers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.textonphoto.photoeditor.quotecreator.R;

import java.util.ArrayList;

public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.ViewHolder> {
    private static final ArrayList<String> backgrounds = new ArrayList<>();

    private Context context;
    private LayoutInflater inflater;
    private OnStickerClickListener onStickerClickListener;

    public StickerAdapter(@NonNull Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sticker_item_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
//            fontsPickerView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (onTextPickerClickListener != null) {
//                        onTextPickerClickListener.onTextPickerClickListener(fonts.get(getAdapterPosition()));
//                    }
//                }
//            });
        }
    }

    public void setStickerListener(OnStickerClickListener onStickerClickListener) {
        this.onStickerClickListener = onStickerClickListener;
    }

    public interface OnStickerClickListener {
        void onStickerClickListener(String textContent);
    }
}
