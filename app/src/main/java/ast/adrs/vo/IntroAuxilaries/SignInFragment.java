package ast.adrs.vo.IntroAuxilaries;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.armoomragames.denketa.R;
import com.google.gson.JsonObject;

import ast.adrs.vo.IntroActivity;
import ast.adrs.vo.Utils.AppConfig;
import ast.adrs.vo.Utils.IWebCallback;
import ast.adrs.vo.IntroAuxilaries.WebServices.Intro_WebHit_Post_LogIn;


public class SignInFragment extends Fragment implements View.OnClickListener {

    RelativeLayout rlSignin, rlSignUp, rlForgot;
    EditText edtName, edtPassword;
    String str = "";
    private Dialog progressDialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_sign_in, container, false);

        init();
        bindviews(frg);


        return frg;

    }

    private void bindviews(View frg) {

        rlSignin = frg.findViewById(R.id.frg_presigin_rllogin);
        edtName = frg.findViewById(R.id.frg_signin_editTextTextPersonName);
        edtPassword = frg.findViewById(R.id.frg_signin_editTextTextPassword);


        rlSignin.setOnClickListener(this);


        editTextWatchers();
    }

    private void init() {
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.frg_presigin_rllogin:

                closeKeyboard();
                checkErrorConditions();

                break;

        }
    }


    private void navToMainActivity() {
        ((IntroActivity) getActivity()).navtoMainActivity();

    }


    private void checkErrorConditions() {
        if (checkPasswordError()) {


            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("username", edtName.getText().toString());
            jsonObject.addProperty("password", edtPassword.getText().toString());
            jsonObject.addProperty("appVersion", "1.0");

            requestUserSigin(jsonObject.toString());


//            AppConfig.getInstance().mUser.isLoggedIn = true;
//            AppConfig.getInstance().saveUserProfile();
//            navToMainActivity();
        }
    }

    private boolean checkPasswordError() {
        if (!edtName.getText().toString().equalsIgnoreCase("") && !edtPassword.getText().toString().isEmpty()) {
            return true;
        } else {
            Toast.makeText(getContext(), "Enter all fields", Toast.LENGTH_LONG).show();
            return false;
        }

    }

    //region  functions for Dialog
    private void dismissProgDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    private void showProgDialog() {
        progressDialog = new Dialog(getActivity(), R.style.AppTheme);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.dialog_progress);

        progressDialog.setCancelable(false);
        progressDialog.show();
    }


    private void showProgInstallingDataDialog() {
        progressDialog = new Dialog(getActivity(), R.style.AppTheme);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.dialog_installing_progress);

        progressDialog.setCancelable(false);
        progressDialog.show();
    }


    private void closeKeyboard() {
        AppConfig.getInstance().closeKeyboard(getActivity());
    }

    //region  functions for API
    private void requestUserSigin(String _signUpEntity) {

        showProgDialog();
        Intro_WebHit_Post_LogIn intro_webHit_post_logIn = new Intro_WebHit_Post_LogIn();

        intro_webHit_post_logIn.postSignIn(getContext(), new IWebCallback() {
            @Override
            public void onWebResult(boolean isSuccess, String strMsg) {
                if (isSuccess) {

                    dismissProgDialog();

                    //Save user login data todo un
                    AppConfig.getInstance().mUser.User_Id = (Intro_WebHit_Post_LogIn.responseObject.getResult().getId());

                    AppConfig.getInstance().mUser.Email = Intro_WebHit_Post_LogIn.responseObject.getResult().getUserName();

                    AppConfig.getInstance().mUser.Authorization = Intro_WebHit_Post_LogIn.responseObject.getResult().getAuthToken();

                    AppConfig.getInstance().mUser.isLoggedIn = true;

                    AppConfig.getInstance().saveUserProfile();

                    navToMainActivity();

                } else {
                    dismissProgDialog();
                    Toast.makeText(getActivity(), strMsg, Toast.LENGTH_SHORT).show();
                    //uncommet todo
                    AppConfig.getInstance().showErrorMessage(getContext(), strMsg);
                }
            }

            @Override
            public void onWebException(Exception ex) {
                dismissProgDialog();
                Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                AppConfig.getInstance().showErrorMessage(getContext(), ex.toString());
            }
        }, _signUpEntity);
    }




    private void editTextWatchers() {

        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().startsWith(" ")) {
                    edtName.setText("");
                }
            }
        });
        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().startsWith(" ")) {
                    edtPassword.setText("");
                }
            }
        });

    }
}
