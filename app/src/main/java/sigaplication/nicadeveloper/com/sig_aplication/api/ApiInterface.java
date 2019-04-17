package sigaplication.nicadeveloper.com.sig_aplication.api;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import sigaplication.nicadeveloper.com.sig_aplication.model.Complaint;
import sigaplication.nicadeveloper.com.sig_aplication.model.Profile;
import sigaplication.nicadeveloper.com.sig_aplication.model.User;
import sigaplication.nicadeveloper.com.sig_aplication.model.UserPicture;
import sigaplication.nicadeveloper.com.sig_aplication.model.AccessToken;

public interface ApiInterface {
    //Method GET
    @GET("Complaints")
    Call<List<Complaint>> getComplaints(@Header("Authorization") String authorization);
    @GET("Users")
    Call<List<User>> getUserInfo(@Header("Authorization") String authorization);
/*
    @GET("Profiles")
    Call<List<Profile>> getUserInfo(@Header("Authorization") String authorization);*/

    @GET("userPictures")
    Call<List<UserPicture>> getImageProfile(@Header("Authorization") String authorization);

    //Method POST
    @POST("Users")
    Call<User> saveUser(@Body User user);

    @POST("Users/login")
    Call<AccessToken> login(@Body User user);

    @POST("Profiles")
    Call<Profile> createProfile(@Body Profile profile);
}
