package csd.gisc.issuetracker.fragment;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import csd.gisc.issuetracker.enums.Status;

/**
 * Created by admin on 22/12/2560.
 */

public class ClosedIssueFragment extends IssueListFragment {

    public ClosedIssueFragment() {
    }

    public static ClosedIssueFragment newInstance() {
        ClosedIssueFragment fragment = new ClosedIssueFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        return databaseReference.child("issues")
                .orderByChild("status")
                .equalTo(Status.Closed.name());
    }
}
