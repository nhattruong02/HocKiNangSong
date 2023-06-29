package com.example.app_hoc_ki_nang_song;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_hoc_ki_nang_song.DAO.LuyentapDAO;

public class Hoanthanhcauhoi extends AppCompatActivity {
    Button btnquaylai,btnlamlai;
    ImageView imgchucmung;
    TextView txtdiem;
    int diemso;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoan_thanh_cau_hoi);
        Anhxa();
        mediaPlayer = MediaPlayer.create(this,R.raw.clap);
        mediaPlayer.start();
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation_lammo);
        imgchucmung.setImageResource(R.drawable.chucmung);
        imgchucmung.setAnimation(animation);
        txtdiem.setText(""+diemso);
        btnlamlai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                Intent intent = new Intent(Hoanthanhcauhoi.this, LuyentapDAO.class);
                startActivity(intent);
            }
        });
        btnquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                Intent intent = new Intent(Hoanthanhcauhoi.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void Anhxa(){
        btnlamlai = findViewById(R.id.btnlamlai);
        btnquaylai = findViewById(R.id.btnquaylai);
        imgchucmung = findViewById(R.id.imgchucmung);
        txtdiem = findViewById(R.id.txtdiemhoanthanh);
        Intent intent = getIntent();
        diemso = intent.getIntExtra("diemso",0);
    }
}
