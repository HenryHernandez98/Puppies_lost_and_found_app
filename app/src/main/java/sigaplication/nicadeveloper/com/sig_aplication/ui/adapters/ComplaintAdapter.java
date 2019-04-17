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
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import sigaplication.nicadeveloper.com.sig_aplication.R;
import sigaplication.nicadeveloper.com.sig_aplication.model.Complaint;
import sigaplication.nicadeveloper.com.sig_aplication.ui.activities.DetailComplaintActivity;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ViewHolder> {
    private List<Complaint> complaint;
    private Context context;

    public ComplaintAdapter(List<Complaint> complaint, Context context) {
        this.complaint = complaint;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView petName;
        private TextView city;
        private TextView date;
        private CardView cardView;
        private TextView id;
        private Button share;
        private Button like;
        private SimpleDraweeView draweeView;


        public ViewHolder (View view){
            super (view);
            petName = view.findViewById(R.id.pet_name);
            city = view.findViewById(R.id.pet_city);
            date = view.findViewById(R.id.pet_date);
            id = view.findViewById(R.id.userName);
            share = view.findViewById(R.id.share);
            like = view.findViewById(R.id.likes);
            //save = view.findViewById(R.id.saveDB); guardar en BD, aún en prueba
            cardView = view.findViewById(R.id.card_view);
            draweeView = (SimpleDraweeView) view.findViewById(R.id.image);
        }

    }

    @NonNull
    @Override
    public ComplaintAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_complaint, parent, false);
        return new ComplaintAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ComplaintAdapter.ViewHolder holder, final int position) {
        final Complaint post = complaint.get(position);
        holder.petName.setText(post.getPetname());
        holder.city.setText(post.getCity());
        holder.date.setText(post.getDate());
        /*holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailComplaintActivity.class);
                context.startActivity(intent);
            }
        });*/



        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"likes +1",Toast.LENGTH_LONG).show();
            }
        });

        /*holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareSubject = "Pets";
                String shareText = "Esta mascota se encuentra perdida";
                String shareTitle = "Compartido vía:";
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                context.startActivity(Intent.createChooser(shareIntent,shareTitle));
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return complaint.size();
    }


}
