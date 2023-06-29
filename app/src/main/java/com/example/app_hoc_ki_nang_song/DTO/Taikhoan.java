package com.example.app_hoc_ki_nang_song.DTO;

import java.util.Date;

public class Taikhoan {
    public int mataikhoan;
    public String taikhoan;
    public String matkhau;
    public String hoten;
    public String gioitinh;
    public String namsinh;
    public String sdt;
    public String loaitaikhoan;


    public Taikhoan(int mataikhoan, String taikhoan, String matkhau, String hoten, String gioitinh, String namsinh, String sdt, String loaitaikhoan) {
        this.mataikhoan = mataikhoan;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.namsinh = namsinh;
        this.sdt = sdt;
        this.loaitaikhoan = loaitaikhoan;
    }

    public int getMataikhoan() {
        return mataikhoan;
    }

    public void setMataikhoan(int mataikhoan) {
        this.mataikhoan = mataikhoan;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public String getsdt() {
        return sdt;
    }

    public void sdt(String sdt) {
        this.sdt = sdt;
    }

    public String getLoaitaikhoan() {
        return loaitaikhoan;
    }

    public void setLoaitaikhoan(String loaitaikhoan) {
        this.loaitaikhoan = loaitaikhoan;
    }
}
