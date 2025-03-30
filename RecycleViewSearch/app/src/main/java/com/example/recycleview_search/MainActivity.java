package com.example.recycleview_search;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvIcon;
    private IconAdapter mIconAdapter;
    private List<IconModel> mIcons;

    private SearchView sView;

    private MainActivity mainActivity;
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

        rvIcon = findViewById(R.id.rcIcon);

        mIcons = new ArrayList<>();

        mIcons.add(new IconModel(R.drawable.icon1, "Shopee"));
        mIcons.add(new IconModel(R.drawable.icon2, "Shopee Pay"));
        mIcons.add(new IconModel(R.drawable.icon3, "Shop back"));
        mIcons.add(new IconModel(R.drawable.icon4, "Ninja Van"));
        mIcons.add(new IconModel(R.drawable.icon5, "Cho Tot"));
        mIcons.add(new IconModel(R.drawable.icon6, "Tiktok"));
        mIcons.add(new IconModel(R.drawable.icon7, "Tiki"));
        mIcons.add(new IconModel(R.drawable.icon8, "Giaohangtietkiem"));
        mIcons.add(new IconModel(R.drawable.icon9, "GiaoHangNhanh"));
        mIcons.add(new IconModel(R.drawable.icon1, "Shopee"));
        mIcons.add(new IconModel(R.drawable.icon2, "Shopee Pay"));
        mIcons.add(new IconModel(R.drawable.icon3, "Shop back"));
        mIcons.add(new IconModel(R.drawable.icon4, "Ninja Van"));
        mIcons.add(new IconModel(R.drawable.icon5, "Cho Tot"));
        mIcons.add(new IconModel(R.drawable.icon6, "Tiktok"));
        mIcons.add(new IconModel(R.drawable.icon7, "Tiki"));
        mIcons.add(new IconModel(R.drawable.icon8, "Giaohangtietkiem"));
        mIcons.add(new IconModel(R.drawable.icon9, "GiaoHangNhanh"));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        rvIcon.setLayoutManager((gridLayoutManager));
        mIconAdapter = new IconAdapter(this,mIcons);
        rvIcon.setAdapter(mIconAdapter);
        rvIcon.addItemDecoration(new LinePagerIndicatorDecoration(this));
        sView = (SearchView) findViewById(R.id.sView1);
        sView.clearFocus();
        sView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterListener(s);
                return true;
            }
        });
    }
    private void filterListener(String text){
        List<IconModel> list = new ArrayList<>();
        for(IconModel iconModel:mIcons){
            if(iconModel.getDesc().toLowerCase().contains(text.toLowerCase())){
                list.add(iconModel);
            }
        }
        if(list.isEmpty()){
            Toast.makeText(this,"Không có dữ liệu cần tìm",Toast.LENGTH_SHORT).show();

        }
        else{
            mIconAdapter.setListenerList(list);
        }
    }
}