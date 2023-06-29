package com.example.app_hoc_ki_nang_song.DAO;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_hoc_ki_nang_song.R;
import com.example.app_hoc_ki_nang_song.database.Database;

public class Dangky extends AppCompatActivity {
    EditText edttaikhoan, edtmatkhau, edthoten, edtgioitinh, edtnamsinh, edtsdt;
    Button btndangky, btnthoat;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_ky);
        Anhxa();
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dangkytk();
            }
        });
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnthoat();
            }
        });
    }

    private void btnthoat() {
        Intent intent = new Intent(Dangky.this, DangNhap.class);
        startActivity(intent);
    }

    private void Anhxa() {
        edttaikhoan = (EditText) findViewById(R.id.edttaikhoandk);
        edtmatkhau = (EditText) findViewById(R.id.edtmatkhaudk);
        edthoten = (EditText) findViewById(R.id.edthotendk);
        edtgioitinh = (EditText) findViewById(R.id.edtgioitinhdk);
        edtnamsinh = (EditText) findViewById(R.id.edtnamsinhdk);
        edtsdt = (EditText) findViewById(R.id.edtsdtdk);
        btndangky = findViewById(R.id.btndangkytk);
        btnthoat = findViewById(R.id.btnthoatdk);
        database = new Database(this);
    }

    private void Dangkytk() {
        String taikhoan = edttaikhoan.getText().toString();
        String matkhau = edtmatkhau.getText().toString();
        String hoten = edthoten.getText().toString();
        String gioitinh = edtgioitinh.getText().toString();
        String namsinh = edtnamsinh.getText().toString();
        String sdt = edtsdt.getText().toString();
        String loaitk = "Người dùng";
        if (database.ktDangky(taikhoan)) {
            Toast.makeText(Dangky.this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
        }else if(taikhoan.equals("") || matkhau.equals("")|| hoten.equals("")||gioitinh.equals("")||namsinh.equals("")||sdt.equals("")){
            Toast.makeText(this, "Hãy nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Dangky.this, "Đăng ký tài khoản thành công!", Toast.LENGTH_SHORT).show();
            database.Dangky(taikhoan, matkhau, hoten, gioitinh, namsinh, sdt, loaitk);
            Intent intent = new Intent(Dangky.this, DangNhap.class);
            startActivity(intent);
        }
    }
}

