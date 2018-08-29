package com.textonphoto.photoeditor.quotecreator;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.textonphoto.photoeditor.quotecreator.background.BackgroundFragment;
import com.textonphoto.photoeditor.quotecreator.base.BaseActivity;
import com.textonphoto.photoeditor.quotecreator.brush.BrushFragment;
import com.textonphoto.photoeditor.quotecreator.constants.Contants;
import com.textonphoto.photoeditor.quotecreator.edit_text.BlurFragment;
import com.textonphoto.photoeditor.quotecreator.edit_text.ColorFragment;
import com.textonphoto.photoeditor.quotecreator.edit_text.FormatFragment;
import com.textonphoto.photoeditor.quotecreator.edit_text.HighLightFragment;
import com.textonphoto.photoeditor.quotecreator.edit_text.ShadowFragment;
import com.textonphoto.photoeditor.quotecreator.edit_text.SpacingFragment;
import com.textonphoto.photoeditor.quotecreator.edit_text.TextSizeFragment;
import com.textonphoto.photoeditor.quotecreator.emoji.EmojiFragment;
import com.textonphoto.photoeditor.quotecreator.filter.FilterFragment;
import com.textonphoto.photoeditor.quotecreator.edit_text.FontsFragment;
import com.textonphoto.photoeditor.quotecreator.image.ImageFragment;
import com.textonphoto.photoeditor.quotecreator.stickers.StickersFragment;
import com.textonphoto.photoeditor.quotecreator.utils.BitmapUtils;
import com.textonphoto.photoeditor.quotecreator.views.OnPhotoEditorListener;
import com.textonphoto.photoeditor.quotecreator.views.PhotoEditor;
import com.textonphoto.photoeditor.quotecreator.views.PhotoEditorView;
import com.textonphoto.photoeditor.quotecreator.views.SaveSettings;
import com.textonphoto.photoeditor.quotecreator.views.ViewType;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainActivity extends BaseActivity implements OnPhotoEditorListener, ImageFragment.OnImageListener {

    private static final int CAMERA_REQUEST = 52;
    private static final int PICK_REQUEST = 53;

    private ImageView addText;
    private PhotoEditor mPhotoEditor;
    private PhotoEditorView mPhotoEditorView;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private View containerTextTabs;
    private TabLayout textTabLayout;
    private ViewPager textViewPager;

    private View back;

    private View llSave;
    private View viewHome;

    private ConstraintLayout mRootView;

    private void setupTabIcons() {

        int[] tabIcons = {
                R.drawable.ic_camera_2,
                R.drawable.ic_gallery,
                R.drawable.ic_brush,
                R.drawable.ic_photo_filter,
                R.drawable.ic_insert_emoticon,
                R.drawable.ic_sticker,
        };

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
        tabLayout.getTabAt(5).setIcon(tabIcons[5]);

//        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabOne.setText("ONE");
//        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_favourite, 0, 0);
//        tabLayout.getTabAt(0).setCustomView(tabOne);
    }


    private void setupViewPager(ViewPager viewPager) {
        ImageFragment imageFragment = new ImageFragment();
        imageFragment.setImageListener(this);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(imageFragment, "Canvas");
        adapter.addFragment(new BackgroundFragment(), "Background");
        adapter.addFragment(new BrushFragment(), "Brush");
        adapter.addFragment(new FilterFragment(), "Filter");
        adapter.addFragment(new EmojiFragment(), "Emoji");
        adapter.addFragment(new StickersFragment(), "Sticker");
        viewPager.setAdapter(adapter);
    }

    private void setupTextTabIcons() {

        int[] tabIcons = {
                R.drawable.ic_camera_2,
                R.drawable.ic_gallery,
                R.drawable.ic_brush,
                R.drawable.ic_text,
                R.drawable.ic_photo_filter,
                R.drawable.ic_insert_emoticon,
                R.drawable.ic_sticker,
        };

        textTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        textTabLayout.getTabAt(1).setIcon(tabIcons[1]);
        textTabLayout.getTabAt(2).setIcon(tabIcons[2]);
        textTabLayout.getTabAt(3).setIcon(tabIcons[3]);
        textTabLayout.getTabAt(4).setIcon(tabIcons[4]);
        textTabLayout.getTabAt(5).setIcon(tabIcons[5]);
        textTabLayout.getTabAt(6).setIcon(tabIcons[6]);
        textTabLayout.getTabAt(7).setIcon(tabIcons[6]);

//        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabOne.setText("ONE");
//        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_favourite, 0, 0);
//        tabLayout.getTabAt(0).setCustomView(tabOne);
    }


    private void setupTextViewPager(ViewPager viewPager) {
        ImageFragment imageFragment = new ImageFragment();
        imageFragment.setImageListener(this);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FormatFragment(), "FORMAT");
        adapter.addFragment(new FontsFragment(), "FONT");
        adapter.addFragment(new TextSizeFragment(), "TEXT SIZE");
        adapter.addFragment(new ColorFragment(), "COLOR");
        adapter.addFragment(new ShadowFragment(), "SHADOW");
        adapter.addFragment(new SpacingFragment(), "SPACING");
        adapter.addFragment(new BlurFragment(), "BLUR");
        adapter.addFragment(new HighLightFragment(), "HIGHLIGHT");


        viewPager.setAdapter(adapter);
    }

    private static Typeface mTypeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_temp);

        initViews();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        back = findViewById(R.id.back);
        containerTextTabs = findViewById(R.id.containerTextTabs);

        textViewPager = (ViewPager) findViewById(R.id.textViewpager);
        setupTextViewPager(textViewPager);

        textTabLayout = (TabLayout) findViewById(R.id.textTabs);
        textTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        textTabLayout.setupWithViewPager(textViewPager);
        setupTextTabIcons();

        llSave = findViewById(R.id.llSave);
        llSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImage();
            }
        });

        viewHome = findViewById(R.id.viewHome);
        viewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
            }
        });
        mTypeface = Typeface.createFromAsset(getAssets(),
                Contants.folderFontPath + "AbrilFatface-Regular.ttf");
        addText = findViewById(R.id.addText);
        addText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, MainActivity.class));
