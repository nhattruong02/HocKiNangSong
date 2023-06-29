package com.example.app_hoc_ki_nang_song.DAO;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_hoc_ki_nang_song.Adapter.BaihocAdapter;
import com.example.app_hoc_ki_nang_song.Adapter.ChudeAdapter;
import com.example.app_hoc_ki_nang_song.BitmapUtils;
import com.example.app_hoc_ki_nang_song.DTO.Baihoc;
import com.example.app_hoc_ki_nang_song.DTO.Chude;
import com.example.app_hoc_ki_nang_song.R;
import com.example.app_hoc_ki_nang_song.database.Database;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BaihocDAO extends AppCompatActivity {
    ImageButton btnimageupdate;
    Button btnexit, btnupdate;
    ListView lvbaihoc;
    BaihocAdapter adapter;
    List<Baihoc> baihocList;
    Database db;
    private int REQUEST_CODE_FOLDER = 123;
    int machude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai_hoc);
        Anhxa();
        kttaikhoan();
        loadBaihoc();
        lvbaihoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BaihocDAO.this, ChitietbaihocDAO.class);
                intent.putExtra("Tenbaihoc", baihocList.get(i).getTenbaihoc());
                intent.putExtra("Noidung", baihocList.get(i).getNoidung());
                intent.putExtra("Linkvideo", baihocList.get(i).getVideo());
                startActivity(intent);
            }
        });
    }

    private void loadBaihoc() {
        Intent intent = getIntent();
        machude = intent.getIntExtra("Machude", 0);
        baihocList = new ArrayList<>();
        baihocList = db.getListBaihoc(machude);
        adapter = new BaihocAdapter(this, R.layout.dong_bai_hoc, baihocList);
        lvbaihoc.setAdapter(adapter);
    }

    private void Anhxa() {
        lvbaihoc = findViewById(R.id.lvbaihoc);
        db = new Database(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_1, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo i = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = i.position;
        switch (item.getItemId()) {
            case R.id.update:
                Baihoc baihoc = baihocList.get(index);
                String tenbh = baihoc.getTenbaihoc();
                String mota = baihoc.getMota();
                String noidung = baihoc.getNoidung();
                String linkvideo = baihoc.getVideo();
                int mabaihoc = baihoc.getMabaihoc();
                SuabaihocDialog(mabaihoc, tenbh, mota, noidung, linkvideo);
                return true;
            case R.id.delete:
                Baihoc baihoc1 = baihocList.get(index);
                int mabaihoc1 = baihoc1.getMabaihoc();
                String tenbaihoc1 = baihoc1.getTenbaihoc();
                Xoabaihoc(tenbaihoc1, mabaihoc1);
                return true;
        }
        return super.onContextItemSelected(item);
    }


    private void SuabaihocDialog(int mabaihoc, String tenbh, String mota, String nd, String link) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua_bai_hoc);
        btnimageupdate = dialog.findViewById(R.id.btnsuahinhbaihoc);
        EditText edttenbaihoc = dialog.findViewById(R.id.edtsuatenbaihoc);
        EditText edtmota = dialog.findViewById(R.id.edtsuamota);
        EditText edtnoidung = dialog.findViewById(R.id.edtsuanoidung);
        EditText edtlinkvideo = dialog.findViewById(R.id.edtsuavideo);
        edttenbaihoc.setText(tenbh);
        edtmota.setText(mota);
        edtnoidung.setText(nd);
        edtlinkvideo.setText(link);
        btnupdate = dialog.findViewById(R.id.btnsuabaihoc);
        btnexit = dialog.findViewById(R.id.btnthoatsuabaihoc);
        btnimageupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenbaihoc = edttenbaihoc.getText().toString();
                String mota = edtmota.getText().toString();
                String noidung = edtnoidung.getText().toString();
                String linkvideo = edtlinkvideo.getText().toString();
                try {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) btnimageupdate.getDrawable();
                    if (tenbaihoc.equals("")) {
                        Toast.makeText(BaihocDAO.this, "Hãy nhập tên bài học", Toast.LENGTH_SHORT).show();
                    }
                    if (mota.equals("")) {
                        Toast.makeText(BaihocDAO.this, "Hãy nhập mô tả bài học", Toast.LENGTH_SHORT).show();
                    }
                    if (noidung.equals("")) {
                        Toast.makeText(BaihocDAO.this, "Hãy nhập nội dung bài học", Toast.LENGTH_SHORT).show();
                    }
                    if (linkvideo.equals("")) {
                        Toast.makeText(BaihocDAO.this, "Hãy nhập link video bài học", Toast.LENGTH_SHORT).show();
                    } else if (bitmapDrawable != null) {
                        Bitmap bitmap = bitmapDrawable.getBitmap();
                        byte[] hinh = BitmapUtils.getBytes(bitmap);
                        db.suabaihoc(mabaihoc, tenbaihoc, mota, noidung, hinh, linkvideo);
                        dialog.dismiss();
                        loadBaihoc();
                    }
                } catch (ClassCastException e) {
                    Toast.makeText(BaihocDAO.this, "Hãy thêm hình bài học", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void Xoabaihoc(String tenbaihoc, int mabaihoc) {
        AlertDialog.Builder dialogxoa = new AlertDialog.Builder(this);
        dialogxoa.setMessage("Bạn có muốn xoá bài học " + tenbaihoc + " này không?");
        dialogxoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.xoabaihoc(mabaihoc);
                loadBaihoc();
            }
        });
        dialogxoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogxoa.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = (Bitmap) BitmapFactory.decodeStream(inputStream);
                btnimageupdate.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void kttaikhoan() {
        if (DangNhap.taikhoan.equals("Admin") && DangNhap.matkhau.equals("1")) {
            registerForContextMenu(lvbaihoc);
        }
    }
}
