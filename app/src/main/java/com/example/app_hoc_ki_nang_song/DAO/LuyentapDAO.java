package com.example.app_hoc_ki_nang_song.DAO;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_hoc_ki_nang_song.DTO.Cauhoi;
import com.example.app_hoc_ki_nang_song.Hoanthanhcauhoi;
import com.example.app_hoc_ki_nang_song.MainActivity;
import com.example.app_hoc_ki_nang_song.R;
import com.example.app_hoc_ki_nang_song.database.Database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class LuyentapDAO extends AppCompatActivity {
    TextView txtcauhoi,txtdiem;
    Button btndapanA,btndapanB,btndapanC,btndapanD;
    List<Cauhoi> arrcauhoi= new ArrayList<>();
    Database db;
    int slcauhoi;
    Cauhoi cauhoihientai;
    int dem;
    TextView txtthoigian;
    Animation animation;
    ImageView imgdongho,imgquaylai;
    CountDownTimer timer;
    String ttmenu;
    int diemso;
    long time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cau_hoi);
        Anhxa();
        animation = AnimationUtils.loadAnimation(this,R.anim.animation_xoay);
        imgdongho.setAnimation(animation);
        animation.start();
        arrcauhoi = db.getListCauhoi();
        Collections.shuffle(arrcauhoi);
        Loadcauhoi();
        kttaikhoan();
        btndapanA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ktdapan(btndapanA.getText().toString().trim());
            }
        });
        btndapanB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ktdapan(btndapanB.getText().toString().trim());
            }
        });
        btndapanC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ktdapan(btndapanC.getText().toString().trim());
            }
        });
        btndapanD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ktdapan(btndapanD.getText().toString().trim());
            }
        });
        imgquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LuyentapDAO.this,MainActivity.class);
                timer.cancel();
                startActivity(intent);
            }
        });
        txtcauhoi.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LuyentapDAO.this,MainActivity.class);
        timer.cancel();
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    private void Loadcauhoi(){
        slcauhoi = arrcauhoi.size();
        if(dem < slcauhoi){
            cauhoihientai = arrcauhoi.get(dem);
            txtcauhoi.setText(""+cauhoihientai.getCauhoi());
            btndapanA.setText(""+cauhoihientai.getDapanA());
            btndapanB.setText(""+cauhoihientai.getDapanB());
            btndapanC.setText(""+cauhoihientai.getDapanC());
            btndapanD.setText(""+cauhoihientai.getDapanD());
            time = 30000;
            timer = new CountDownTimer(time,1000) {
                @Override
                public void onTick(long l) {
                    time = l;
                    txtthoigian.setText("00:"+(l/1000)%60);
                }
                @Override
                public void onFinish() {
                    time = 0;
                    Hoanthanh();
                    timer.cancel();
                }
            }.start();
        }
        else{
            if (slcauhoi > 0){
                Hoanthanh();
            }
        }
    }
    private void Hoanthanh(){
        timer.cancel();
        Intent intent = new Intent(LuyentapDAO.this, Hoanthanhcauhoi.class);
        intent.putExtra("diemso",diemso);
        startActivity(intent);
        finishAffinity();
    }
    private void ktdapan(String traloi){
        if(arrcauhoi.get(dem).getDapandung().equalsIgnoreCase(traloi)){
            diemso+=10;
            txtdiem.setText("" + diemso);
        }
        dem++;
        timer.cancel();
        Loadcauhoi();
    }
    private void Anhxa(){
        txtcauhoi= findViewById(R.id.txtcauhoi);
        txtdiem = findViewById(R.id.txtdiemso);
        txtthoigian = findViewById(R.id.txtthoigian);
        btndapanA = findViewById(R.id.btndapanA);
        btndapanB = findViewById(R.id.btndapanB);
        btndapanC = findViewById(R.id.btndapanC);
        btndapanD = findViewById(R.id.btndapanD);
        imgdongho = findViewById(R.id.imgdongho);
        imgquaylai = findViewById(R.id.quaylai);
        db = new Database(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_2, menu);
        if(ttmenu.equals("an_menu")){
            menu.findItem(R.id.setting).setVisible(false);
        }
        if(ttmenu.equals("hien_menu")){
            menu.findItem(R.id.setting).setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.setting) {
            Intent intent = new Intent(LuyentapDAO.this, QuanlycauhoiDAO.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void kttaikhoan() {
        if (DangNhap.taikhoan.equals("Admin") && DangNhap.matkhau.equals("1")) {
            ttmenu = "hien_menu";
            invalidateOptionsMenu();
            if(arrcauhoi.size()>0){
                timer.cancel();
            }
        }
        else{
            ttmenu = "an_menu";
            invalidateOptionsMenu();
        }
    }
}
