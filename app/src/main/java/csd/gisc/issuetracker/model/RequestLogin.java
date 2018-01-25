package csd.gisc.issuetracker.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 16/1/2561.
 */

public class RequestLogin {
    @SerializedName("username") private String username;
    @SerializedName("password") private String password;
    @SerializedName("GroupID") private int groupId;

    public RequestLogin(String username, String password, int groupId) {
        this.username = username;
        this.password = password;
        this.groupId = groupId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
