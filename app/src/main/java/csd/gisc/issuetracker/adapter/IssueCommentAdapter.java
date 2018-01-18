package csd.gisc.issuetracker.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import csd.gisc.issuetracker.view.IssueCommentItem;

/**
 * Created by admin on 4/1/2561.
 */

public class IssueCommentAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup viewParent) {
        IssueCommentItem item;
        if (convertView != null) {
            item = (IssueCommentItem) convertView;
        } else {
            item = new IssueCommentItem(viewParent.getContext());
        }
        return item;
    }
}
