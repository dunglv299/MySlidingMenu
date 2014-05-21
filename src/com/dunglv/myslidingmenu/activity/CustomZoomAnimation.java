package com.dunglv.myslidingmenu.activity;

import android.graphics.Canvas;
import android.os.Bundle;
import com.dunglv.myslidingmenu.R;
import com.dunglv.myslidingmenu.fragment.FragmentTransactionHelper;
import com.dunglv.myslidingmenu.fragment.SampleListFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class CustomZoomAnimation extends SlidingFragmentActivity {

    private CanvasTransformer mTransformer;
    private SlidingMenu slidingMenu;
    private FragmentTransactionHelper mFragmentTransactionHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the Above View
        setContentView(R.layout.content_frame);
        configSlidingMenu();
        addLeftRightMenu();

        // Add main fragment
        mFragmentTransactionHelper.setLayoutId(R.id.content_frame);
        mFragmentTransactionHelper.replaceFragmentToView(new SampleListFragment(),false);
    }

    public void configSlidingMenu() {
        // customize the SlidingMenu
        slidingMenu = getSlidingMenu();
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingMenu.setBehindScrollScale(0.0f);
        mTransformer = new CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen) {
                float scale = (float) (percentOpen * 0.25 + 0.75);
                canvas.scale(scale, scale, canvas.getWidth() / 2, canvas.getHeight() / 2);
            }
        };
        slidingMenu.setBehindCanvasTransformer(mTransformer);
    }

    public void addLeftRightMenu() {
        // set the Behind View
        // Add left
        mFragmentTransactionHelper = new FragmentTransactionHelper(this,
                R.id.menu_frame);
        setBehindContentView(R.layout.menu_frame);
        mFragmentTransactionHelper.replaceFragmentToView(new SampleListFragment(),false);

        // Add right
        slidingMenu.setSecondaryMenu(R.layout.menu_frame_two);
        slidingMenu.setSecondaryShadowDrawable(R.drawable.shadowright);
        mFragmentTransactionHelper.setLayoutId(R.id.menu_frame_two);
        mFragmentTransactionHelper.replaceFragmentToView(new SampleListFragment(),false);

    }

}
