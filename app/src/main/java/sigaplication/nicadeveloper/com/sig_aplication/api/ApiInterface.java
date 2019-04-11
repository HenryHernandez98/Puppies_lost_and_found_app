package sigaplication.nicadeveloper.com.sig_aplication.api;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import sigaplication.nicadeveloper.com.sig_aplication.models.Complaint;

public interface ApiInterface {
    //Method GET
    @GET("Complaint")
    Call<List<Complaint>> getComplaints(@Header("Authorization") String authorization);

    //Method POST
}
