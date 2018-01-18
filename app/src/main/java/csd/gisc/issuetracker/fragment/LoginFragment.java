package csd.gisc.issuetracker.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import csd.gisc.issuetracker.R;
import csd.gisc.issuetracker.activity.IssueBoardActivity;

/**
 * Created by admin on 22/12/2560.
 */

public class LoginFragment extends Fragment implements View.OnClickListener {

    TextInputEditText editUsername;
    TextInputEditText editPassword;
    Spinner spinCompany;

    AppCompatButton buttonLogin;
    AppCompatButton buttonReset;

    private static final String TAG = "LoginFragmentTAG";

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
        editUsername = rootView.findViewById(R.id.edit_username);
        editPassword = rootView.findViewById(R.id.edit_password);
        spinCompany = rootView.findViewById(R.id.spin_company);

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
//            if (checkAllFieldEmpty()) {
//                Log.d(TAG, "Field empty");
//                return;
//            }

            Intent intent = new Intent(getActivity(), IssueBoardActivity.class);
//            int groupId = 0;
//            switch (spinCompany.getSelectedItemPosition()) {
//                case 0:
//                    groupId = 1;
//                    break;
//                case 1:
//                    groupId = 2;
//                    break;
//                case 2:
//                    groupId = 4;
//                    break;
//                default:
//                    break;
//            }
//
//            CredentialDao loginRequest = new CredentialDao(
//                    editUsername.getEditableText().toString(),
//                    editPassword.getEditableText().toString(),
//                    groupId
//            );
//            HttpManager.getInstance().getService()
//                    .login(loginRequest)
//                    .enqueue(new Callback<ResponseDao<ResultCredentialDao>>() {
//                        @Override
//                        public void onResponse(@NonNull Call<ResponseDao<ResultCredentialDao>> call,
//                                               @NonNull Response<ResponseDao<ResultCredentialDao>> response) {
//                            if (response.isSuccessful()) {
//                                if (response.body().getErrorCode().equals("Success")) {
//                                    Log.d(TAG, "Login success");
//                                    SharedPreferences sharedPref = getContext().getSharedPreferences(
//                                            getString(R.string.preference_file_key), Context.MODE_PRIVATE);
//                                    SharedPreferences.Editor editor = sharedPref.edit();
//                                    String userToken = response.body().getResult().get(0).getTokenUser();
//                                    editor.putString("token_user", userToken);
//                                    intent.putExtra("token_user", userToken);
                                    startActivity(intent);
//                                    getActivity().finish();
//                                } else {
//                                    Log.d(TAG, "Login fail");
//                                }
//                            } else {
//                                Log.d(TAG, response.code() + " : " + response.message());
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(@NonNull Call<ResponseDao<ResultCredentialDao>> call,
//                                              @NonNull Throwable t) {
//                            Log.e(TAG, t.toString());
//                        }
//                    });
//        }
//        if (id == R.id.button_reset) {
//            resetField();
        }
        if (id == R.id.button_reset) {
            resetField();
        }
    }

    private boolean checkAllFieldEmpty() {
        return editUsername.getText().toString().equals("") ||
                editPassword.getText().toString().equals("");
    }

    private void resetField() {
        editUsername.setText("");
        editPassword.setText("");
        spinCompany.setSelection(0);
    }
}
