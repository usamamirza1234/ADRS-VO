package ast.adrs.vo.IntroAuxilaries.WebServices;

import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.nio.charset.StandardCharsets;


import cz.msebera.android.httpclient.Header;


public class Home_WebHit_Get_Database {


    private final AsyncHttpClient mClient = new AsyncHttpClient();

    public void getDatabase(final IWebCallback iWebCallback) {

        String myUrl;
        myUrl = AppConfig.getInstance().getBaseUrlApi() + ApiMethod.GET.getDatabase;


        Log.d("LOG_AS", "getDatabase: " + myUrl +" " +AppConfig.getInstance().mUser.getAuthorization());
        mClient.addHeader(
                ApiMethod.HEADER.Authorization,
                ApiMethod.HEADER.Bearer +
                        AppConfig.getInstance().mUser.getAuthorization());

        mClient.addHeader(ApiMethod.HEADER.Lang, AppConfig.getInstance().loadDefLanguage());
  
        mClient.setMaxRetriesAndTimeout(AppConstt.LIMIT_API_RETRY, AppConstt.LIMIT_TIMOUT_MILLIS);
        mClient.get(myUrl, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String strResponse;
                        try {
                            Gson gson = new Gson();
                            strResponse = new String(responseBody, StandardCharsets.UTF_8);
                            Log.d("LOG_AS", "onSuccess: " + strResponse);



                            if (strResponse != "") {
                                iWebCallback.onWebResult(true, strResponse);
                            }


//                            switch (statusCode) {
//
//                                case AppConstt.ServerStatus.OK:
//
//
//
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
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable
                            error) {


                        String strResponse;
                        try {
                            Gson gson = new Gson();
                            strResponse = new String(responseBody, StandardCharsets.UTF_8);

                            Log.d("LOG_AS","onFailure: "+ strResponse);

                        } catch (Exception ex) {

                        }



                        switch (statusCode) {
                            case AppConstt.ServerStatus.NETWORK_ERROR:
                                iWebCallback.onWebResult(false, AppConfig.getInstance().getNetworkErrorMessage());
                                break;

                            case AppConstt.ServerStatus.FORBIDDEN:
                            case AppConstt.ServerStatus.UNAUTHORIZED:
                            default:
                                iWebCallback.onWebResult(false, "");

//                                AppConfig.getInstance().parsErrorMessage(iWebCallback, responseBody);
                                break;
                        }
                    }
                }

        );
    }





}
