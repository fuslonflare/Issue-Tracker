package csd.gisc.issuetracker.manager;

import android.content.Context;

import csd.gisc.issuetracker.manager.http.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 16/1/2561.
 */

public class HttpManager {
    private static final String URL = "http://www2.cdg.co.th/WebServiceMobile/MobileISWebService.svc/";

    private static HttpManager instance;
    private Context mContext;
    private ApiService service;

    private HttpManager() {
        mContext = ContextBuilder.getInstance().getContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
    }

    public static HttpManager getInstance() {
        if (instance == null) {
            instance = new HttpManager();
        }
        return instance;
    }

    public ApiService getService() {
        return service;
    }
}
