package com.example.yuniverse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yuniverse.R;
import com.example.yuniverse.model.ScreenItem;

import java.util.List;

public class IntroViewPagerAdapter extends RecyclerView.Adapter<IntroViewPagerAdapter.OnboardingViewHolder>{

    Context mContext;
    List<ScreenItem> myListScreen;

    public IntroViewPagerAdapter(Context mContext, List<ScreenItem> myListScreen) {
        this.mContext = mContext;
        this.myListScreen = myListScreen;
    }



    public class OnboardingViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgSlide;
        public TextView title;
        public TextView description;

        public OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSlide = itemView.findViewById(R.id.intro_img);
            title = itemView.findViewById(R.id.intro_title);
            description = itemView.findViewById(R.id.intro_description);

        }
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_screen,parent,false);
        return new OnboardingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {

        ScreenItem screenItem = myListScreen.get(position);

       holder.imgSlide.setImageResource(screenItem.getScreenImg());
       holder.title.setText(screenItem.getTitle());
       holder.description.setText(screenItem.getDescription());

    }

    @Override
    public int getItemCount() {
        return myListScreen.size();
    }

}

/*

  LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layoutScreen = inflater.inflate(R.layout.layout_screen,null);

        ImageView imgSlide = layoutScreen.findViewById(R.id.intro_img);
        TextView title = layoutScreen.findViewById(R.id.intro_title);
        TextView description = layoutScreen.findViewById(R.id.intro_description);

        title.setText(myListScreen.get(position).getTitle());
        description.setText(myListScreen.get(position).getDescription());
        imgSlide.setImageResource(myListScreen.get(position).getScreenImg());
 */
