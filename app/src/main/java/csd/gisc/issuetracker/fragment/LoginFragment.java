package csd.gisc.issuetracker.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import csd.gisc.issuetracker.R;
import csd.gisc.issuetracker.activity.IssueBoardActivity;

/**
 * Created by admin on 22/12/2560.
 */

public class LoginFragment extends Fragment implements View.OnClickListener {

    AppCompatButton buttonLogin;
    AppCompatButton buttonReset;

    public LoginFragment() {
        super();
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        buttonLogin = rootView.findViewById(R.id.button_login);
        buttonReset = rootView.findViewById(R.id.button_reset);

        buttonLogin.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_login) {
            Intent intent = new Intent(getActivity(), IssueBoardActivity.class);
            getActivity().startActivity(intent);
        }
    }
}
