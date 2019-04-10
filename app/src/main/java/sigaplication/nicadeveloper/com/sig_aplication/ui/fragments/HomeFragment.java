package sigaplication.nicadeveloper.com.sig_aplication.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.tumblr.remember.Remember;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sigaplication.nicadeveloper.com.sig_aplication.R;
import sigaplication.nicadeveloper.com.sig_aplication.api.Api;
import sigaplication.nicadeveloper.com.sig_aplication.models.complaint;
import sigaplication.nicadeveloper.com.sig_aplication.ui.adapters.ComplaintAdapter;
import sigaplication.nicadeveloper.com.sig_aplication.ui.adapters.ComplaintImageAdapter;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView petName;
    private TextView city;
    private TextView date;

    public HomeFragment() {
        // Required empty public constructor
    }

   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Fresco.initialize(getContext());
        init(view);
        return view;
      //  return inflater.inflate(R.layout.fragment_home, container, false);
    }
    public void init(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_home);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        petName = view.findViewById(R.id.pet_name);
        city = view.findViewById(R.id.pet_city);
        date = view.findViewById(R.id.pet_date);
        Call<List<complaint>> complaintCall = Api.instance().getComplaints(Remember.getString("access_token",""));
        complaintCall.enqueue(new Callback<List<complaint>>() {
            @Override
            public void onResponse(Call<List<complaint>> call, Response<List<complaint>> response) {
                if(response.isSuccessful()){
                    ComplaintAdapter complaintAdapter = new ComplaintAdapter(getContext(),response.body());
                    recyclerView.setAdapter(complaintAdapter);

                }else{
                    Toast.makeText(getContext(),"Error to show",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<complaint>> call, Throwable t) {
                Log.e("Err","Error trying to connect to the API", t);
            }
        });

    }

}
