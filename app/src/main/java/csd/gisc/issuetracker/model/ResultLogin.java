package csd.gisc.issuetracker.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 16/1/2561.
 */

public class ResultLogin {
    @SerializedName("employee_id")
    private String employeeId;
    @SerializedName("group_id")
    private String groupId;
    @SerializedName("token_map")
    private String tokenMap;
    @SerializedName("token_user")
    private String tokenUser;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTokenMap() {
        return tokenMap;
    }

    public void setTokenMap(String tokenMap) {
        this.tokenMap = tokenMap;
    }

    public String getTokenUser() {
        return tokenUser;
    }

    public void setTokenUser(String tokenUser) {
        this.tokenUser = tokenUser;
    }
}
