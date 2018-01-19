package csd.gisc.issuetracker.activity;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import csd.gisc.issuetracker.R;
import csd.gisc.issuetracker.fragment.ClosedIssueFragment;
import csd.gisc.issuetracker.fragment.InProgressIssueFragment;
import csd.gisc.issuetracker.fragment.NewIssueFragment;

public class IssueBoardActivity extends AppCompatActivity {

    private static final int SIZE_PAGE = 3;

    private ViewPager pagerIssueBoard;
    private TabLayout tabLayout;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    ImageView imageProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_board);

        initInstances();
    }

    private void initInstances() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                IssueBoardActivity.this,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageProfile = findViewById(R.id.image_profile);
        Glide.with(this)
                .load("http://www2.cdg.co.th/CDGIntranetUpload/PhoneBook/EXC01777_01.JPG")
                .apply(new RequestOptions().circleCrop())
                .into(imageProfile);

        pagerIssueBoard = findViewById(R.id.pager_issue_board);
        tabLayout = findViewById(R.id.sliding_tab_layout);

        pagerIssueBoard.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return NewIssueFragment.newInstance();
                    case 1:
                        return InProgressIssueFragment.newInstance();
                    case 2:
                        return ClosedIssueFragment.newInstance();
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return SIZE_PAGE;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getResources().getStringArray(R.array.list_label_pager)[position];
            }
        });

        tabLayout.setupWithViewPager(pagerIssueBoard);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_announcement);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_track_changes);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_assignment_turned_in);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
