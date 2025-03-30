package com.example.baitap3_slider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImagesViewPageAdapter extends PagerAdapter {
    private List<ImagesSlider> imagesList;

    public ImagesViewPageAdapter(List<ImagesSlider> imagesList) {
        this.imagesList = imagesList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_images, container, false);
        ImageView imageView = view.findViewById(R.id.imgView);
        ImagesSlider images = imagesList.get(position);
        Glide.with(container.getContext()).load(images.getAvatar()).into(imageView);

        // add view
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if (imagesList != null) {
            return imagesList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
