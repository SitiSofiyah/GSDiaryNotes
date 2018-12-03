package com.example.intel.admin_diary.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.intel.admin_diary.LayarRinci;
import com.example.intel.admin_diary.Model.Lokasi;
import com.example.intel.admin_diary.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Lokasi> mLokasiList;

    public MyAdapter(List<Lokasi> lokasiList) {
        mLokasiList = lokasiList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        holder.mTextViewIdLokasi.setText("Id Lokasi :  " + mLokasiList.get(position)
                .getId_lokasi());
        holder.mTextViewLokasi.setText("Lokasi :  " + mLokasiList.get(position)
                .getLokasi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(v.getContext(), LayarRinci.class);
                mIntent.putExtra("id_lokasi",mLokasiList.get(position).getId_lokasi());
                mIntent.putExtra("lokasi",mLokasiList.get(position).getLokasi());
                v.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLokasiList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewIdLokasi, mTextViewLokasi;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewIdLokasi = (TextView) itemView.findViewById(R.id.tvIdLokasi);
            mTextViewLokasi = (TextView) itemView.findViewById(R.id.tvLokasi);

        }
    }





}
