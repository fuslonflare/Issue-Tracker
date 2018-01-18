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

        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_area, IssueDetailFragment.newInstance())
                .commit();
    }

    private void initInstances() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
