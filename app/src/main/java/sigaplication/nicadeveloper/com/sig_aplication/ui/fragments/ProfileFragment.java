package sigaplication.nicadeveloper.com.sig_aplication.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tumblr.remember.Remember;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sigaplication.nicadeveloper.com.sig_aplication.R;
import sigaplication.nicadeveloper.com.sig_aplication.api.Api;
import sigaplication.nicadeveloper.com.sig_aplication.models.User;
import sigaplication.nicadeveloper.com.sig_aplication.ui.adapters.UserAdapter;


public class ProfileFragment extends Fragment {
    private RecyclerView recyclerView;
    private CircleImageView userimage;
    private TextView name;
    private TextView username;
    private TextView email;
    private Button editProfile;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(view);
        Fresco.initialize(getContext());
        return view;
        // return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_profile);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //userimage = view.findViewById(R.id.imgProfile);
        name = view.findViewById(R.id.nameView);
        username = view.findViewById(R.id.userNameView);
        email = view.findViewById(R.id.emailView);

        Call<List<User>> userCall = Api.instance().getUserInfo(Remember.getString("access_token",""));
        userCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    UserAdapter userAdapter = new UserAdapter(getContext(),response.body());
                    recyclerView.setAdapter(userAdapter);

                }else{
                    Toast.makeText(getContext(),"Error to show",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });


      //  callUser();

    }

   /* private void callUser() {
        Call<List<userPicture>> upCall = Api.instance().getImageProfile(Remember.getString("access_toke",""));
        upCall.enqueue(new Callback<List<userPicture>>() {
            @Override
            public void onResponse(Call<List<userPicture>> call, Response<List<userPicture>> response) {
                if(response.isSuccessful()){

                }else{
                    Toast.makeText(getContext(),"An Error occurred while userImageCall was doing",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<userPicture>> call, Throwable t) {
                Log.e("Error","An Error occurred",t);
            }
        });

    }*/
}
