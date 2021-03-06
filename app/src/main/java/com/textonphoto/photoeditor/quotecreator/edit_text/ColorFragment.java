package com.textonphoto.photoeditor.quotecreator.edit_text;

import android.graphics.BlurMaskFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.textonphoto.photoeditor.quotecreator.R;
import com.textonphoto.photoeditor.quotecreator.color.ColorPickerDialogFragment;
import com.textonphoto.photoeditor.quotecreator.color.palettes.ArrayPalette;
import com.textonphoto.photoeditor.quotecreator.color.palettes.ColorFactory;
import com.textonphoto.photoeditor.quotecreator.color.palettes.ColorShadeFactory;
import com.textonphoto.photoeditor.quotecreator.color.palettes.CombinedColorFactory;
import com.textonphoto.photoeditor.quotecreator.color.palettes.FactoryPalette;
import com.textonphoto.photoeditor.quotecreator.color.palettes.Palette;
import com.textonphoto.photoeditor.quotecreator.color.palettes.RainbowColorFactory;
import com.textonphoto.photoeditor.quotecreator.color.palettes.RandomPalette;

import org.dmfs.android.retentionmagic.annotations.Retain;

import java.util.ArrayList;

public class ColorFragment extends Fragment implements  ColorPickerDialogFragment.ColorDialogResultListener {

    private RecyclerView recyclerView;
    private ColorPickerAdapter colorPickerAdapter;
    private ImageView showMore;

    int mColorCode;

    private OnBackgroundListener onBackgroundListener;

    private final static int[] COLORS = new int[]{
            0xff000000, 0xff0000ff, 0xff00ff00, 0xffff0000, 0xffffff00, 0xff00ffff, 0xffff00ff, 0xff404040,
            0xff808080, 0xff8080ff, 0xff80ff80, 0xffff8080, 0xffffff80, 0xff80ffff, 0xffff80ff, 0xffffffff};

    private final static int[] MATERIAL_COLORS_PRIMARY = {
            0xffe91e63, 0xfff44336, 0xffff5722, 0xffff9800, 0xffffc107, 0xffffeb3b, 0xffcddc39, 0xff8bc34a,
            0xff4caf50, 0xff009688, 0xff00bcd4, 0xff03a9f4, 0xff2196f3, 0xff3f51b5, 0xff673ab7, 0xff9c27b0};

    private static final int MATERIAL_COLORS_SECONDARY[] = {
            0xffad1457, 0xffc62828, 0xffd84315, 0xffef6c00, 0xffff8f00, 0xfff9a825, 0xff9e9d24, 0xff558b2f,
            0xff2e7d32, 0xff00695c, 0xff00838f, 0xff0277bd, 0xff1565c0, 0xff283593, 0xff4527a0, 0xff6a1b9a};

    @Retain(permanent = true, classNS = "DemoActivity")
    private String mSelectedPalette = null;

    public ColorFragment() {
        // Required empty public constructor
    }

    public static ColorFragment newInstance() {
        ColorFragment fragment = new ColorFragment();
        return fragment;
    }

    public void setBackgroudListener(OnBackgroundListener onBackgroundListener) {
        this.onBackgroundListener = onBackgroundListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_text_color, container, false);
        colorPickerAdapter = new ColorPickerAdapter(getActivity());

        recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutEditManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutEditManager);
        recyclerView.setHasFixedSize(true);
        //This listener will change the text fonts when clicked on any fonts
        colorPickerAdapter.setOnColorPickerClickListener(new ColorPickerAdapter.OnColorPickerClickListener() {
            @Override
            public void onColorPickerClickListener(int colorCode) {

            }
        });
        recyclerView.setAdapter(colorPickerAdapter);

        showMore = view.findViewById(R.id.showMore);
        showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickColor();
            }
        });
        return view;
    }

    @Override
    public void onColorChanged(int color, String paletteId, String colorName, String paletteName) {
        Log.d("TABBBB", color + "");
        mSelectedPalette = paletteId;
        mColorCode = color;
    }

    @Override
    public void onColorDialogCancelled() {

    }

    private void onClickColor() {
        ColorPickerDialogFragment d = new ColorPickerDialogFragment();
        d.setColorDialogResultListener(this);

        ArrayList<Palette> palettes = new ArrayList<Palette>();

        palettes.add(new ArrayPalette("material_primary", "Material Colors", MATERIAL_COLORS_PRIMARY, 4));
        palettes.add(new ArrayPalette("material_secondary", "Dark Material Colors", MATERIAL_COLORS_SECONDARY, 4));

        // add a palette from the resources
        palettes.add(ArrayPalette.fromResources(getActivity().getApplicationContext(), "base", R.string.base_palette_name, R.array.base_palette_colors, R.array.base_palette_color_names));

        palettes.add(new ArrayPalette("base2", "Base 2", COLORS));

        // Android Material color schema
        // Add a palette with rainbow colors
        palettes.add(new FactoryPalette("rainbow", "Rainbow", ColorFactory.RAINBOW, 16));

        // Add a palette with many darker rainbow colors
        palettes.add(new FactoryPalette("rainbow2", "Dirty Rainbow", new RainbowColorFactory(0.5f, 0.5f), 16));

        // Add a palette with pastel colors
        palettes.add(new FactoryPalette("pastel", "Pastel", ColorFactory.PASTEL, 16));

        // Add a palette with red+orange colors
        palettes.add(new FactoryPalette("red/orange", "Red/Orange", new CombinedColorFactory(ColorFactory.RED, ColorFactory.ORANGE), 16));

        // Add a palette with yellow+green colors
        palettes.add(new FactoryPalette("yellow/green", "Yellow/Green", new CombinedColorFactory(ColorFactory.YELLOW, ColorFactory.GREEN), 16));

        // Add a palette with cyan+blue colors
        palettes.add(new FactoryPalette("cyan/blue", "Cyan/Blue", new CombinedColorFactory(ColorFactory.CYAN, ColorFactory.BLUE), 16));

        // Add a palette with purple+pink colors
        palettes.add(new FactoryPalette("purble/pink", "Purple/Pink", new CombinedColorFactory(ColorFactory.PURPLE, ColorFactory.PINK), 16));

        // Add a palette with red colors
        palettes.add(new FactoryPalette("red", "Red", ColorFactory.RED, 16));
        // Add a palette with green colors
        palettes.add(new FactoryPalette("green", "Green", ColorFactory.GREEN, 16));
        // Add a palette with blue colors
        palettes.add(new FactoryPalette("blue", "Blue", ColorFactory.BLUE, 16));

        // Add a palette with few random colors
        palettes.add(new RandomPalette("random1", "Random 1", 1));
        // Add a palette with few random colors
        palettes.add(new RandomPalette("random4", "Random 4", 4));
        // Add a palette with few random colors
        palettes.add(new RandomPalette("random9", "Random 9", 9));
        // Add a palette with few random colors
        palettes.add(new RandomPalette("random16", "Random 16", 16));

        // Add a palette with random colors
        palettes.add(new RandomPalette("random25", "Random 25", 25));

        // Add a palette with many random colors
        palettes.add(new RandomPalette("random81", "Random 81", 81));

        // Add a palette with secondary colors
        palettes.add(new FactoryPalette("secondary1", "Secondary 1", new CombinedColorFactory(new ColorShadeFactory(18),
                new ColorShadeFactory(53), new ColorShadeFactory(80), new ColorShadeFactory(140)), 16, 4));

        // Add another palette with secondary colors
        palettes.add(new FactoryPalette("secondary2", "Secondary 2", new CombinedColorFactory(new ColorShadeFactory(210),
                new ColorShadeFactory(265), new ColorShadeFactory(300), new ColorShadeFactory(340)), 16, 4));

        // set the palettes
        d.setPalettes(palettes.toArray(new Palette[palettes.size()]));

        // set the initial palette
        d.selectPaletteId(mSelectedPalette);

        // show the fragment
        d.show(getActivity().getSupportFragmentManager(), "");
    }


    static public interface OnBackgroundListener {
        void onBackgroundChoose();
    }
}
