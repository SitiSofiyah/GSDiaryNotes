package com.example.intel.admin_diary.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intel.admin_diary.MainActivity;
import com.example.intel.admin_diary.Model.Mood;
import com.example.intel.admin_diary.R;
import com.example.intel.admin_diary.Rest.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodViewHolder> {
    List<Mood> listMood;

    public MoodAdapter(List<Mood> listMood) {
        this.listMood = listMood;
    }

    @Override
    public MoodAdapter.MoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mood, parent, false);
        MoodViewHolder mHolder = new MoodViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(MoodAdapter.MoodViewHolder holder,final int position) {
        holder.idMood.setText(listMood.get(position).getIdMood());
        if (listMood.get(position).getMood() != "" ){
            Picasso.with(holder.itemView.getContext()).load(ApiClient.BASE_URL+listMood.get(position).getMood()).into(holder.mood);
//            Glide.with(holder.itemView.getContext()).load(listMood.get(position).getMood()).into(holder.mood);
        } else {
            //Picassp.with(holder.itemView.getContext()).load(R.drawable.photoid).into(holder.mPhotoURL);
            Glide.with(holder.itemView.getContext()).load(R.drawable.ic_launcher_background).into(holder.mood);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                intent.putExtra("id_mood", listMood.get(position).getIdMood());
                intent.putExtra("mood", listMood.get(position).getIdMood());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listMood.size();
    }

    public class MoodViewHolder extends RecyclerView.ViewHolder{
        ImageView mood;
        TextView idMood;
        public MoodViewHolder(View itemView) {
            super(itemView);
            mood = (ImageView) itemView.findViewById(R.id.mood);
            idMood = (TextView) itemView.findViewById(R.id.idMood);
        }
    }
}
