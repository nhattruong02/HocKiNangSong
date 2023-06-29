package com.example.app_hoc_ki_nang_song.DAO;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.app_hoc_ki_nang_song.DTO.Baihoc;
import com.example.app_hoc_ki_nang_song.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class XemvideoDAO extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    String linkvideo;
    String API_KEY ="AIzaSyCEA95fkfbzMq2J24LLRNFGyR9JqCixlRI";
    YouTubePlayerView youTubePlayerView;
    int REQUEST_VIDEO = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_video);
        Intent intent = getIntent();
        linkvideo =intent.getStringExtra("Linkvideo");
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.viewyoutube);
        youTubePlayerView.initialize(API_KEY,this);

    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo(linkvideo);
    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(XemvideoDAO.this,REQUEST_VIDEO);
        }
        else{
            Toast.makeText(this, "Lỗi !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_VIDEO){
            youTubePlayerView.initialize(API_KEY,XemvideoDAO.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
