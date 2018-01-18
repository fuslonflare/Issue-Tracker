package csd.gisc.issuetracker.enums;

/**
 * Created by admin on 17/1/2561.
 */
public enum Status {
    New ("New"),
    InProgress ("InProgess"),
    Closed("Closed");

    private final String status;

    Status(String name) {
        this.status = name;
    }
}
