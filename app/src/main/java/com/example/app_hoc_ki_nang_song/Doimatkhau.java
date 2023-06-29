package com.example.app_hoc_ki_nang_song;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_hoc_ki_nang_song.DAO.DangNhap;
import com.example.app_hoc_ki_nang_song.database.Database;

import java.util.Date;

public class Doimatkhau extends AppCompatActivity {
    Button btndoimk,btnhuydoimk;
    EditText tk,mk,mkmoi,xacnhanmk;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doi_mat_khau);
        anhxa();
        btndoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doimk();

            }
        });
        btnhuydoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Doimatkhau.this, DangNhap.class);
                startActivity(intent);
            }
        });
    }

    private void doimk() {
        String taikhoan = tk.getText().toString();
        String matkhau = mk.getText().toString();
        String matkhaumoi = mkmoi.getText().toString();
        String xnmatkhau = xacnhanmk.getText().toString();
        if(database.ktdangnhap(taikhoan,matkhau) == false){
            Toast.makeText(this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
        }
        else if (!matkhaumoi.equals(xnmatkhau)){
            Toast.makeText(this, "Nhập lại mật khẩu xác nhận", Toast.LENGTH_SHORT).show();
        }
        else{
            database.doimatkhau(taikhoan,matkhaumoi);
            Toast.makeText(this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Doimatkhau.this,DangNhap.class);
            startActivity(intent);
        }
    }

    private void anhxa() {
        database = new Database(this);
        tk = findViewById(R.id.edttk);
        mk = findViewById(R.id.edtmk);
        mkmoi = findViewById(R.id.edtmkmoi);
        xacnhanmk = findViewById(R.id.edtxacnhanmk);
        btndoimk = findViewById(R.id.btndoimk);
        btnhuydoimk = findViewById(R.id.btnhuydoimk);
    }

}