//                finish();
                mPhotoEditor.addText(mTypeface, "Tap to input text", getResources().getColor(R.color.red_color_picker), 30);
            }
        });

        mPhotoEditor = new PhotoEditor.Builder(this, mPhotoEditorView)
                .setPinchTextScalable(true) // set flag to make text scalable when pinch
                //.setDefaultTextTypeface(mTextRobotoTf)
                //.setDefaultEmojiTypeface(mEmojiTypeFace)
                .build(); // build photo editor sdk

        mPhotoEditor.setOnPhotoEditorListener(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                containerTextTabs.setVisibility(View.INVISIBLE);
                containerTextTabs.setVisibility(View.INVISIBLE);
                tabLayout.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.VISIBLE);
            }
        });

        //loadType();
    }

    private void initViews() {
        mRootView = findViewById(R.id.rootView);
        mPhotoEditorView = findViewById(R.id.photoEditorView);
    }

    private String key = "1";

    private void loadType() {
        switch (key) {
            case "1":
                Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.got_s);
                Bitmap imageScale = Bitmap.createScaledBitmap(image, 300, 300, false);
                mPhotoEditor.addImage(imageScale);
                break;
            case "2":
                mPhotoEditorView.getSource().setImageResource(R.drawable.got);
                break;
            case "3":
                break;
            default:
                break;
        }
    }

    // slide the view from below itself to the current position
    public void slideUp(View view) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    // slide the view from its current position to below itself
    public void slideDown(View view) {
        view.setVisibility(View.GONE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    @Override
    public void onImageChangeListener(View rootView, Bitmap bitmap) {

    }

    @Override
    public void onEditTextChangeListener(View rootView, String text, int colorCode) {
        Log.d("TAGG", "text");
//        mPhotoEditor.editText(rootView, mTypeface, "Change", R.color.red_color_picker, 50);
        containerTextTabs.setVisibility(View.VISIBLE);
        textViewPager.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);
        viewPager.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onEditTextChangeListener(View rootView, String text, int colorCode, Typeface typeFace, int textSize) {
        Log.d("TAGG", "text");
//        mPhotoEditor.editText(rootView, mTypeface, "Change", R.color.red_color_picker, 50);
        containerTextTabs.setVisibility(View.VISIBLE);
        textViewPager.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);
        viewPager.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onAddViewListener(ViewType viewType, int numberOfAddedViews) {

    }

    @Override
    public void onRemoveViewListener(int numberOfAddedViews) {

    }

    @Override
    public void onRemoveViewListener(ViewType viewType, int numberOfAddedViews) {

    }

    @Override
    public void onStartViewChangeListener(ViewType viewType) {

    }

    @Override
    public void onStopViewChangeListener(ViewType viewType) {

    }

    @SuppressLint("MissingPermission")
    private void saveImage() {
        if (requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            showLoading("Saving...");
            String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/addTextapp";

//            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/photoEditor"
//                    + System.currentTimeMillis() + ".png");

            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + ""
                    + System.currentTimeMillis() + ".png");
            Log.d("TABBBB", file.getAbsolutePath());
            try {
                file.createNewFile();

                SaveSettings saveSettings = new SaveSettings.Builder()
                        .setClearViewsEnabled(true)
                        .setTransparencyEnabled(true)
                        .build();

                mPhotoEditor.saveAsFile(file.getAbsolutePath(), saveSettings, new PhotoEditor.OnSaveListener() {
                    @Override
                    public void onSuccess(@NonNull final String imagePath) {
                        hideLoading();
//                        showSnackbar("Image Saved Successfully");
                        Log.d("TABBBB", imagePath);
                        mPhotoEditorView.getSource().setImageURI(Uri.fromFile(new File(imagePath)));
                        mPhotoEditorView.getSource().buildDrawingCache();
                        Bitmap bmap = mPhotoEditorView.getSource().getDrawingCache();
                        saveImageToGallery(bmap);

//                        if (!TextUtils.isEmpty(imagePath)) {
//                            Snackbar snackbar = Snackbar
//                                    .make(mRootView, "Image saved to gallery!", Snackbar.LENGTH_LONG)
//                                    .setAction("OPEN", new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            openImage(imagePath);
//                                        }
//                                    });
//
//                            snackbar.show();
//                        } else {
//                            Snackbar snackbar = Snackbar
//                                    .make(mRootView, "Unable to save image!", Snackbar.LENGTH_LONG);
//
//                            snackbar.show();
//                        }
                    }

                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        hideLoading();
                        showSnackbar("Failed to save Image");
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                hideLoading();
                showSnackbar(e.getMessage());
            }
        }
    }

//    @SuppressLint("MissingPermission")
//    private void saveImage() {
//        if (requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//            showLoading("Saving...");
//            File file = new File(Environment.getExternalStorageDirectory()
//                    + File.separator + ""
//                    + System.currentTimeMillis() + ".png");
//            try {
//                file.createNewFile();
//
//                SaveSettings saveSettings = new SaveSettings.Builder()
//                        .setClearViewsEnabled(true)
//                        .setTransparencyEnabled(true)
//                        .build();
//
//                mPhotoEditor.saveAsFile(file.getAbsolutePath(), saveSettings, new PhotoEditor.OnSaveListener() {
//                    @Override
//                    public void onSuccess(@NonNull final String imagePath) {
//                        hideLoading();
//                        //addImageToGallery(imagePath);
////                        showSnackbar("Image Saved Successfully");
//                        mPhotoEditorView.getSource().setImageURI(Uri.fromFile(new File(imagePath)));
//                        if (!TextUtils.isEmpty(imagePath)) {
//                            Snackbar snackbar = Snackbar
//                                    .make(mRootView, "Image saved to gallery!", Snackbar.LENGTH_LONG)
//                                    .setAction("OPEN", new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            openImage(imagePath);
//                                        }
//                                    });
//
//                            snackbar.show();
//                        } else {
//                            Snackbar snackbar = Snackbar
//                                    .make(mRootView, "Unable to save image!", Snackbar.LENGTH_LONG);
//
//                            snackbar.show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(@NonNull Exception exception) {
//                        hideLoading();
//                        showSnackbar("Failed to save Image");
//                    }
//                });
//            } catch (IOException e) {
//                e.printStackTrace();
//                hideLoading();
//                showSnackbar(e.getMessage());
//            }
//        }
//    }

    /*
   * saves image to camera gallery
   * */
    private void saveImageToGallery(final Bitmap finalImage) {
        Dexter.withActivity(this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            final String path = BitmapUtils.insertImage(getContentResolver(), finalImage, System.currentTimeMillis() + "_profile.jpg", null);
                            if (!TextUtils.isEmpty(path)) {
                                Snackbar snackbar = Snackbar
                                        .make(mRootView, "Image saved to gallery!", Snackbar.LENGTH_LONG)
                                        .setAction("OPEN", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                openImage(path);
                                            }
                                        });

                                snackbar.show();
                            } else {
                                Snackbar snackbar = Snackbar
                                        .make(mRootView, "Unable to save image!", Snackbar.LENGTH_LONG);

                                snackbar.show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Permissions are not granted!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }

    public void addImageToGallery(final String filePath) {

        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.MediaColumns.DATA, filePath);

        getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }

    // opening image in default image viewer app
    private void openImage(String path) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(path), "image/*");
        startActivity(intent);
    }

    @Override
    public void isPermissionGranted(boolean isGranted, String permission) {
        if (isGranted) {
            saveImage();
        }
    }

    private void showSaveDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you want to exit without saving image ?");
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveImage();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNeutralButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create().show();

    }

    /**
     * Image fragment listener
     **/
    @Override
    public void onImageBackgroundChoose() {

    }

    @Override
    public void onImageCameraChoose() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    public void onImageGalleryChoose() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAMERA_REQUEST:
                    mPhotoEditor.clearAllViews();
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    if (key.equals("1")) {
                        mPhotoEditorView.getSource().setImageBitmap(photo);
                    } else {
                        mPhotoEditor.addImage(photo);
                    }

                    break;
                case PICK_REQUEST:
                    try {
                        mPhotoEditor.clearAllViews();
                        Uri uri = data.getData();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        if (key.equals("1")) {
                            mPhotoEditorView.getSource().setImageBitmap(bitmap);
                        } else {
                            mPhotoEditor.addImage(bitmap);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
