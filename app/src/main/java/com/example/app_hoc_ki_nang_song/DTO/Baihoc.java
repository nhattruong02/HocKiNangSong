package com.example.app_hoc_ki_nang_song.DTO;

public class Baihoc {
    private int mabaihoc;
    private String tenbaihoc;
    private String mota;
    private String noidung;
    private byte[] hinh;
    private String video;
    private int machude;
    public Baihoc(int mabaihoc, String tenbaihoc, String mota, String noidung, byte[] hinh, String video,int machude) {
        this.mabaihoc = mabaihoc;
        this.tenbaihoc = tenbaihoc;
        this.mota = mota;
        this.noidung = noidung;
        this.hinh = hinh;
        this.video = video;
        this.machude = machude;
    }

    public int getMabaihoc() {
        return mabaihoc;
    }

    public void setMabaihoc(int mabaihoc) {
        this.mabaihoc = mabaihoc;
    }

    public String getTenbaihoc() {
        return tenbaihoc;
    }

    public void setTenbaihoc(String tenbaihoc) {
        this.tenbaihoc = tenbaihoc;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getMachude() {
        return machude;
    }

    public void setMachude(int machude) {
        this.machude = machude;
    }
}
