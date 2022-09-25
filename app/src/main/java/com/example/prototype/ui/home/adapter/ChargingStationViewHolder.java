package com.example.prototype.ui.home.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prototype.databinding.ItemChargingPointBinding;

public class ChargingStationViewHolder extends RecyclerView.ViewHolder {
    ItemChargingPointBinding mBinding;
    public ChargingStationViewHolder(@NonNull ItemChargingPointBinding itemView) {
        super(itemView.getRoot());
        this.mBinding = itemView;
    }
}