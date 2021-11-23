package ast.adrs.vo.IntroAuxilaries.WebServices;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.nio.charset.StandardCharsets;
import java.util.List;


import ast.adrs.vo.Utils.AppConfig;
import ast.adrs.vo.Utils.AppConstt;
import ast.adrs.vo.Utils.IWebCallback;
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
        String myUrl = "http://services.adrspunjab.gov.pk/api/Account/login";
        Log.d("LOG_AS", "postSignIn: " + myUrl + _signInEntity);
        StringEntity entity = null;
        entity = new StringEntity(_signInEntity, "UTF-8");
        mClient.setMaxRetriesAndTimeout(AppConstt.LIMIT_API_RETRY, AppConstt.LIMIT_TIMOUT_MILLIS);
        mClient.post(mContext, myUrl, entity, "application/json", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String strResponse;
                        try {
                            Gson gson = new Gson();
                            strResponse = new String(responseBody, StandardCharsets.UTF_8);
                            Log.d("LOG_AS", "postSignIn: strResponse" + strResponse);
                            responseObject = gson.fromJson(strResponse, ResponseModel.class);

                            switch (statusCode) {

                                case AppConstt.ServerStatus.OK:
                                case AppConstt.ServerStatus.CREATED:
                                    iWebCallback.onWebResult(true, "");
                                    break;

                                default:
                                    AppConfig.getInstance().parsErrorMessageOnFailure(iWebCallback, responseBody);
                                    break;
                            }
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
                                AppConfig.getInstance().parsErrorMessageOnFailure(iWebCallback, responseBody);
                                break;
                        }
                    }
                }

        );
    }


    public class ResponseModel {

        public class Result
        {
            private String userName;

            private String authToken;

            private String refreshToken;

            private int id;

            public void setUserName(String userName){
                this.userName = userName;
            }
            public String getUserName(){
                return this.userName;
            }
            public void setAuthToken(String authToken){
                this.authToken = authToken;
            }
            public String getAuthToken(){
                return this.authToken;
            }
            public void setRefreshToken(String refreshToken){
                this.refreshToken = refreshToken;
            }
            public String getRefreshToken(){
                return this.refreshToken;
            }
            public void setId(int id){
                this.id = id;
            }
            public int getId(){
                return this.id;
            }
        }



            private String version;

            private int statusCode;

            private String errorMessage;

            private Result result;

            private String timestamp;

            private List<String> errors;

            public void setVersion(String version){
                this.version = version;
            }
            public String getVersion(){
                return this.version;
            }
            public void setStatusCode(int statusCode){
                this.statusCode = statusCode;
            }
            public int getStatusCode(){
                return this.statusCode;
            }
            public void setErrorMessage(String errorMessage){
                this.errorMessage = errorMessage;
            }
            public String getErrorMessage(){
                return this.errorMessage;
            }
            public void setResult(Result result){
                this.result = result;
            }
            public Result getResult(){
                return this.result;
            }
            public void setTimestamp(String timestamp){
                this.timestamp = timestamp;
            }
            public String getTimestamp(){
                return this.timestamp;
            }
            public void setErrors(List<String> errors){
                this.errors = errors;
            }
            public List<String> getErrors(){
                return this.errors;
            }


    }


}