package csd.gisc.issuetracker.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import csd.gisc.issuetracker.R;
import csd.gisc.issuetracker.fragment.IssueDetailFragment;

public class IssueDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_detail);

        initInstances();

        String issueKey = getIntent().getStringExtra("issue_key");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_area, IssueDetailFragment.newInstance(issueKey))
                    .commit();
        }
    }

    private void initInstances() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
