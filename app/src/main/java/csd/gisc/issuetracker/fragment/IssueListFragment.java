package csd.gisc.issuetracker.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import csd.gisc.issuetracker.R;
import csd.gisc.issuetracker.activity.IssueDetailActivity;
import csd.gisc.issuetracker.adapter.IssueListAdapter;
import csd.gisc.issuetracker.model.Issue;
import csd.gisc.issuetracker.enums.Status;
import csd.gisc.issuetracker.view.RecyclerItemTouchHelper;
import csd.gisc.issuetracker.view.RecyclerViewClickListener;
import csd.gisc.issuetracker.view.holder.IssueViewHolder;

/**
 * Created by admin on 22/12/2560.
 */

public abstract class IssueListFragment extends Fragment
        implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, RecyclerViewClickListener {

    private static final String TAG = IssueListFragment.class.getSimpleName() + "TAG";

    private RecyclerView recyclerView;
    private RecyclerViewClickListener recyclerViewClickListener;
    private List<Issue> issueList;

    private Query query;
    private FirebaseRecyclerOptions<Issue> options;
    private FirebaseRecyclerAdapter<Issue, IssueViewHolder> adapter;
    private DatabaseReference mDatabase;

    private Context context;

    public IssueListFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initInstances();
    }

    private void initInstances() {
        context = getContext();
        issueList = new ArrayList<>();
        recyclerViewClickListener = this;

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_issue, container, false);
        initUi(rootView);
        return rootView;
    }

    private void initUi(View rootView) {
        recyclerView = rootView.findViewById(R.id.list_issue);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(
//                new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
//
//        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
//                new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
//        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
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

        query = getQuery(mDatabase);

        options = new FirebaseRecyclerOptions.Builder<Issue>()
                .setQuery(query, Issue.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Issue, IssueViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final IssueViewHolder holder,
                                            int position,
                                            @NonNull Issue issue) {
                DatabaseReference issueRef = getRef(position);
                final String issueKey = issueRef.getKey();
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), IssueDetailActivity.class);
                        intent.putExtra("issue_key", issueKey);
                        startActivity(intent);
                    }
                });

                holder.bindToIssue(issue);
            }

            @Override
            public IssueViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_issue, parent, false);

                return new IssueViewHolder(view);
            }
        };

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                         int direction,
                         int position) {
        if (viewHolder instanceof IssueListAdapter.IssueHolder) {
            //adapter.removeItem(viewHolder.getAdapterPosition());
        }
    }

    @Override
    public void recyclerViewListClicked(View v,
                                        int position) {
        showToast("Position: " + position);
        Intent intent = new Intent(getContext(), IssueDetailActivity.class);
        startActivity(intent);
    }

    private void showToast(CharSequence message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public abstract Query getQuery(DatabaseReference databaseReference);
}
