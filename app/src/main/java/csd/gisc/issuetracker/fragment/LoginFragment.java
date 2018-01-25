package csd.gisc.issuetracker.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import csd.gisc.issuetracker.R;
import csd.gisc.issuetracker.activity.IssueBoardActivity;
import csd.gisc.issuetracker.manager.HttpManager;
import csd.gisc.issuetracker.model.RequestLogin;
import csd.gisc.issuetracker.model.Response;
import csd.gisc.issuetracker.model.ResultLogin;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by admin on 22/12/2560
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
    public View onCreateView(@NonNull LayoutInflater inflater,
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
    public void onSaveInstanceState(@NonNull Bundle outState) {
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
            if (checkAllFieldEmpty()) {
                Log.d(TAG, "Field empty");
                showSnackbar("Field couldn't empty");
                return;
            }

            final Intent intent = new Intent(getActivity(), IssueBoardActivity.class);
            int groupId = getGroupId(spinCompany.getSelectedItemPosition());

            RequestLogin loginRequest = new RequestLogin(
                    editUsername.getEditableText().toString(),
                    editPassword.getEditableText().toString(),
                    groupId);

            HttpManager.getInstance().getService()
                    .login(loginRequest)
                    .enqueue(new Callback<Response<ResultLogin>>() {


                        @Override
                        public void onResponse(Call<Response<ResultLogin>> call,
                                               retrofit2.Response<Response<ResultLogin>> response) {
                            if (response.isSuccessful()) {
                                Response<ResultLogin> body = response.body();
                                if (body != null) {
                                    if (body.getErrorMsg().length() == 0) {

                                        // Login success
                                        String tokenUser = body.getResult().get(0).getTokenUser();
                                        String employeeId = body.getResult().get(0).getEmployeeId();
                                        String groupId = body.getResult().get(0).getGroupId();

                                        Bundle bundle = packBundle(tokenUser, employeeId, groupId);

                                        intent.putExtra("required_parameters", bundle);
                                        startActivity(intent);
                                        if (getActivity() != null) {
                                            getActivity().finish();
                                        }
                                    } else {
                                        Log.e(TAG, "Error from server : " + body.getErrorMsg());
                                        showSnackbar("Error from server : " + body.getErrorMsg());
                                    }
                                } else {
                                    Log.wtf(TAG, "onResponse but not body is null");
                                    showSnackbar("Sorry, We have a problem (body is null)");
                                }
                            } else {
                                Log.e(TAG, "onResponse but not successful : " + response.message());
                                showSnackbar("Error " + response.code() + " " + response.message() +
                                        ",\nPlease check your credential.");
                            }
                        }

                        @Override
                        public void onFailure(Call<Response<ResultLogin>> call,
                                              Throwable t) {
                            Log.e(TAG, "onFailure : " + t.toString(), t);
                            showSnackbar("Connect server error, please check your connection.");
                        }
                    });
        }

        if (id == R.id.button_reset) {
            resetField();
        }
    }

    private Bundle packBundle(String tokenUser,
                              String employeeId,
                              String groupId) {
        Bundle result = new Bundle();
        result.putString("token_user", tokenUser);
        result.putString("employee_id", employeeId);
        result.putString("group_id", groupId);

        return result;
    }

    private int getGroupId(int selectedItemPosition) {
        int groupId = 0;
        switch (selectedItemPosition) {
            case 0:
                groupId = 1;
                break;
            case 1:
                groupId = 2;
                break;
            case 2:
                groupId = 4;
                break;
            default:
                break;
        }
        return groupId;
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

    private void showSnackbar(CharSequence message) {
        Snackbar.make(buttonLogin, message, Snackbar.LENGTH_SHORT).show();
    }

    private void showSnackbar(int stringResourceId) {
        Snackbar.make(buttonLogin, stringResourceId, Snackbar.LENGTH_SHORT).show();
    }
}
