package csd.gisc.issuetracker.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import csd.gisc.issuetracker.R;
import csd.gisc.issuetracker.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initInstances();

        SharedPreferences sharedPref = getSharedPreferences("preference_user_data", Context.MODE_PRIVATE);

        String name = sharedPref.getString("name", "");
        String unitCode = sharedPref.getString("unit_code", "");
        String profilePicture = sharedPref.getString("profile_picture", "");
        String employeeId = sharedPref.getString("employee_id", "");

        if (name.length() != 0 && unitCode.length() != 0 && profilePicture.length() != 0) {
            Bundle bundle = new Bundle();
            bundle.putString("user_name", name);
            bundle.putString("user_unit_code", unitCode);
            bundle.putString("user_profile_picture", profilePicture);
            bundle.putString("employee_id", employeeId);

            Intent intent = new Intent(LoginActivity.this, IssueBoardActivity.class);
            intent.putExtra("user_info", bundle);
            startActivity(intent);
            finish();
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_area, LoginFragment.newInstance())
                    .commit();
        }
    }

    private void initInstances() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
