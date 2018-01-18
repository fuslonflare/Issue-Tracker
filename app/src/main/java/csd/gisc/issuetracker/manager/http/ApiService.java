package csd.gisc.issuetracker.manager.http;

import csd.gisc.issuetracker.model.CredentialDao;
import csd.gisc.issuetracker.model.ResponseDao;
import csd.gisc.issuetracker.model.ResultCredentialDao;
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
    Call<ResponseDao<ResultCredentialDao>> login(@Body CredentialDao loginRequest);
}
