package kr.insungjung.finalproject;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import kr.insungjung.finalproject.adapters.PagerAdapter;
import kr.insungjung.finalproject.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    ActivityMainBinding act;

    PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        act.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(act.tabBar));
        act.tabBar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                act.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void setValues() {

        act.tabBar.addTab(act.tabBar.newTab().setText("회원정보"));
        act.tabBar.addTab(act.tabBar.newTab().setText("공지사항"));

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), act.tabBar.getTabCount());
        act.viewPager.setAdapter(mPagerAdapter);
        act.viewPager.setOffscreenPageLimit(3);

    }

    @Override
    public void bindViews() {

        act = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

}
