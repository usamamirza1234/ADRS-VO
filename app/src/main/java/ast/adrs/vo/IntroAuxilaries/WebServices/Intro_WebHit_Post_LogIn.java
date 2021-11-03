package ast.adrs.vo.IntroAuxilaries.WebServices;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.nio.charset.StandardCharsets;


import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;


public class Intro_WebHit_Post_LogIn {

    //object bnaya ha api k respnse ko website a dalny k bad
    // root class ka nam hatm kr dia ha
    //andr wali czain rhny di han
    public static ResponseModel responseObject = null;

    //http request ka object ha
    private final AsyncHttpClient mClient = new AsyncHttpClient();

    //context
    public Context mContext;


    //nam kuch b ho
    public void postSignIn(Context context, final IWebCallback iWebCallback,
                           final String _signInEntity) {
        mContext = context;


       // String myUrl = AppConfig.getInstance().getBaseUrlApi() + ApiMethod.POST.signIn;
        String myUrl = "http://119.160.90.110:8020/auth/login";


        ///////////
        Log.d("LOG_AS", "postSignIn: " + myUrl + _signInEntity);

        StringEntity entity = null;

        entity = new StringEntity(_signInEntity, "UTF-8");

        mClient.setMaxRetriesAndTimeout(AppConstt.LIMIT_API_RETRY, AppConstt.LIMIT_TIMOUT_MILLIS);

        Log.d("currentLang", AppConfig.getInstance().loadDefLanguage());

        mClient.post(mContext, myUrl, entity, "application/json", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String strResponse;


                        try {
                            Gson gson = new Gson();
                            strResponse = new String(responseBody, StandardCharsets.UTF_8);
                            Log.d("LOG_AS", "postSignIn: strResponse" + strResponse);
                            responseObject = gson.fromJson(strResponse, ResponseModel.class);


                            iWebCallback.onWebResult(true, "");

//                            switch (statusCode) {
//
//                                case AppConstt.ServerStatus.OK:
//                                case AppConstt.ServerStatus.CREATED:
//                                    iWebCallback.onWebResult(true, "");
//                                    break;
//
//                                default:
//                                    AppConfig.getInstance().parsErrorMessage(iWebCallback, responseBody);
//                                    break;
//                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            iWebCallback.onWebException(ex);
                        }
                    }


                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        switch (statusCode) {
                            case AppConstt.ServerStatus.NETWORK_ERROR:
                                iWebCallback.onWebResult(false, AppConfig.getInstance().getNetworkErrorMessage());
                                break;

                            case AppConstt.ServerStatus.FORBIDDEN:
                            case AppConstt.ServerStatus.UNAUTHORIZED:
                            default:
                                AppConfig.getInstance().parsErrorMessage(iWebCallback, responseBody);
                                break;
                        }
                    }
                }

        );
    }


    public class ResponseModel {


        public class User {
            private String id;

            private String loginID;

            private String name;

            private String designation;

            public void setId(String id) {
                this.id = id;
            }

            public String getId() {
                return this.id;
            }

            public void setLoginID(String loginID) {
                this.loginID = loginID;
            }

            public String getLoginID() {
                return this.loginID;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return this.name;
            }

            public void setDesignation(String designation) {
                this.designation = designation;
            }

            public String getDesignation() {
                return this.designation;
            }
        }


        private String token;

        private User user;

        public void setToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return this.token;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public User getUser() {
            return this.user;
        }


    }
}