package csd.gisc.issuetracker.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import csd.gisc.issuetracker.R;
import csd.gisc.issuetracker.enums.Status;

/**
 * Created by admin on 22/12/2560
 */

public class NewIssueFragment extends IssueListFragment {

    public NewIssueFragment() {
    }

    public static NewIssueFragment newInstance() {
        NewIssueFragment fragment = new NewIssueFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        return databaseReference.child("issues")
                .orderByChild("status")
                .equalTo(Status.New.name());
    }
}
