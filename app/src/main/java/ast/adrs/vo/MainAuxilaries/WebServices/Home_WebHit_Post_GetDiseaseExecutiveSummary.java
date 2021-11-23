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


public class Home_WebHit_Post_GetDiseaseExecutiveSummary {

    //object bnaya ha api k respnse ko website a dalny k bad
    // root class ka nam hatm kr dia ha
    //andr wali czain rhny di han
    public static ResponseModel responseObject = null;

    //http request ka object ha
    private final AsyncHttpClient mClient = new AsyncHttpClient();

    //context
    public Context mContext;


    //nam kuch b ho
    public void PostGetDiseaseExecutiveSummary(Context context, final IWebCallback iWebCallback,
                                               final String _signInEntity) {
        mContext = context;


        String myUrl = AppConfig.getInstance().getBaseUrlApi() + ApiMethod.POST.GetDiseaseExecutiveSummary;

        Log.d("LOG_AS", "PostGetDiseaseExecutiveSummary: " + myUrl + _signInEntity);

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
                            Log.d("LOG_AS", "PostGetDiseaseExecutiveSummary: strResponse" + strResponse);
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

        private String version;
        private int statusCode;
        private String errorMessage;
        private Result result;
        private String timestamp;
        private List<String> errors;

        public String getVersion() {
            return this.version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getStatusCode() {
            return this.statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getErrorMessage() {
            return this.errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public Result getResult() {
            return this.result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        public String getTimestamp() {
            return this.timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public List<String> getErrors() {
            return this.errors;
        }

        public void setErrors(List<String> errors) {
            this.errors = errors;
        }

        public class DiseaseSummaryDetailViewModel {
            private String diseaseName;

            private int di;

            private int dr;

            public String getDiseaseName() {
                return this.diseaseName;
            }

            public void setDiseaseName(String diseaseName) {
                this.diseaseName = diseaseName;
            }

            public int getDi() {
                return this.di;
            }

            public void setDi(int di) {
                this.di = di;
            }

            public int getDr() {
                return this.dr;
            }

            public void setDr(int dr) {
                this.dr = dr;
            }
        }

        public class AnimalPopulation {
            private String name;

            private int totalAnimals;

            private int sickAnimals;

            private int deadAnimals;

            public String getName() {
                return this.name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getTotalAnimals() {
                return this.totalAnimals;
            }

            public void setTotalAnimals(int totalAnimals) {
                this.totalAnimals = totalAnimals;
            }

            public int getSickAnimals() {
                return this.sickAnimals;
            }

            public void setSickAnimals(int sickAnimals) {
                this.sickAnimals = sickAnimals;
            }

            public int getDeadAnimals() {
                return this.deadAnimals;
            }

            public void setDeadAnimals(int deadAnimals) {
                this.deadAnimals = deadAnimals;
            }
        }

        public class Result {
            private List<DiseaseSummaryDetailViewModel> diseaseSummaryDetailViewModel;

            private List<AnimalPopulation> animalPopulation;

            public List<DiseaseSummaryDetailViewModel> getDiseaseSummaryDetailViewModel() {
                return this.diseaseSummaryDetailViewModel;
            }

            public void setDiseaseSummaryDetailViewModel(List<DiseaseSummaryDetailViewModel> diseaseSummaryDetailViewModel) {
                this.diseaseSummaryDetailViewModel = diseaseSummaryDetailViewModel;
            }

            public List<AnimalPopulation> getAnimalPopulation() {
                return this.animalPopulation;
            }

            public void setAnimalPopulation(List<AnimalPopulation> animalPopulation) {
                this.animalPopulation = animalPopulation;
            }
        }


    }
}