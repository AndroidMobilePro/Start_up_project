package com.textonphoto.photoeditor.quotecreator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements EditingToolsAdapter.OnItemSelected, EditingToolssAdapter.OnItemSelected, EditViewAdapter.EditViewListener {

    private boolean isUp = true;
    private boolean isUps = true;
    private View myView;
    private View myBackgroundView;

    private View viewBackground;
    private View viewText;
    private View viewSticker;
    private View viewEffect;

    private View viewTextBottom;
    private View llTextFuncBottom;
    private ImageView down;

    private RecyclerView lineRow;
    private RecyclerView lineTools;
    private RecyclerView addRecyclerView;

    private EditingToolsAdapter mEditingToolsAdapter = new EditingToolsAdapter(this);
    private EditingToolssAdapter mEditingToolssAdapter = new EditingToolssAdapter(this);

    private EditViewAdapter mEditViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = findViewById(R.id.llBottom);
        myBackgroundView = findViewById(R.id.llBackgroundBottom);

//        viewBackground = findViewById(R.id.containerBackgroud);
//        viewText = findViewById(R.id.containerText);
//        viewSticker = findViewById(R.id.containerSticker);
//        viewEffect = findViewById(R.id.containerEffect);

        viewTextBottom = findViewById(R.id.llTextBottom);
        llTextFuncBottom = findViewById(R.id.llTextFuncBottom);
        lineRow = (RecyclerView) findViewById(R.id.lineRow);
        lineTools = (RecyclerView) findViewById(R.id.lineTools);

        down = findViewById(R.id.down);

        LinearLayoutManager llmTools = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        lineRow.setLayoutManager(llmTools);
        lineRow.setAdapter(mEditingToolsAdapter);

        LinearLayoutManager llmToolss = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        lineTools.setLayoutManager(llmToolss);
        lineTools.setAdapter(mEditingToolssAdapter);

        lineRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myView.getVisibility() != VISIBLE) {
                    if (isUp) {
                        slideDown(myView);
                        slideUp(llTextFuncBottom);
                    } else {
                        slideDown(llTextFuncBottom);
                        slideUp(myView);
                    }
                    isUp = !isUp;
                }
            }
        });

        //Setup the color picker for text color
        addRecyclerView = findViewById(R.id.add_picker_recycler_view);
        LinearLayoutManager layoutEditManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        addRecyclerView.setLayoutManager(layoutEditManager);
        addRecyclerView.setHasFixedSize(true);
        mEditViewAdapter = new EditViewAdapter(this, this);
        //This listener will change the text fonts when clicked on any fonts from picker
        addRecyclerView.setAdapter(mEditViewAdapter);
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

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//        }
//    }

    @Override
    public void onToolSelected(ToolType toolType) {
        switch (toolType) {
            case BACKGROUND:
                if (isUp) {
                    slideDown(myView);
                    llTextFuncBottom.setVisibility(View.VISIBLE);
                    slideUp(llTextFuncBottom);
                } else {
                    slideDown(llTextFuncBottom);
                    down.setVisibility(GONE);
                    llTextFuncBottom.setVisibility(GONE);
                    slideUp(myView);
                }
                isUp = !isUp;
                break;
            case TEXT:

                break;
            case BRUSH:

                break;
            case EMOJI:

                break;
            case ERASER:

                break;
            case FILTER:

                break;
            case STICKER:

                break;

        }
    }

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

    @Override
    public void onBackPressed() {
        if (isUp) {
            slideDown(myView);
            llTextFuncBottom.setVisibility(View.VISIBLE);
            slideUp(llTextFuncBottom);
        } else {
            slideDown(llTextFuncBottom);
            down.setVisibility(GONE);
            llTextFuncBottom.setVisibility(GONE);
            slideUp(myView);
        }
        isUp = !isUp;
    }
}
