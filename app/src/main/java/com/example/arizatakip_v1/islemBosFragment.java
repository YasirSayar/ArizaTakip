package com.example.arizatakip_v1;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class islemBosFragment extends Fragment {
    private ArizaAdapter adapter;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_islem_bos, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView RecyclerView = (RecyclerView) getView().findViewById(R.id.rv);
        RecyclerView.setHasFixedSize(true);
        RecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        sql_Veri tablo= new sql_Veri();


        adapter= new ArizaAdapter(requireContext(),tablo.VeriOlustur("ISLEME ALINDI"));
        RecyclerView.setAdapter(adapter);
    }


}