package com.example.baitap1_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.baitap1_fragment.databinding.FragmentNeworderBinding;

import java.io.FileReader;

public class DanhGiaFragment extends Fragment {
    FragmentNeworderBinding binding;

    public DanhGiaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentNeworderBinding.inflate(inflater, container, false);

        // recyclerView

        return binding.getRoot();
    }

}
