package sigaplication.nicadeveloper.com.sig_aplication.api;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import sigaplication.nicadeveloper.com.sig_aplication.models.Profile;
import sigaplication.nicadeveloper.com.sig_aplication.models.User;
import sigaplication.nicadeveloper.com.sig_aplication.models.accesToken;
import sigaplication.nicadeveloper.com.sig_aplication.models.complaint;
import sigaplication.nicadeveloper.com.sig_aplication.models.userPicture;

    Call<List<complaint>> getComplaints(@Header("Authorization") String authorization);
    @GET("Users")
    Call<List<User>> getUserInfo(@Header("Authorization") String authorization);
/*
    @GET("Profiles")
    Call<List<Profile>> getUserInfo(@Header("Authorization") String authorization);*/

    @GET("userPictures")
    Call<List<userPicture>> getImageProfile(@Header("Authorization") String authorization);

}
