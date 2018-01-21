package csd.gisc.issuetracker.manager.http;

import csd.gisc.issuetracker.model.RequestLogin;
import csd.gisc.issuetracker.model.ResponseLogin;
import csd.gisc.issuetracker.model.ResultLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by admin on 16/1/2561.
 */

public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("rest/auth/loginGroup")
    Call<ResponseLogin<ResultLogin>> login(@Body RequestLogin loginRequest);
}
