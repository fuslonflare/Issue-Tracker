package csd.gisc.issuetracker.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 16/1/2561.
 */

public class Response<T> {
    @SerializedName("error_code")
    private String errorCode;
    @SerializedName("error_msg")
    private String errorMsg;
    @SerializedName("result")
    private List<T> result;
    @SerializedName("timestamp")
    private int timestamp;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
