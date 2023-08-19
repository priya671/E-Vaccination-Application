package com.example.e_vaccinationsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CenterRVAdapter extends RecyclerView.Adapter<CenterRVAdapter.ViewHolder> {

    private CenterRVModal[] centerList;

    private RecyclerViewListener listener;

    public CenterRVAdapter(List<CenterRVModal> centerList, RecyclerViewListener listener){
        this.centerList = centerList.toArray(new CenterRVModal[0]);
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView centerName = itemView.findViewById(R.id.txtcentername);
        TextView centerAddress = itemView.findViewById(R.id.txtcenterlocation);
        TextView centerTimings = itemView.findViewById(R.id.txtcentertimings);
        TextView vaccineName = itemView.findViewById(R.id.txtvaccinename);
        TextView vaccineFees = itemView.findViewById(R.id.txtvaccinefees);
        TextView ageLimit = itemView.findViewById(R.id.txtagelimit);
        TextView availability = itemView.findViewById(R.id.txtavailability);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.center_rv_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return centerList.length;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CenterRVModal center = centerList[position];
        holder.centerName.setText(center.centerName);
        holder.centerAddress.setText(center.centerAddress);
        holder.centerTimings.setText("From: " + center.centerFromTime + "To: " + center.centerToTime);
        holder.vaccineName.setText(center.vaccineName);
        holder.vaccineFees.setText(center.fee_type);
        holder.ageLimit.setText("Age Limit: " + center.ageLimit.toString());
        holder.availability.setText("Availability: " + center.availableCapacity.toString());

    }

    public interface RecyclerViewListener{
        void onClick(View v,int position);
    }

}