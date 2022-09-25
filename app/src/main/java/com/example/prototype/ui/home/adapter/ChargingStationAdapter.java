package com.example.prototype.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prototype.R;
import com.example.prototype.databinding.ItemChargingPointBinding;
import com.example.prototype.ui.home.ChargerModel;

import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_internal_builders_ViewModelComponentBuilder;

public class ChargingStationAdapter extends RecyclerView.Adapter<ChargingStationViewHolder> {
    ObservableArrayList<ChargerModel> data = new ObservableArrayList<>();
    Context mContext;

    public ChargingStationAdapter(ObservableArrayList<ChargerModel> chargerModels){
        this.data.addAll(chargerModels);
    }
    @NonNull
    @Override
    public ChargingStationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        ItemChargingPointBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_charging_point, parent, false);
        return new ChargingStationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChargingStationViewHolder holder, int position) {
        ChargerModel item = data.get(position);
        holder.mBinding.tvAddress.setText(item.getAddress());
        holder.mBinding.tvAvailable.setText(item.getAvailable());
        holder.mBinding.tvDistance.setText(item.getDistance());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}