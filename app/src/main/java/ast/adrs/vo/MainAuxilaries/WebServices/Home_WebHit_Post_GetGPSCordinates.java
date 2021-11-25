package ast.adrs.vo.MainAuxilaries.WebServices;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.nio.charset.StandardCharsets;
import java.util.List;

import ast.adrs.vo.Utils.ApiMethod;
import ast.adrs.vo.Utils.AppConfig;
import ast.adrs.vo.Utils.AppConstt;
import ast.adrs.vo.Utils.IWebCallback;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;


public class Home_WebHit_Post_GetGPSCordinates {

    //object bnaya ha api k respnse ko website a dalny k bad
    // root class ka nam hatm kr dia ha
    //andr wali czain rhny di han
    public static ResponseModel responseObject = null;

    //http request ka object ha
    private final AsyncHttpClient mClient = new AsyncHttpClient();

    //context
    public Context mContext;


    //nam kuch b ho
    public void GetGPSCordinates(Context context, final IWebCallback iWebCallback,
                                               final String _signInEntity) {
        mContext = context;


        String myUrl = AppConfig.getInstance().getBaseUrlApi() + ApiMethod.POST.GetGPSCordinates;

        Log.d("LOG_AS", "GetGPSCordinates: " + myUrl + _signInEntity);

        StringEntity entity = null;

        entity = new StringEntity(_signInEntity, "UTF-8");

        mClient.setMaxRetriesAndTimeout(AppConstt.LIMIT_API_RETRY, AppConstt.LIMIT_TIMOUT_MILLIS);
        mClient.addHeader(ApiMethod.HEADER.Authorization_TOKEN, AppConfig.getInstance().mUser.getAuthorization());
        mClient.post(mContext, myUrl, entity, "application/json", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String strResponse;


                        try {
                            Gson gson = new Gson();
                            strResponse = new String(responseBody, StandardCharsets.UTF_8);
                            Log.d("LOG_AS", "GetGPSCordinates: strResponse" + strResponse);
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
                        try {   String strResponse;
                            Gson gson = new Gson();
                            strResponse = new String(responseBody, StandardCharsets.UTF_8);
                            Log.d("LOG_AS", "GetGPSCordinates: strResponse" + strResponse);}
                        catch (Exception e)
                        {}
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
            private String dateFrom;

            private String dateTo;

            private int areaCode;

            private String mouzaCode;

            private String mouzaName;

            private double latitude;

            private double longitude;

            private String species;

            private String disease;

            private int totalAnimals;

            private int sickAnimals;

            private int deadAnimals;

            private String dirReportId;

            public void setDateFrom(String dateFrom){
                this.dateFrom = dateFrom;
            }
            public String getDateFrom(){
                return this.dateFrom;
            }
            public void setDateTo(String dateTo){
                this.dateTo = dateTo;
            }
            public String getDateTo(){
                return this.dateTo;
            }
            public void setAreaCode(int areaCode){
                this.areaCode = areaCode;
            }
            public int getAreaCode(){
                return this.areaCode;
            }
            public void setMouzaCode(String mouzaCode){
                this.mouzaCode = mouzaCode;
            }
            public String getMouzaCode(){
                return this.mouzaCode;
            }
            public void setMouzaName(String mouzaName){
                this.mouzaName = mouzaName;
            }
            public String getMouzaName(){
                return this.mouzaName;
            }
            public void setLatitude(double latitude){
                this.latitude = latitude;
            }
            public double getLatitude(){
                return this.latitude;
            }
            public void setLongitude(double longitude){
                this.longitude = longitude;
            }
            public double getLongitude(){
                return this.longitude;
            }
            public void setSpecies(String species){
                this.species = species;
            }
            public String getSpecies(){
                return this.species;
            }
            public void setDisease(String disease){
                this.disease = disease;
            }
            public String getDisease(){
                return this.disease;
            }
            public void setTotalAnimals(int totalAnimals){
                this.totalAnimals = totalAnimals;
            }
            public int getTotalAnimals(){
                return this.totalAnimals;
            }
            public void setSickAnimals(int sickAnimals){
                this.sickAnimals = sickAnimals;
            }
            public int getSickAnimals(){
                return this.sickAnimals;
            }
            public void setDeadAnimals(int deadAnimals){
                this.deadAnimals = deadAnimals;
            }
            public int getDeadAnimals(){
                return this.deadAnimals;
            }
            public void setDirReportId(String dirReportId){
                this.dirReportId = dirReportId;
            }
            public String getDirReportId(){
                return this.dirReportId;
            }
        }


            private String version;

            private int statusCode;

            private String errorMessage;

            private List<Result> result;

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
            public void setResult(List<Result> result){
                this.result = result;
            }
            public List<Result> getResult(){
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