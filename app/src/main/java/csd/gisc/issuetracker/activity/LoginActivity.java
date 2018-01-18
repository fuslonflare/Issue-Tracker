package csd.gisc.issuetracker.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

//        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//        String userToken = sharedPref.getString("token_user", "");
//
//        if (userToken.length() >= 0) {
//            Intent intent = new Intent(LoginActivity.this, IssueBoardActivity.class);
//            intent.putExtra("token_user", userToken);
//            startActivity(intent);
//            finish();
//        }

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
