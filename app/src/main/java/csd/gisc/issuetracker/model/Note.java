package csd.gisc.issuetracker.model;

import java.util.Locale;

/**
 * Created by admin on 18/1/2561.
 */

public class Note {
    private String createBy;
    private int createTime;
    private String detail;

    public Note() {
    }

    public Note(String createBy, int createTime, String detail) {
        this.createBy = createBy;
        this.createTime = createTime;
        this.detail = detail;
    }

    public String getCreateBy() {
        return createBy;
    }

    public int getCreateTime() {
        return createTime;
    }

    public String getDetail() {
        return detail;
    }

    public String getDateTime(int epoch) {
        return new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US)
                .format(new java.util.Date(epoch * 1000));
    }
}
