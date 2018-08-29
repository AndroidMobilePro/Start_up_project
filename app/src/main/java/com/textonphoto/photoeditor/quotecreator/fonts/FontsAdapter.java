package com.textonphoto.photoeditor.quotecreator.fonts;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.textonphoto.photoeditor.quotecreator.R;
import com.textonphoto.photoeditor.quotecreator.constants.Contants;

import java.io.IOException;
import java.util.ArrayList;

public class FontsAdapter extends RecyclerView.Adapter<FontsAdapter.ViewHolder> {
    private static final ArrayList<String> fonts = new ArrayList<>();

    private Context context;
    private LayoutInflater inflater;
    private OnTextPickerClickListener onTextPickerClickListener;

    public FontsAdapter(@NonNull Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        final AssetManager assetManager = context.getAssets();
        try {
            String[] filelistInSubfolder = assetManager.list(Contants.folderFont);
            for (int i = 0; i < filelistInSubfolder.length; i++) {
                // Get filename of file or directory
                String filename = filelistInSubfolder[i];
                fonts.add(filename);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fonts_picker_item_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.fontsPickerView.setTypeface(Typeface.createFromAsset(context.getAssets(),
                Contants.folderFontPath + fonts.get(position)));
        holder.fontsPickerView.setText("AA");
    }

    @Override
    public int getItemCount() {
        return fonts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView fontsPickerView;

        public ViewHolder(final View itemView) {
            super(itemView);
            fontsPickerView = itemView.findViewById(R.id.fonts_picker_view);
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

    public void setFontsPickerListener(OnTextPickerClickListener onTextPickerClickListener) {
        this.onTextPickerClickListener = onTextPickerClickListener;
    }

    public interface OnTextPickerClickListener {
        void onTextPickerClickListener(String textContent);
    }
}
