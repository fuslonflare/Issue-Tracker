package csd.gisc.issuetracker;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

import csd.gisc.issuetracker.manager.ContextBuilder;

/**
 * Created by admin on 22/12/2560.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ContextBuilder.getInstance().init(getApplicationContext());
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
