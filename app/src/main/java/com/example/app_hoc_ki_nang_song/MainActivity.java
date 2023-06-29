package com.example.app_hoc_ki_nang_song;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app_hoc_ki_nang_song.DAO.ChudeDAO;
import com.example.app_hoc_ki_nang_song.DAO.DangNhap;
import com.example.app_hoc_ki_nang_song.DAO.Dangky;
import com.example.app_hoc_ki_nang_song.DAO.LuyentapDAO;

public class MainActivity extends AppCompatActivity {
    Button btnxemchude,btnluyentap,btnthoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        btnxemchude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChudeDAO.class);
                startActivity(intent);
            }
        });
        btnluyentap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LuyentapDAO.class);
                startActivity(intent);
            }
        });
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DangNhap.class);
                startActivity(intent);
            }
        });
    }
    private void Anhxa(){
        btnxemchude = findViewById(R.id.btnxemchude);
        btnluyentap = findViewById(R.id.btnluyentap);
        btnthoat = findViewById(R.id.btnthoat);
    }
}