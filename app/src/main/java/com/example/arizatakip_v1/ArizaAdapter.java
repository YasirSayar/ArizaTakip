package com.example.arizatakip_v1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorLong;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

public class ArizaAdapter extends RecyclerView.Adapter<ArizaAdapter.CardViewTasarimNesneleriniTutucu> {
    private Context mContext;
    public List<Arizalar> arizalarList;



    public ArizaAdapter(Context mContext, List<Arizalar> arizalarList) {
        this.mContext = mContext;
        this.arizalarList = arizalarList;

    }

    @NonNull
    @Override
    public CardViewTasarimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ariza_tasarim,parent,false);
        return new CardViewTasarimNesneleriniTutucu(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CardViewTasarimNesneleriniTutucu holder, int position) {
        Arizalar ariza = arizalarList.get(position);

        holder.textViewKullaniciAdiBosluk.setText(ariza.getKullanici_adi());
        holder.textViewIpAdiBosluk.setText(ariza.getIp_adi());
        holder.textViewPcAdiBosluk.setText(ariza.getPc_adi());
        holder.textViewDurumBosluk.setText(ariza.getDurum());//Durumun textiyle aynı yapıp renkleri düzeltmem lazım!!!
        holder.textViewTarihBosluk.setText(ariza.getTarih());
        holder.textViewSorunBosluk.setText(ariza.getSorun());

        if (ariza.getDurum() != null) {
            if (ariza.getDurum().equals("TAMAMLANDI")) {
                holder.imageViewDurumRengi.setBackgroundColor(ContextCompat.getColor(mContext, R.color.TAMAMLANDI));
            } else if (ariza.getDurum().equals("ISLEME ALINDI")) {
                holder.imageViewDurumRengi.setBackgroundColor(ContextCompat.getColor(mContext, R.color.ISLEMDE));
            }
            else {holder.imageViewDurumRengi.setBackgroundColor(ContextCompat.getColor(mContext, R.color.Gri));}
        } else {
            holder.imageViewDurumRengi.setBackgroundColor(ContextCompat.getColor(mContext, R.color.Gri));
        }

        holder.imageViewMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(mContext,holder.imageViewMoreButton);
                MenuInflater inflater= popup.getMenuInflater();//popup.getMenuInflater(),inflate(R.menu.card_menu,popup.getMenu());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    popup.setForceShowIcon(true);
                }
                inflater.inflate(R.menu.popup_menu,popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        sqlUpdate patch=new sqlUpdate();
                        if(R.id.menuIslemeAl==menuItem.getItemId())
                            {
                                patch.VeriGuncelle(ariza.getId(),"ISLEME ALINDI");
                            } else if (R.id.menuSıraya==menuItem.getItemId()) {
                            patch.VeriGuncelle(ariza.getId(),"");
                        } else if (R.id.menuTamamlandi==menuItem.getItemId()) {
                            patch.VeriGuncelle(ariza.getId(),"TAMAMLANDI");
                        }
                        Intent intent = new Intent(mContext, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(mContext,"Güncellendi",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return arizalarList.size();
    }



    public class CardViewTasarimNesneleriniTutucu extends RecyclerView.ViewHolder{
        public ImageView imageViewDurumRengi;
        public TextView textViewPcAdiBosluk;
        public TextView textViewIpAdiBosluk;
        public TextView textViewKullaniciAdiBosluk;
        public TextView textViewSorunBosluk;
        public TextView textViewTarihBosluk;
        public TextView textViewDurumBosluk;
        public ImageView imageViewMoreButton;

    public CardViewTasarimNesneleriniTutucu(@NonNull View itemView) {
        super(itemView);

        imageViewDurumRengi=itemView.findViewById(R.id.imageViewDurumRengi);
        textViewPcAdiBosluk=itemView.findViewById(R.id.textViewPcAdiBosluk);
        textViewIpAdiBosluk=itemView.findViewById(R.id.textViewIpAdiBosluk);
        textViewKullaniciAdiBosluk=itemView.findViewById(R.id.textViewKullaniciAdiBosluk);
        textViewSorunBosluk= itemView.findViewById(R.id.textViewSorunBosluk);
        textViewTarihBosluk=itemView.findViewById(R.id.textViewTarihBosluk);
        textViewDurumBosluk = itemView.findViewById(R.id.textViewDurumBosluk);
        imageViewMoreButton=itemView.findViewById(R.id.imageViewMoreButton);
    }
}
}
