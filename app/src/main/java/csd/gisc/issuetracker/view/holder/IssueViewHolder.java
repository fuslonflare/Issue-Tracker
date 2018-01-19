package csd.gisc.issuetracker.view.holder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;

import csd.gisc.issuetracker.R;
import csd.gisc.issuetracker.enums.Status;
import csd.gisc.issuetracker.model.Issue;

/**
 * Created by admin on 18/1/2561.
 */

public class IssueViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout viewForeground;
    private LinearLayout viewBackground;

    private AppCompatTextView textIssueId;
    private AppCompatTextView textProductName;
    private AppCompatTextView textDetail;
    private AppCompatTextView textNewStatus;

    private String issueKey;
    private Status newStatus;

    public IssueViewHolder(View itemView) {
        super(itemView);

        textIssueId = itemView.findViewById(R.id.text_issue_id);
        textProductName = itemView.findViewById(R.id.text_product_name);
        textDetail = itemView.findViewById(R.id.text_detail);
        viewBackground = itemView.findViewById(R.id.view_background);
        viewForeground = itemView.findViewById(R.id.view_foreground);
        textNewStatus = itemView.findViewById(R.id.text_new_status);
    }

    public void bindToIssue(Issue issue, String issueKey) {
        this.issueKey = issueKey;

        textIssueId.setText(String.valueOf(issue.getId()));
        textProductName.setText(issue.getProductName());
        textDetail.setText(issue.getDetail());

        newStatus = issue.getStatus();
        switch (issue.getStatus()) {
            case New:
                newStatus = Status.InProgress;
                break;
            case InProgress:
                newStatus = Status.Closed;
                break;
            case Closed:
                newStatus = Status.InProgress;
                break;
            default:
                break;
        }

        textNewStatus.setText(newStatus.name());
    }

    public void changeStatus(DatabaseReference ref) {
        ref.child(issueKey).child("status").setValue(newStatus);
    }
}
