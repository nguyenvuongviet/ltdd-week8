package com.example.slide_images_with_viewpager_indicator;

import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    CircleIndicator circleIndicator;
    List<Images> imagesList;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(viewPager.getCurrentItem() == imagesList.size()-1) {
                viewPager.setCurrentItem(0);
            }else {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewPager = findViewById(R.id.viewpage);
        circleIndicator = findViewById(R.id.circle_indicator);

        imagesList = getListImages();
        ImagesViewPageAdapter adapter = new ImagesViewPageAdapter(imagesList);
        viewPager.setAdapter(adapter);

        circleIndicator.setViewPager(viewPager);

        // gọi runnable
        handler.postDelayed(runnable, 3000);

        // lắng nghe viewpager chuyển trang
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private List<Images> getListImages(){
        List<Images> list = new ArrayList<>();
        list.add(new Images(R.drawable.coffee));
        list.add(new Images(R.drawable.quangcao));
        list.add(new Images(R.drawable.companypizza));
        return list;
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable,3000);
    }
}