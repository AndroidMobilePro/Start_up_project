package com.textonphoto.photoeditor.quotecreator.emoji;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.textonphoto.photoeditor.quotecreator.R;
import com.textonphoto.photoeditor.quotecreator.views.PhotoEditor;

import java.util.ArrayList;

public class EmojiAdapters extends RecyclerView.Adapter<EmojiAdapters.ViewHolder> {


    ArrayList<String> emojisList = new ArrayList<>();

    public EmojiAdapters(Context context) {
        emojisList = PhotoEditor.getEmojis(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_emoji_s, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtEmoji.setText(emojisList.get(position));
    }

    @Override
    public int getItemCount() {
        return emojisList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtEmoji;

        ViewHolder(View itemView) {
            super(itemView);
            txtEmoji = itemView.findViewById(R.id.txtEmoji);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }
}