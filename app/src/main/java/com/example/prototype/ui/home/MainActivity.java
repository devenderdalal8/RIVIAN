package com.example.prototype.ui.home;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.prototype.R;
import com.example.prototype.databinding.ActivityMainBinding;
import com.example.prototype.databinding.DialogCarTempBinding;
import com.example.prototype.ui.home.adapter.ChargingStationAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;
    ChargingStationAdapter mAdapter;
    ObservableArrayList<ChargerModel> data;
    MainViewModel mainViewModel;
    BottomSheetDialog mBottomSheet;
    DialogCarTempBinding dialogCarTempBinding;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setAnimation();
        initializeViewModel();
        initializeBottomSheet();
        createStaticData();
        setAdapter();
        setOnClickListeners();
    }

    private void setAnimation() {
        animation = AnimationUtils.loadAnimation(this, R.anim.slide_out_anim);
        animation.reset();
        mBinding.tvMil.clearAnimation();
        mBinding.svBottom.clearAnimation();
        mBinding.tvMil.startAnimation(animation);
        mBinding.svBottom.startAnimation(animation);

    }

    private void initializeBottomSheet() {
        dialogCarTempBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_car_temp, null, false);
        mBottomSheet = new BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme);
        mBottomSheet.setContentView(dialogCarTempBinding.getRoot());
        mBottomSheet.setCanceledOnTouchOutside(true);
        mBottomSheet.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void initializeViewModel() {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    private void createStaticData() {
        data = new ObservableArrayList<>();
        data.add(new ChargerModel("100 N Mitsubishi Motorway, Normal, IL, 61761", "3 / 5 Available", "1.5mi"));
        data.add(new ChargerModel("Walmart Supercenter 2225 W Market St, Bloomington, IL, 61705", "4 / 5 Available", "2.5mi"));
        data.add(new ChargerModel("100 N Mitsubishi Motorway, Bloomington, IL, 61761", "2 / 5 Available", "3.0mi"));
    }

    private void setAdapter() {
        mAdapter = new ChargingStationAdapter(data);
        mBinding.includeBattery.rvChargingPoint.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mBinding.includeBattery.rvChargingPoint.setAdapter(mAdapter);
    }

    private void setOnClickListeners() {
        Animation bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_to_top);
        mBinding.includeMainBottom.cvEnergy.setOnClickListener(v -> {
            mBinding.includeMainBottom.getRoot().setVisibility(View.GONE);
            mBinding.tvMil.clearAnimation();
            mBinding.includeBattery.getRoot().clearAnimation();
            mBinding.svBottom.startAnimation(bottomAnimation);
            mBinding.includeBattery.getRoot().setVisibility(View.VISIBLE);
            mBinding.tvTitle.setText(getString(R.string.energyTitle));
            mBinding.ivSetting.setImageResource(R.drawable.ic_back_arrow_icon);
            mBinding.ivSetting.setClickable(true);
        });

        mBinding.ivSetting.setOnClickListener(view -> {
            mBinding.ivSetting.setClickable(false);
            mBinding.includeMainBottom.getRoot().setVisibility(View.VISIBLE);
            mBinding.includeBattery.getRoot().setVisibility(View.GONE);
            mBinding.tvMil.clearAnimation();
            mBinding.includeBattery.getRoot().clearAnimation();
            mBinding.svBottom.startAnimation(bottomAnimation);
            mBinding.tvTitle.setText(getString(R.string.carModel));
            mBinding.ivSetting.setImageResource(R.drawable.ic_baseline_settings_24);
            mBinding.hideMainGroup.setVisibility(View.VISIBLE);
        });

        mBinding.includeMainBottom.cvTemp.setOnClickListener(view -> {
            mBottomSheet.show();
            mBinding.ivSetting.setClickable(true);
            mBinding.tvTitle.setText(getString(R.string.aircon));
            mBinding.ivSetting.setImageResource(R.drawable.ic_back_arrow_icon);
        });
    }

}