package com.example.baitap3_slider;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    CircleIndicator circleIndicator;
    List<ImagesSlider> imagesList = new ArrayList<>();
    APIService apiService;

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

        getListImages();


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

    private void getListImages(){
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        Call<MessageModel> call = apiService.LoadImageSlider(1);
        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                imagesList = new ArrayList<>(response.body().getResult()); // Cập nhật danh sách ảnh

                // Cập nhật ViewPager ngay sau khi có dữ liệu
                ImagesViewPageAdapter adapter = new ImagesViewPageAdapter(imagesList);
                viewPager.setAdapter(adapter);
                circleIndicator.setViewPager(viewPager);

                // Bắt đầu auto-scroll sau khi có dữ liệu
                handler.postDelayed(runnable, 3000);
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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