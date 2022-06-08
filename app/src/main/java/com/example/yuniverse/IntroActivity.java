package com.example.yuniverse;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.yuniverse.adapter.IntroViewPagerAdapter;
import com.example.yuniverse.model.ScreenItem;
import com.google.android.material.button.MaterialButton;


import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager2 screenPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private List<ScreenItem> myList;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton onBoardingActionButton;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        layoutOnboardingIndicators = findViewById(R.id.layoutOnboardingIndicators);
        onBoardingActionButton = findViewById(R.id.onBoardingActionButton);

        // fill list screen
        myList = new ArrayList<>();

        myList.add(new ScreenItem("Üniversiteni keşfet!","Yaşayan pek çok kişi ölümü hak eder. Ölülerden bazıları da yaşamı. Yaşamı onlara verebilir misin? Ölüm hakkında karar vermekte aceleci olma. En bilgeler bile her sonucu bilemez",R.drawable.img1));
        myList.add(new ScreenItem("Etkinlikleri takip et!","Yaşayan pek çok kişi ölümü hak eder. Ölülerden bazıları da yaşamı. Yaşamı onlara verebilir misin? Ölüm hakkında karar vermekte aceleci olma. En bilgeler bile her sonucu bilemez",R.drawable.img2));
        myList.add(new ScreenItem("Topluluklara katıl!","Yaşayan pek çok kişi ölümü hak eder. Ölülerden bazıları da yaşamı. Yaşamı onlara verebilir misin? Ölüm hakkında karar vermekte aceleci olma. En bilgeler bile her sonucu bilemez",R.drawable.img3));

        // Setup ViewPager
        screenPager = findViewById(R.id.screenViewPager);

        introViewPagerAdapter = new IntroViewPagerAdapter(getApplicationContext(),myList);

        screenPager.setAdapter(introViewPagerAdapter);

        setUpOnboardingIndicators();

        //setCurrentOnboardingIndicator(0); initial value for indicator (!!!!)

        //Indicatör'ü anlık olarak görmek için

     screenPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
         @Override
         public void onPageSelected(int position) {
             super.onPageSelected(position);
             setCurrentOnboardingIndicator(position);
         }
     });

     // Start ya da Next olma durumunu kontrol ediyoruz.
     onBoardingActionButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(screenPager.getCurrentItem() + 1 < introViewPagerAdapter.getItemCount()){
                 screenPager.setCurrentItem(screenPager.getCurrentItem()+1);
             }else{

                 startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                 finish();

             }
         }
     });
    }

    public void setUpOnboardingIndicators(){

        ImageView [] indicators = new ImageView[introViewPagerAdapter.getItemCount()];

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8,0,8,0);

        for(int i = 0; i < indicators.length; i++){

            indicators[i] = new ImageView(getApplicationContext());

            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive));

            indicators[i].setLayoutParams(layoutParams);

            layoutOnboardingIndicators.addView(indicators[i]);
        }

    }


    public void setCurrentOnboardingIndicator(int index){

        int childCount = layoutOnboardingIndicators.getChildCount();

        for(int i = 0; i < childCount; i++){

          ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
          if(i==index){
              imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active));
          }
          else{
              imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive));
          }
        }


        if(index == introViewPagerAdapter.getItemCount() - 1){
            onBoardingActionButton.setText("Start");
        }else{
            onBoardingActionButton.setText("Next");
        }
    }


}