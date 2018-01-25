package csd.gisc.issuetracker.manager.http;

import csd.gisc.issuetracker.model.RequestLogin;
import csd.gisc.issuetracker.model.Response;
import csd.gisc.issuetracker.model.ResultLogin;
import csd.gisc.issuetracker.model.ResultUserInfo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by admin on 16/1/2561.
 */

public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("rest/auth/loginGroup")
    Call<Response<ResultLogin>> login(@Body RequestLogin loginRequest);

    @GET("rest/group/{groupId}/users/{employeeId}")
    Call<Response<ResultUserInfo>> getEmployeeInfo(@Header("token_user") String tokenUser,
                                                  @Path("groupId") String groupId,
                                                  @Path("employeeId") String employeeId);
}
