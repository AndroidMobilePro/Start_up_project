package com.textonphoto.photoeditor.quotecreator.decorate;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.textonphoto.photoeditor.quotecreator.R;

public class DecorateFragment extends Fragment {

    private RecyclerView rvConstraintTools;
    private DecorateAdapter decorateAdapter;

    private OnBackgroundListener onBackgroundListener;
    TabLayout tabLayout;

    public DecorateFragment() {
        // Required empty public constructor
    }

    public static DecorateFragment newInstance() {
        DecorateFragment fragment = new DecorateFragment();
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
        View view = inflater.inflate(R.layout.fragment_decorate, container, false);
//        decorateAdapter = new DecorateAdapter(getActivity());
//
//        rvConstraintTools = view.findViewById(R.id.rvConstraintTools);
//        LinearLayoutManager layoutEditManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        rvConstraintTools.setLayoutManager(layoutEditManager);
//        rvConstraintTools.setHasFixedSize(true);
//        //This listener will change the text fonts when clicked on any fonts
//        rvConstraintTools.setAdapter(decorateAdapter);

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        setupTabIcons();

        return view;
    }


    private void setupTabIcons() {
//
//        int[] tabIcons = {
//                R.drawable.ic_camera_2,
//                R.drawable.ic_gallery,
//                R.drawable.ic_photo_filter,
//                R.drawable.ic_insert_emoticon,
////                R.drawable.ic_sticker,
//        };
//
//        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
//        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
//        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
//        tabLayout.getTabAt(3).setIcon(tabIcons[3]);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        View view2 = LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        View view3 = LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        View view4 = LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);

        TextView tvTab = view.findViewById(R.id.tvTitleTab);
        TextView tvTab1 = view1.findViewById(R.id.tvTitleTab);
        TextView tvTab2 = view2.findViewById(R.id.tvTitleTab);
        TextView tvTab3 = view3.findViewById(R.id.tvTitleTab);
        TextView tvTab4 = view4.findViewById(R.id.tvTitleTab);

        String title[] = new String[]{"Ảnh", "Sticker", "Nhãn", "Ảnh bìa", "Vẽ"};
        tvTab.setText(title[0]);
        tvTab1.setText(title[1]);
        tvTab2.setText(title[2]);
        tvTab3.setText(title[3]);
        tvTab4.setText(title[4]);

        ImageView imgTab = view.findViewById(R.id.imgIconTab);
        ImageView imgTab1 = view1.findViewById(R.id.imgIconTab);
        ImageView imgTab2 = view2.findViewById(R.id.imgIconTab);
        ImageView imgTab3 = view3.findViewById(R.id.imgIconTab);
        ImageView imgTab4 = view3.findViewById(R.id.imgIconTab);

//        tabLayout.getTabAt(4).setIcon(tabIcons[4]);

//        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabOne.setText("ONE");


//        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_favourite, 0, 0);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view));
        tabLayout.addTab(tabLayout.newTab().setCustomView(view1));
        tabLayout.addTab(tabLayout.newTab().setCustomView(view2));
        tabLayout.addTab(tabLayout.newTab().setCustomView(view3));
        tabLayout.addTab(tabLayout.newTab().setCustomView(view4));

//        tabLayout.getTabAt(0).setCustomView(view);
//        tabLayout.getTabAt(1).setCustomView(view1);
//        tabLayout.getTabAt(2).setCustomView(view2);
//        tabLayout.getTabAt(3).setCustomView(view3);
//        tabLayout.getTabAt(3).setCustomView(view3);
    }

    static public interface OnBackgroundListener {
        void onBackgroundChoose();
    }
}
