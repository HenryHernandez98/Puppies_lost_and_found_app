package sigaplication.nicadeveloper.com.sig_aplication.ui.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import sigaplication.nicadeveloper.com.sig_aplication.R;
import sigaplication.nicadeveloper.com.sig_aplication.model.Image;

public class ComplaintImageAdapter  extends RecyclerView.Adapter<ComplaintImageAdapter.ViewHolder>{
    private List<Image> picturesList;
    private Context context;

    public ComplaintImageAdapter(List<Image> picturesList1, Context context1) {
        this.picturesList = picturesList1;
        this.context = context1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public SimpleDraweeView draweeView;
        public ViewHolder (View view){
            super(view);
            draweeView = (SimpleDraweeView) view.findViewById(R.id.image);
        }
    }

    @NonNull
    @Override
    public ComplaintImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_complaint, parent, false);
        return new ComplaintImageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintImageAdapter.ViewHolder holder, int position) {
        final Image pic = picturesList.get(position);
        Uri uri = Uri.parse(pic.getUrl());
        holder.draweeView.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return picturesList.size();
    }
}
