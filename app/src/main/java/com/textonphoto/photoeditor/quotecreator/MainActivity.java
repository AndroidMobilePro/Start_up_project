package com.textonphoto.photoeditor.quotecreator;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.textonphoto.photoeditor.quotecreator.background.BackgroundFragment;
import com.textonphoto.photoeditor.quotecreator.brush.BrushFragment;
import com.textonphoto.photoeditor.quotecreator.emoji.EmojiFragment;
import com.textonphoto.photoeditor.quotecreator.filter.FilterFragment;
import com.textonphoto.photoeditor.quotecreator.fonts.AddTextAdapter;
import com.textonphoto.photoeditor.quotecreator.fonts.AddTextFragment;
import com.textonphoto.photoeditor.quotecreator.fonts.FontsFragment;
import com.textonphoto.photoeditor.quotecreator.image.ImageFragment;
import com.textonphoto.photoeditor.quotecreator.stickers.StickersFragment;
import com.textonphoto.photoeditor.quotecreator.views.PhotoEditorView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements EditingToolsAdapter.OnItemSelected, EditingToolssAdapter.OnItemSelected, AddTextAdapter.EditViewListener {

    private boolean isUpBackgroud = true;
    private boolean isUpBrush = true;
    private boolean isUpText = true;
    private boolean isUpFilter = true;
    private boolean isUpEmoij = true;
    private boolean isUpSticker = true;
    private boolean isUpEraser = true;

    //    private View viewTextBottom;
    private ImageView addText;
    private PhotoEditorView drawView;

    private RecyclerView lineRow;

    private View viewBottom;
    private View viewBottomAddText;

    private EditingToolsAdapter mEditingToolsAdapter = new EditingToolsAdapter(this);

    private ToolType currentToolType = ToolType.BACKGROUND;
    //Setup add text fragment
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private View llSave;
    private View viewHome;

    private void setupTabIcons() {

        int[] tabIcons = {
                R.drawable.ic_camera_2,
                R.drawable.ic_gallery,
                R.drawable.ic_brush,
                R.drawable.ic_text,
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
        tabLayout.getTabAt(6).setIcon(tabIcons[6]);

//        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabOne.setText("ONE");
//        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_favourite, 0, 0);
//        tabLayout.getTabAt(0).setCustomView(tabOne);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ImageFragment(), "Canvas");
        adapter.addFragment(new BackgroundFragment(), "Background");
        adapter.addFragment(new BrushFragment(), "Brush");
        adapter.addFragment(new FontsFragment(), "Text");
        adapter.addFragment(new FilterFragment(), "Filter");
        adapter.addFragment(new EmojiFragment(), "Emoji");
        adapter.addFragment(new StickersFragment(), "Sticker");
        viewPager.setAdapter(adapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_temp);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        llSave = findViewById(R.id.llSave);
        llSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        viewHome = findViewById(R.id.viewHome);
        viewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        fragmentManager = getSupportFragmentManager();

        addText = findViewById(R.id.addText);
        addText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
            }
        });

        viewBottom = findViewById(R.id.frContainer);
        viewBottomAddText = findViewById(R.id.frContainerAddText);

        drawView = findViewById(R.id.drawView);

        drawView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (currentToolType) {
                    case BACKGROUND:
                        if (isUpText) {
                            slideDown(lineRow);
                            viewBottom.setVisibility(View.VISIBLE);
                            slideUp(viewBottom);
                            isUpText = false;
                        } else {
                            slideDown(viewBottom);
                            viewBottom.setVisibility(GONE);
                            slideUp(lineRow);
                            isUpText = true;
                        }
                        break;
                    case BRUSH:
                        if (isUpText) {
                            slideDown(lineRow);
                            viewBottom.setVisibility(View.VISIBLE);
                            slideUp(viewBottom);
                            isUpText = false;
                        } else {
                            slideDown(viewBottom);
                            viewBottom.setVisibility(GONE);
                            slideUp(lineRow);
                            isUpText = true;
                        }
                        break;
                    case TEXT:
                        if (isUpText) {
                            slideDown(lineRow);
                            viewBottomAddText.setVisibility(View.VISIBLE);
                            slideUp(viewBottomAddText);
                            isUpText = false;
                        } else {
                            slideDown(viewBottomAddText);
                            viewBottomAddText.setVisibility(GONE);
                            slideUp(lineRow);
                            isUpText = true;
                        }
                        break;
                    case FILTER:
                        if (isUpText) {
                            slideDown(lineRow);
                            viewBottom.setVisibility(View.VISIBLE);
                            slideUp(viewBottom);
                            isUpText = false;
                        } else {
                            slideDown(viewBottom);
                            viewBottom.setVisibility(GONE);
                            slideUp(lineRow);
                            isUpText = true;
                        }
                        break;
                    case EMOJI:
                        if (isUpText) {
                            slideDown(lineRow);
                            viewBottom.setVisibility(View.VISIBLE);
                            slideUp(viewBottom);
                            isUpText = false;
                        } else {
                            slideDown(viewBottom);
                            viewBottom.setVisibility(GONE);
                            slideUp(lineRow);
                            isUpText = true;
                        }
                        break;
                    case STICKER:
                        if (isUpText) {
                            slideDown(lineRow);
                            viewBottom.setVisibility(View.VISIBLE);
                            slideUp(viewBottom);
                            isUpText = false;
                        } else {
                            slideDown(viewBottom);
                            viewBottom.setVisibility(GONE);
                            slideUp(lineRow);
                            isUpText = true;
                        }
                        break;
                    case ERASER:
                        if (isUpText) {
                            slideDown(lineRow);
                            viewBottom.setVisibility(View.VISIBLE);
                            slideUp(viewBottom);
                            isUpText = false;
                        } else {
                            slideDown(viewBottom);
                            viewBottom.setVisibility(GONE);
                            slideUp(lineRow);
                            isUpText = true;
                        }
                        break;

                }
            }
        });

        setLineToolBottom();
        setBackgroundFragment();
        setFontsFragment();
    }

    private void setLineToolBottom() {
        lineRow = findViewById(R.id.lineTools);
        LinearLayoutManager llmTools = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        lineRow.setLayoutManager(llmTools);
        lineRow.setAdapter(mEditingToolsAdapter);

    }

    private void setAddTextFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        AddTextFragment addTextFragment = AddTextFragment.newInstance();
        fragmentTransaction.replace(R.id.frContainer, addTextFragment);
        fragmentTransaction.commit();
    }

    FontsFragment fontsFragment;

    private void setFontsFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fontsFragment = FontsFragment.newInstance();
        fragmentTransaction.replace(R.id.frContainerAddText, fontsFragment);
        fragmentTransaction.commit();
