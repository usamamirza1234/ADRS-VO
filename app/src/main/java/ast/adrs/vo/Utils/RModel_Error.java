package ast.adrs.vo.Utils;

import java.util.List;

public class RModel_Error {
    private String version;

    private int statusCode;

    private String errorMessage;

    private String result;

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
    public void setResult(String result){
        this.result = result;
    }
    public String getResult(){
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
