package csd.gisc.issuetracker.view.holder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import csd.gisc.issuetracker.R;
import csd.gisc.issuetracker.model.Issue;

/**
 * Created by admin on 18/1/2561.
 */

public class IssueViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout viewForeground;
    private LinearLayout viewBackground;

    private AppCompatTextView textIssueId;
    private AppCompatTextView textProductName;
    private AppCompatTextView textDetail;

    public IssueViewHolder(View itemView) {
        super(itemView);

        textIssueId = itemView.findViewById(R.id.text_issue_id);
        textProductName = itemView.findViewById(R.id.text_product_name);
        textDetail = itemView.findViewById(R.id.text_detail);
        viewBackground = itemView.findViewById(R.id.view_background);
        viewForeground = itemView.findViewById(R.id.view_foreground);
    }

    public void bindToIssue(Issue issue) {
        textIssueId.setText(String.valueOf(issue.getId()));
        textProductName.setText(issue.getProductName());
        textDetail.setText(issue.getDetail());
    }
}
