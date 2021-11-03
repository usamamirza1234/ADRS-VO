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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.armoomragames.denketa.R;
import com.google.gson.JsonObject;

import ast.adrs.vo.IntroAuxilaries.WebServices.AppConfig;
import ast.adrs.vo.IntroActivity;
import ast.adrs.vo.IntroAuxilaries.WebServices.Home_WebHit_Get_Database;
import ast.adrs.vo.IntroAuxilaries.WebServices.Intro_WebHit_Post_LogIn;
import ast.adrs.vo.MainAuxilaries.HomeFragment;
import ast.adrs.vo.IntroAuxilaries.WebServices.AppConstt;
import ast.adrs.vo.IntroAuxilaries.WebServices.IWebCallback;

public class PreSignInFragment extends Fragment implements View.OnClickListener {

    private Dialog progressDialog;
    RelativeLayout rlSignin, rlSignUp, rlForgot;
    EditText edtName, edtPassword;
    String str = "";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_pre_sign_in, container, false);

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

            AppConfig.getInstance().mUser.isLoggedIn = true;
            AppConfig.getInstance().saveUserProfile();
            navToMainActivity();
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.addProperty("loginId", edtName.getText().toString());
//            jsonObject.addProperty("password", edtPassword.getText().toString());

//            requestUserSigin(jsonObject.toString());
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
                  AppConfig.getInstance().mUser.User_Id = Integer.parseInt(Intro_WebHit_Post_LogIn.responseObject.getUser().getId());

                    AppConfig.getInstance().mUser.Email = Intro_WebHit_Post_LogIn.responseObject.getUser().getLoginID();

                    AppConfig.getInstance().mUser.Authorization = Intro_WebHit_Post_LogIn.responseObject.getToken();
                    if (Intro_WebHit_Post_LogIn.responseObject.getUser().getName() != null)
                        Toast.makeText(getActivity(),"error", Toast.LENGTH_SHORT).show();
                        AppConfig.getInstance().mUser.Name = Intro_WebHit_Post_LogIn.responseObject.getUser().getName();
                    AppConfig.getInstance().mUser.isLoggedIn = true;
                    AppConfig.getInstance().saveUserProfile();


                    requestDataBase();

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


    private void requestDataBase() {
        dismissProgDialog();
        showProgDialog();
        Home_WebHit_Get_Database home_webHit_get_database = new Home_WebHit_Get_Database();
        home_webHit_get_database.getDatabase(new IWebCallback() {
            @Override
            public void onWebResult(boolean isSuccess, String strMsg) {
                if (isSuccess) {

                    showProgInstallingDataDialog();
                    if (strMsg != "") {
                     //   populateDBOPS(strMsg);
                    }

                    navToMainActivity();
                    dismissProgDialog();
                } else {
                    dismissProgDialog();
                    Toast.makeText(getActivity(), strMsg, Toast.LENGTH_SHORT).show();
//                    AppConfig.getInstance().showErrorMessage(getContext(), strMsg);
                }
            }

            @Override
            public void onWebException(Exception ex) {
                dismissProgDialog();
                Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
//                AppConfig.getInstance().showErrorMessage(getContext(), ex.toString());
            }
        });
    }






    private void navToSignUpFragment() {

    }

    private void navToSignInFragment() {
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        Fragment frag = new SignInFragment();
//        ft.add(R.id.act_intro_content_frg, frag, AppConstt.FragTag.FN_SignInFragment);
//        ft.addToBackStack(AppConstt.FragTag.FN_SignInFragment);
//        ft.hide(this);
//        ft.commit();
    }
    private void navtoHomeFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment frag = new HomeFragment();
        ft.add(R.id.act_intro_content_frg, frag, AppConstt.FragTag.FN_HomeFragment);
        ft.addToBackStack(AppConstt.FragTag.FN_HomeFragment);
        ft.hide(this);
        ft.commit();
    }





    private FragmentManager getSupportFragmentManager() {
        return null;
    }


    private void navToForgotPasswordFragment() {
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        Fragment frag = new ForgotPasswordFragment();
//        ft.add(R.id.act_intro_content_frg, frag, AppConstt.FragTag.FN_SignInFragment);
//        ft.addToBackStack(AppConstt.FragTag.FN_SignInFragment);
//        ft.hide(this);
//        ft.commit();
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
