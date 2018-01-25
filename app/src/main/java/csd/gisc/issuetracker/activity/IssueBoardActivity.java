package csd.gisc.issuetracker.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.ArraySet;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Set;

import csd.gisc.issuetracker.R;
import csd.gisc.issuetracker.fragment.ClosedIssueFragment;
import csd.gisc.issuetracker.fragment.InProgressIssueFragment;
import csd.gisc.issuetracker.fragment.NewIssueFragment;
import csd.gisc.issuetracker.manager.HttpManager;
import csd.gisc.issuetracker.model.Response;
import csd.gisc.issuetracker.model.ResultUserInfo;
import retrofit2.Call;
import retrofit2.Callback;

public class IssueBoardActivity extends AppCompatActivity {

    private static final int SIZE_PAGE = 3;
    private static final String TAG = IssueBoardActivity.class.getSimpleName();

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private ViewPager pagerIssueBoard;
    private TabLayout tabLayout;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ImageView imageProfile;
    private NavigationView navigationView;
    private View navigationHeader;
    private AppCompatTextView textName;
    private AppCompatTextView textUnitCode;

    private Bundle requiredParameters;
    private Bundle userInfo;
    private String tokenUser;
    private String employeeId;
    private String groupId;
    private String name;
    private String unitCode;
    private Uri profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_board);

        initInstances();
        initUi();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!isNullOrEmpty(tokenUser) &&
                !isNullOrEmpty(employeeId) &&
                !isNullOrEmpty(groupId)) {
            HttpManager.getInstance().getService()
                    .getEmployeeInfo(tokenUser, groupId, employeeId)
                    .enqueue(new Callback<Response<ResultUserInfo>>() {
                        @Override
                        public void onResponse(Call<Response<ResultUserInfo>> call,
                                               retrofit2.Response<Response<ResultUserInfo>> response) {
                            Response<ResultUserInfo> body = response.body();
                            if (body != null) {
                                if (body.getErrorMsg().length() == 0) {
                                    // Get employee success

                                    name = body.getResult().get(0).getEngName();
                                    name = improveName(name);
                                    unitCode = body.getResult().get(0).getUnitCode();
                                    profilePicture = Uri.parse(body.getResult().get(0).getProfileImage());

                                    storeUserData(name, unitCode, profilePicture);
                                    setProfile(name, unitCode, profilePicture);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Response<ResultUserInfo>> call,
                                              Throwable t) {
                            Log.e(TAG, "onFailure : " + t.toString(), t);
                            showSnackbar("Connect server error, please check your connection.");
                        }
                    });
        } else {
            setProfile(name, unitCode, profilePicture);

            /*Log.d(TAG, String.format("Some requiredParameters is null or empty : " +
                    "tokenUser : %1$s, " +
                    "employeeId : %2$s, " +
                    "groupId : %3$s", tokenUser, employeeId, groupId));*/
        }
    }

    private void storeUserData(String name,
                               String unitCode,
                               Uri profilePicture) {
        editor = preferences.edit();

        editor.putString("name", name);
        editor.putString("unit_code", unitCode);
        editor.putString("profile_picture", profilePicture.toString());
        editor.putString("employee_id", employeeId);

        editor.apply();
    }

    private void setProfile(String name,
                            String unitCode,
                            Uri profilePicture) {
        String nameWithEmpId = employeeId + " - " + name;
        Glide.with(this)
                .load(profilePicture)
                .apply(new RequestOptions().circleCrop())
                .into(imageProfile);
        textName.setText(nameWithEmpId);
        textUnitCode.setText(unitCode);
    }

    private String improveName(String name) {
        StringBuilder newFullName = new StringBuilder();
        String[] fullName = name.split(" ");

        for (String aName : fullName) {
            aName = aName.toLowerCase();
            aName = aName.substring(0, 1).toUpperCase() + aName.substring(1);

            newFullName.append(aName).append(" ");
        }

        return newFullName.toString();
    }

    private boolean isNullOrEmpty(String text) {
        return text == null || text.length() == 0;
    }

    private void initInstances() {
        preferences = getSharedPreferences("preference_user_data", MODE_PRIVATE);

        requiredParameters = getIntent().getBundleExtra("required_parameters");
        if (requiredParameters != null) {
            tokenUser = requiredParameters.getString("token_user");
            employeeId = requiredParameters.getString("employee_id");
            groupId = requiredParameters.getString("group_id");
        }

        userInfo = getIntent().getBundleExtra("user_info");
        if (userInfo != null) {
            name = userInfo.getString("user_name");
            unitCode = userInfo.getString("user_unit_code");
            profilePicture = Uri.parse(userInfo.getString("user_profile_picture"));
            employeeId = userInfo.getString("employee_id");
        }
    }

    private void initUi() {
        // Set toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Init drawer navigation
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                IssueBoardActivity.this,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Set data to drawer navigation
        navigationHeader = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.log_out) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        deleteSharedPreferences("preference_user_data");
                    } else {
                        editor = preferences.edit();
                        editor.clear();
                        editor.apply();
                    }

                    Intent intent = new Intent(IssueBoardActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                return true;
            }
        });
        imageProfile = navigationHeader.findViewById(R.id.image_profile);
        textName = navigationHeader.findViewById(R.id.text_name);
        textUnitCode = navigationHeader.findViewById(R.id.text_unit_code);

        // Init ViewPager
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
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void showSnackbar(CharSequence message) {
        Snackbar.make(toolbar, message, Snackbar.LENGTH_SHORT).show();
    }

    private void showSnackbar(int stringResourceId) {
        Snackbar.make(toolbar, stringResourceId, Snackbar.LENGTH_SHORT).show();
    }
}
