package sigaplication.nicadeveloper.com.sig_aplication.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sigaplication.nicadeveloper.com.sig_aplication.R;
import sigaplication.nicadeveloper.com.sig_aplication.api.Api;
import sigaplication.nicadeveloper.com.sig_aplication.constants.Constants;
import sigaplication.nicadeveloper.com.sig_aplication.model.Complaint;
import sigaplication.nicadeveloper.com.sig_aplication.ui.adapters.ComplaintAdapter;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton newComplaintButton;

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Fresco.initialize(getContext());
        init(view);
        return view;
    }
    public void init(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_home);
        newComplaintButton = view.findViewById(R.id.fab_add_complaint);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        Call<List<Complaint>> call = Api.instance().getComplaints(Remember.getString(Constants.ACCESS_TOKEN,""));
        call.enqueue(new Callback<List<Complaint>>() {
            @Override

            public void onResponse(Call<List<Complaint>> call, Response<List<Complaint>> response) {
                if(response.body()!=null){
                    setAdapter(response.body());

                }else{
                    Toast.makeText(getContext(),"Error al mostrar denuncias",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Complaint>> call, Throwable t) {
                Log.e("Err","Error trying to connect to the API", t);
            }
        });
    }

    private void setAdapter(List<Complaint> complaints) {
        ComplaintAdapter adapter = new ComplaintAdapter(complaints, getActivity());
        recyclerView.setAdapter(adapter);
    }

}
