package com.example.app_hoc_ki_nang_song.DAO;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_hoc_ki_nang_song.DTO.Baihoc;
import com.example.app_hoc_ki_nang_song.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class ChitietbaihocDAO extends AppCompatActivity {
    TextView txttenctbh,txtnoidung;
    Button btnxemvideo;
    List<Baihoc> baihocList;
    String tenbaihoc,noidung,linkvideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chi_tiet_bai_hoc);
        Anhxa();
        txtnoidung.setMovementMethod(new ScrollingMovementMethod());
        Loadnoidung();
        btnxemvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChitietbaihocDAO.this,XemvideoDAO.class);
                intent.putExtra("Linkvideo", linkvideo);
                startActivity(intent);
            }
        });
    }
    private void Anhxa(){
        txttenctbh = findViewById(R.id.tenctbh);
        txtnoidung = findViewById(R.id.noidung);
        btnxemvideo = findViewById(R.id.btnvideo);
        baihocList = new ArrayList<>();
        Intent intent = getIntent();
        tenbaihoc = intent.getStringExtra("Tenbaihoc");
        noidung = intent.getStringExtra("Noidung");
        linkvideo =intent.getStringExtra("Linkvideo");
    }
    private void Loadnoidung(){
        txttenctbh.setText(tenbaihoc);
        txtnoidung.setText(noidung);
    }
}
