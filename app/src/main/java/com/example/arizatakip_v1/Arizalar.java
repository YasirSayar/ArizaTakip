package com.example.arizatakip_v1;

public class Arizalar {
    private String pc_adi;
    private String ip_adi;
    private String kullanici_adi;
    private String sorun;
    private String tarih;
    private String durum;

    private int id;

    public Arizalar(String pc_adi, String ip_adi, String kullanici_adi, String sorun, String tarih, String durum, int id) {
        this.pc_adi = pc_adi;
        this.ip_adi = ip_adi;
        this.kullanici_adi = kullanici_adi;
        this.sorun = sorun;
        this.tarih = tarih;
        this.durum = durum;
        this.id = id;
    }

    public String getPc_adi() {
        return pc_adi;
    }

    public void setPc_adi(String pc_adi) {
        this.pc_adi = pc_adi;
    }

    public String getIp_adi() {
        return ip_adi;
    }

    public void setIp_adi(String ip_adi) {
        this.ip_adi = ip_adi;
    }

    public String getKullanici_adi() {
        return kullanici_adi;
    }

    public void setKullanici_adi(String kullanici_adi) {
        this.kullanici_adi = kullanici_adi;
    }

    public String getSorun() {
        return sorun;
    }

    public void setSorun(String sorun) {
        this.sorun = sorun;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public Arizalar() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
