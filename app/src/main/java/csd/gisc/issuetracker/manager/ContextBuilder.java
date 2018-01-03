package csd.gisc.issuetracker.manager;

import android.content.Context;

/**
 * Created by admin on 22/12/2560.
 */

public class ContextBuilder {

    private static ContextBuilder instance;
    private Context mContext;

    private ContextBuilder() {
        // default constructor
    }

    public static ContextBuilder getInstance() {
        if (instance == null)
            instance = new ContextBuilder();
        return instance;
    }

    public void init(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }
}
