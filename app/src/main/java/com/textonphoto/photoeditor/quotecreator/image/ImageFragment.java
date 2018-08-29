package com.textonphoto.photoeditor.quotecreator.image;

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

public class ImageFragment extends Fragment {
    private ImageView imageViewPicture;
    private ImageView imageViewCamera;
    private ImageView imageViewGallery;

    private OnImageListener onImageListener;

    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment newInstance() {
        ImageFragment fragment = new ImageFragment();
        return fragment;
    }

    public void setImageListener(OnImageListener onImageListener) {
        this.onImageListener = onImageListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image, container, false);

        imageViewPicture = view.findViewById(R.id.imageViewPicture);
        imageViewCamera = view.findViewById(R.id.imageViewCamera);
        imageViewGallery = view.findViewById(R.id.imageViewGallery);
        imageViewPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "show picture", Toast.LENGTH_SHORT).show();
                if (onImageListener != null) {
                    onImageListener.onImageBackgroundChoose();
                }
            }
        });

        imageViewCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "show camera", Toast.LENGTH_SHORT).show();
                if (onImageListener != null) {
                    onImageListener.onImageCameraChoose();
                }
            }
        });

        imageViewGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "show gallery", Toast.LENGTH_SHORT).show();
                if (onImageListener != null) {
                    onImageListener.onImageGalleryChoose();
                }
            }
        });
        return view;
    }


    static public interface OnImageListener {
        void onImageBackgroundChoose();
        void onImageCameraChoose();
        void onImageGalleryChoose();
    }
}
