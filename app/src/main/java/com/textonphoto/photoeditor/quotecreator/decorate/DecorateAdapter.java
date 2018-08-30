package com.textonphoto.photoeditor.quotecreator.decorate;

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

public class DecorateAdapter extends RecyclerView.Adapter<DecorateAdapter.ViewHolder> {
    private static final ArrayList<String> backgrounds = new ArrayList<>();

    private Context context;
    private LayoutInflater inflater;
    private OnBackgroundClickListener onBackgroundClickListener;

    private String title[] = new String[]{"Ảnh", "Sticker", "Nhãn", "Ảnh bìa", "Vẽ"};

    public DecorateAdapter(@NonNull Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.decorate_item_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgDecorate);
            textView = itemView.findViewById(R.id.tvTitleDecorate);
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

    public void setBackgroundListener(OnBackgroundClickListener onBackgroundClickListener) {
        this.onBackgroundClickListener = onBackgroundClickListener;
    }

    public interface OnBackgroundClickListener {
        void onBackgroundClickListener(String textContent);
    }
}
