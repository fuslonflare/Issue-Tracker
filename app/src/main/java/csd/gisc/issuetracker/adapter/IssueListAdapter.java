package csd.gisc.issuetracker.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import csd.gisc.issuetracker.view.IssueListItem;

/**
 * Created by admin on 28/12/2560.
 */

public class IssueListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 10000;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        IssueListItem item;
        if (convertView == null) {
            item = new IssueListItem(parent.getContext());
        } else {
            item = (IssueListItem) convertView;
        }
        return item;
    }
}
