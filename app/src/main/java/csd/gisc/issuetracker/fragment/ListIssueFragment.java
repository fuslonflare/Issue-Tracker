package csd.gisc.issuetracker.fragment;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import csd.gisc.issuetracker.R;
import csd.gisc.issuetracker.adapter.IssueListAdapter;

/**
 * Created by admin on 22/12/2560.
 */

public class ListIssueFragment extends Fragment {

    ListView listIssue;
    IssueListAdapter adapter;

    public ListIssueFragment() {
        super();
    }

    public static ListIssueFragment newInstance() {
        ListIssueFragment fragment = new ListIssueFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_issue, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        listIssue = rootView.findViewById(R.id.list_issue);
        adapter = new IssueListAdapter();
        listIssue.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }
}
