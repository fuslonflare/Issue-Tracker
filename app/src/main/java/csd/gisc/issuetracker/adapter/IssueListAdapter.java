package csd.gisc.issuetracker.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

import csd.gisc.issuetracker.R;
import csd.gisc.issuetracker.model.Issue;
import csd.gisc.issuetracker.view.RecyclerViewClickListener;

/**
 * Created by admin on 28/12/2560.
 */

public class IssueListAdapter extends FirebaseRecyclerAdapter<Issue, IssueListAdapter.IssueHolder> {

    private Context context;
    private List<Issue> issueList;
    private RecyclerViewClickListener recyclerViewClickListener;

    public IssueListAdapter(@NonNull FirebaseRecyclerOptions<Issue> options) {
        super(options);
    }

    public void removeItem(int position) {
        issueList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Issue item, int position) {
        issueList.add(position, item);
        notifyItemInserted(position);
    }

    @Override
    protected void onBindViewHolder(@NonNull IssueHolder holder, int position, @NonNull Issue model) {

    }

    @Override
    public IssueHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public class IssueHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout viewForeground;

        AppCompatTextView textIssue;
        AppCompatTextView textProductName;
        AppCompatTextView textDescription;
        LinearLayout viewBackground;

        IssueHolder(View itemView) {
            super(itemView);
            textIssue = itemView.findViewById(R.id.text_issue_id);
            textProductName = itemView.findViewById(R.id.text_product_name);
            textDescription = itemView.findViewById(R.id.text_detail);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewClickListener.recyclerViewListClicked(view, this.getLayoutPosition());
        }
    }
}
