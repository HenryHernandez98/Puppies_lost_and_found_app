package sigaplication.nicadeveloper.com.sig_aplication.api;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import sigaplication.nicadeveloper.com.sig_aplication.models.complaint;

public interface ApiInterface {
    //Method GET
    @GET("complaint")
    Call<List<complaint>> getComplaints(@Header("Authorization") String authorization);

    //Method POST
}
