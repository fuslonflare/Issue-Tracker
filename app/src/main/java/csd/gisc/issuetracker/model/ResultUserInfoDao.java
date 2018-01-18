package csd.gisc.issuetracker.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 16/1/2561.
 */

public class ResultUserInfoDao {
    @SerializedName("cell_phone")
    private String cellPhone;
    @SerializedName("company")
    private String company;
    @SerializedName("email")
    private String email;
    @SerializedName("employee_id")
    private String employeeId;
    @SerializedName("eng_name")
    private String engName;
    @SerializedName("group")
    private String group;
    @SerializedName("group_id")
    private String groupId;
    @SerializedName("nick_name")
    private String nickName;
    @SerializedName("position")
    private String position;
    @SerializedName("profile_image")
    private String profileImage;
    @SerializedName("thai_name")
    private String thaiName;
    @SerializedName("unit_code")
    private String unitCode;

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getThaiName() {
        return thaiName;
    }

    public void setThaiName(String thaiName) {
        this.thaiName = thaiName;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
}
