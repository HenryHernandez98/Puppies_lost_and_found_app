package sigaplication.nicadeveloper.com.sig_aplication.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import sigaplication.nicadeveloper.com.sig_aplication.R;
import sigaplication.nicadeveloper.com.sig_aplication.models.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> user;
    private Context context;

    public UserAdapter(Context context, List<User> user) {
        this.user = user;
        this.context = context;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView recyclerView;
        private CircleImageView userimage;
        private TextView name;
        private TextView username;
        private TextView email;
        private Button editProfile;
        public CardView cardView;
        public SimpleDraweeView draweeView;

        public ViewHolder (View view){
            super (view);
            name = view.findViewById(R.id.nameView);
            username = view.findViewById(R.id.userNameView);
            email = view.findViewById(R.id.emailView);
            editProfile = view.findViewById(R.id.edit_button);
            cardView = view.findViewById(R.id.card_view_profile);
            draweeView = (SimpleDraweeView) view.findViewById(R.id.image);
        }
    }
    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_profile, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        final User post = user.get(position);
        holder.email.setText(post.getEmail());

    }

    @Override
    public int getItemCount() {
        return user.size();
    }



}
