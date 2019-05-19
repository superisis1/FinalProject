package kr.insungjung.finalproject.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import kr.insungjung.finalproject.fragments.NoticeListFragment;
import kr.insungjung.finalproject.fragments.UserInfoFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    Fragment frag1, frag2;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fr = null;

        if (position == 0) {
            if (frag1 == null) {
                frag1 = new UserInfoFragment();
            }
            fr = frag1;
        }
        else if (position == 1) {
            if (frag2 == null) {
                frag2 = new NoticeListFragment();
            }
            fr = frag2;
        }

        return fr;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}




