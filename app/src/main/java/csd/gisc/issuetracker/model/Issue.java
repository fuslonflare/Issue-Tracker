package csd.gisc.issuetracker.model;

import csd.gisc.issuetracker.enums.Status;

/**
 * Created by admin on 17/1/2561.
 */

public class Issue {
    private String assignTo;
    private String detail;
    private int id;
    private String productName;
    private Status status;

    public Issue() {
    }

    public Issue(String assignTo, String detail, int id, String productName, Status status) {
        this.assignTo = assignTo;
        this.detail = detail;
        this.id = id;
        this.productName = productName;
        this.status = status;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public String getDetail() {
        return detail;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public Status getStatus() {
        return status;
    }
}