//        fontsFragment.setOnFontListener(new FontsFragment.OnFontListener() {
//            @Override
//            public void onFontChoose(Typeface typeface) {
//                if (viewBottom.getVisibility() == VISIBLE) {
//                    Toast.makeText(MainActivity.this, "Type", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    private void setBackgroundFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        BackgroundFragment backgroundFragment = BackgroundFragment.newInstance();
        fragmentTransaction.replace(R.id.frContainer, backgroundFragment);
        fragmentTransaction.commit();
    }

    private void setBrushFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        BrushFragment brushFragment = BrushFragment.newInstance();
        fragmentTransaction.replace(R.id.frContainer, brushFragment);
        fragmentTransaction.commit();
    }

    private void setEmojiFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        EmojiFragment emojiFragment = EmojiFragment.newInstance();
        fragmentTransaction.replace(R.id.frContainer, emojiFragment);
        fragmentTransaction.commit();
    }

    private void setStickersFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        StickersFragment stickersFragment = StickersFragment.newInstance();
        fragmentTransaction.replace(R.id.frContainer, stickersFragment);
        fragmentTransaction.commit();
    }

    private void setFilterFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        FilterFragment filterFragment = FilterFragment.newInstance();
        fragmentTransaction.replace(R.id.frContainer, filterFragment);
        fragmentTransaction.commit();
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
        view.setVisibility(GONE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    // slide the view from its current position to below itself
    public void slideDown(View view, boolean check) {
        if (check) {
            view.setVisibility(GONE);
            TranslateAnimation animate = new TranslateAnimation(
                    0,                 // fromXDelta
                    0,                 // toXDelta
                    0,                 // fromYDelta
                    view.getHeight()); // toYDelta
            animate.setDuration(500);
            animate.setFillAfter(true);
            view.startAnimation(animate);
        } else {
//            view.setVisibility(GONE);
            TranslateAnimation animate = new TranslateAnimation(
                    0,                 // fromXDelta
                    0,                 // toXDelta
                    0,                 // fromYDelta
                    view.getHeight()); // toYDelta
            animate.setDuration(500);
            animate.setFillAfter(true);
            view.startAnimation(animate);
        }
    }

    @Override
    public void onToolSelected(ToolType toolType) {
        currentToolType = toolType;
        switch (toolType) {
            case BACKGROUND:
//                setBackgroundFragment();
                if (isUpText) {
                    slideDown(lineRow);
                    slideUp(viewBottom);
                    isUpText = false;
                } else {
                    slideDown(viewBottom);
                    slideUp(lineRow);
                    isUpText = true;
                    viewBottom.clearFocus();
                }
                break;
            case TEXT:
//                setFontsFragment();
                isUpText = true;
                if (isUpText) {
                    slideDown(lineRow);
                    slideUp(viewBottomAddText);
                    isUpText = false;
                } else {
                    slideDown(viewBottomAddText);
                    slideUp(lineRow);
                    isUpText = true;
                    if (getSupportFragmentManager().findFragmentById(R.id.frContainerAddText) != null) {
                        getSupportFragmentManager()
                                .beginTransaction().
                                remove(getSupportFragmentManager().findFragmentById(R.id.frContainerAddText)).commit();
                    }
                    viewBottomAddText.clearFocus();
                }
                break;
            case BRUSH:
                setBrushFragment();
                isUpText = true;
                if (isUpText) {
                    slideDown(lineRow);
                    slideUp(viewBottom);
                    isUpText = false;
                } else {
                    slideDown(viewBottom);
                    slideUp(lineRow);
                    isUpText = true;
                    viewBottom.clearFocus();
                }

                break;
            case EMOJI:
                setEmojiFragment();
                isUpText = true;
                if (isUpText) {
                    slideDown(lineRow);
                    slideUp(viewBottom);
                    isUpText = false;
                } else {
                    slideDown(viewBottom);
                    slideUp(lineRow);
                    isUpText = true;
                }

                break;
            case ERASER:
                isUpText = true;
                if (isUpText) {
                    slideDown(lineRow);
                    slideUp(viewBottom);
                    isUpText = false;
                } else {
                    slideDown(viewBottom);
                    slideUp(lineRow);
                    isUpText = true;
                }

                break;
            case FILTER:
                setFilterFragment();
                isUpText = true;
                if (isUpText) {
                    slideDown(lineRow);
                    viewBottom.setVisibility(View.VISIBLE);
                    slideUp(viewBottom);
                    isUpText = false;
                } else {
                    slideDown(viewBottom);
                    viewBottom.setVisibility(GONE);
                    slideUp(lineRow);
                    isUpText = true;
                }

                break;
            case STICKER:
                setStickersFragment();
                isUpText = true;
                if (isUpText) {
                    slideDown(lineRow);
                    viewBottom.setVisibility(View.VISIBLE);
                    slideUp(viewBottom);
                    isUpText = false;
                } else {
                    slideDown(viewBottom);
                    viewBottom.setVisibility(GONE);
                    slideUp(lineRow);
                    isUpText = true;
                }

                break;

        }
    }

//    @Override
//    public void onBackPressed() {
//        if (isUp) {
//            slideDown(lineRow);
//            viewBottom.setVisibility(View.VISIBLE);
//            slideUp(viewBottom);
//            isUp = false;
//        } else {
//            slideDown(viewBottom);
//            viewBottom.setVisibility(GONE);
//            slideUp(lineRow);
//            isUp = true;
//        }
//    }

    @Override
    public void onFormatListener() {

    }

    @Override
    public void onFontListener() {

    }

    @Override
    public void onTextSizeListener() {

    }

    @Override
    public void onColorListener() {

    }

    @Override
    public void onShadowListener() {

    }

    @Override
    public void onStrokeListener() {

    }

    @Override
    public void onHighLightListener() {

    }

    @Override
    public void onSpacingListener() {

    }

    @Override
    public void onBlurListener() {

    }
}
