package com.textonphoto.photoeditor.quotecreator.stickers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private OnStickerClickListener mStickerListener;


    int[] stickerList = new int[]{R.drawable.aa, R.drawable.bb,
            R.drawable.aa, R.drawable.bb, R.drawable.aa, R.drawable.bb,
            R.drawable.aa, R.drawable.bb, R.drawable.aa, R.drawable.bb, R.drawable.aa,
            R.drawable.bb, R.drawable.aa, R.drawable.bb, R.drawable.aa, R.drawable.bb,
            R.drawable.aa, R.drawable.bb};


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
        holder.imageView.setImageResource(stickerList[position]);
    }

    @Override
    public int getItemCount() {
        return stickerList.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mStickerListener != null) {
                        mStickerListener.onStickerClick(
                                BitmapFactory.decodeResource(itemView.getContext().getResources(),
                                        stickerList[getLayoutPosition()]));
                    }
                }
            });
        }
    }

    public void setStickerListener(OnStickerClickListener onStickerClickListener) {
        this.mStickerListener = onStickerClickListener;
    }

    public interface OnStickerClickListener {
        void onStickerClick(Bitmap bitmap);
    }
}
