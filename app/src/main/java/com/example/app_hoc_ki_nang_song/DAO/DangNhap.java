package com.example.app_hoc_ki_nang_song.DAO;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_hoc_ki_nang_song.Doimatkhau;
import com.example.app_hoc_ki_nang_song.MainActivity;
import com.example.app_hoc_ki_nang_song.R;
import com.example.app_hoc_ki_nang_song.database.Database;

public class DangNhap extends AppCompatActivity {
    EditText edttaikhoan, edtmatkhau;
    TextView txtdangky,txtdoimk;
    Button btndangnhap;
    Database database;
    public static String taikhoan,matkhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_nhap);
        Anhxa();
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dangnhap();
            }
        });
        txtdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btndangky();
            }
        });
        txtdoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btndoimk();
            }
        });
    }

    private void btndoimk() {
        Intent intent = new Intent(DangNhap.this, Doimatkhau.class);
        startActivity(intent);
    }

    private void btndangky() {
        Intent intent = new Intent(DangNhap.this, Dangky.class);
        startActivity(intent);
    }

    private void Anhxa() {
        txtdoimk = findViewById(R.id.txtdoimk);
        edttaikhoan = (EditText) findViewById(R.id.edttaikhoan);
        edtmatkhau = (EditText) findViewById(R.id.edtmatkhau);
        btndangnhap = findViewById(R.id.btndangnhap);
        txtdangky = findViewById(R.id.txtdangky);
        database = new Database(this);
    }

    private void Dangnhap() {
        taikhoan = edttaikhoan.getText().toString();
        matkhau = edtmatkhau.getText().toString();
        if (database.ktdangnhap(taikhoan, matkhau)) {
            Intent intent = new Intent(DangNhap.this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(DangNhap.this, "Tài khoản không tồn tại!", Toast.LENGTH_SHORT).show();
        }
    }
}
