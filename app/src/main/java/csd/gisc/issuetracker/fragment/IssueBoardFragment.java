package csd.gisc.issuetracker.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import csd.gisc.issuetracker.R;
import csd.gisc.issuetracker.enums.Status;
import csd.gisc.issuetracker.view.SlidingTabLayout;

/**
 * Created by admin on 22/12/2560.
 */

public class IssueBoardFragment extends Fragment {

    private static final int SIZE_PAGE = 3;

    private ViewPager pagerIssueBoard;
    private SlidingTabLayout slidingTabLayout;


    public IssueBoardFragment() {
        super();
    }

    public static IssueBoardFragment newInstance() {
        IssueBoardFragment fragment = new IssueBoardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_issue_board, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        pagerIssueBoard = rootView.findViewById(R.id.pager_issue_board);
        slidingTabLayout = rootView.findViewById(R.id.sliding_tab_layout);

        pagerIssueBoard.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        //return NewIssueFragment.newInstance();
                    case 1:
                        //return InProgressIssueFragment.newInstance();
                    case 2:
                        //return ClosedIssueFragment.newInstance();
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return SIZE_PAGE;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getResources().getStringArray(R.array.list_label_pager)[position];
            }
        });
        slidingTabLayout.setViewPager(pagerIssueBoard);
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

    private void showToast(CharSequence message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
