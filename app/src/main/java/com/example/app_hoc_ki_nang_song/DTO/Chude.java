package com.example.app_hoc_ki_nang_song.DTO;

public class Chude {
    private int machude;
    private String tenchude;
    private byte[] hinhchude;

    public Chude(int machude, String tenchude, byte[] hinhchude) {
        this.machude = machude;
        this.tenchude = tenchude;
        this.hinhchude = hinhchude;
    }
    public int getMachude() {
        return machude;
    }

    public void setMachude(int machude) {
        this.machude = machude;
    }

    public String getTenchude() {
        return tenchude;
    }

    public void setTenchude(String tenchude) {
        this.tenchude = tenchude;
    }

    public byte[] getHinhchude() {
        return hinhchude;
    }

    public void setHinhchude(byte[] hinhchude) {
        this.hinhchude = hinhchude;
    }

}
