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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_hoc_ki_nang_song.Adapter.ChudeAdapter;
import com.example.app_hoc_ki_nang_song.BitmapUtils;
import com.example.app_hoc_ki_nang_song.DTO.Chude;
import com.example.app_hoc_ki_nang_song.R;
import com.example.app_hoc_ki_nang_song.database.Database;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ChudeDAO extends AppCompatActivity {
    ImageButton btnimageadd, btnimageaddbh, btnimageupdate;
    Button btnadd, btnaddbh, btnexit, btnexitbh, btnupdate;
    GridView gvchude;
    ChudeAdapter adapter;
    List<Chude> chudeList;
    Database db;
    String ttmenu;
    EditText timkiem;
    private int REQUEST_CODE_FOLDER = 123;
    int REQUEST_CODE_FOLDER_1 = 456;
    int REQUEST_CODE_FOLDER_2 = 789;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chu_de);
        Anhxa();
        kttaikhoan();
        loadChude();
        gvchude.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ChudeDAO.this, BaihocDAO.class);
                intent.putExtra("Machude", chudeList.get(i).getMachude());
                startActivity(intent);
            }
        });
        timkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<Chude> filteredList = new ArrayList<>();
        for (Chude cd : chudeList) {
            if (cd.getTenchude().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(cd);
            }
        }
        adapter.filterList(filteredList);
    }

    private void loadChude() {
        chudeList = new ArrayList<>();
        chudeList = db.getListChude();
        adapter = new ChudeAdapter(this, R.layout.dong_chu_de, chudeList);
        gvchude.setAdapter(adapter);
    }

    private void Anhxa() {
        gvchude = findViewById(R.id.gridview);
        timkiem = findViewById(R.id.txttimkiem);
        db = new Database(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        if (ttmenu.equals("an_menu")) {
            menu.findItem(R.id.add).setVisible(false);
        }
        if (ttmenu.equals("hien_menu")) {
            menu.findItem(R.id.add).setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            ThemchudeDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_3, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo i = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = i.position;
        switch (item.getItemId()) {
            case R.id.addbh:
                Chude chudebh = chudeList.get(index);
                int machudebh = chudebh.getMachude();
                ThembaihocDialog(machudebh);
                return true;
            case R.id.updatecd:
                Chude chude = chudeList.get(index);
                int machudesua = chude.getMachude();
                String tencd = chude.getTenchude();
                SuachudeDialog(machudesua, tencd);
                return true;
            case R.id.deletecd:
                Chude chude1 = chudeList.get(index);
                int machudexoa = chude1.getMachude();
                String tenchude = chude1.getTenchude();
                Xoachude(tenchude, machudexoa);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void ThemchudeDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_chu_de);
        btnimageadd = dialog.findViewById(R.id.btnthemhinhchude);
        EditText edttenchude = dialog.findViewById(R.id.edtthemchude);
        btnadd = dialog.findViewById(R.id.btnthemchude);
        btnexit = dialog.findViewById(R.id.btnthoatthem);
        btnimageadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenchude = edttenchude.getText().toString();
                try {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) btnimageadd.getDrawable();
                    if (tenchude.equals("")) {
                        Toast.makeText(ChudeDAO.this, "Hãy nhập tên chủ đề", Toast.LENGTH_SHORT).show();
                    } else if (bitmapDrawable != null) {
                        Bitmap bitmap = bitmapDrawable.getBitmap();
                        byte[] hinh = BitmapUtils.getBytes(bitmap);
                        db.themchude(tenchude, hinh);
                        dialog.dismiss();
                        loadChude();
                    }
                } catch (ClassCastException e) {
                    Toast.makeText(ChudeDAO.this, "Hãy thêm hình chủ đề", Toast.LENGTH_SHORT).show();
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

    private void SuachudeDialog(int machude, String tencd) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua_chu_de);
        btnimageupdate = dialog.findViewById(R.id.btnsuahinhchude);
        EditText edttenchude = dialog.findViewById(R.id.edtsuatenchude);
        edttenchude.setText(tencd);
        btnupdate = dialog.findViewById(R.id.btnsuachude);
        btnexit = dialog.findViewById(R.id.btnthoatsua);
        btnimageupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER_1);
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenchude = edttenchude.getText().toString();
                try {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) btnimageupdate.getDrawable();
                    if (tenchude.equals("")) {
                        Toast.makeText(ChudeDAO.this, "Hãy nhập tên chủ đề", Toast.LENGTH_SHORT).show();
                    } else if (bitmapDrawable != null) {
                        Bitmap bitmap = bitmapDrawable.getBitmap();
                        byte[] hinh = BitmapUtils.getBytes(bitmap);
                        db.suachude(machude, tenchude, hinh);
                        dialog.dismiss();
                        loadChude();
                    }
                } catch (ClassCastException e) {
                    Toast.makeText(ChudeDAO.this, "Hãy thêm hình chủ đề", Toast.LENGTH_SHORT).show();
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

    private void Xoachude(String tenchude, int machude) {
        AlertDialog.Builder dialogxoa = new AlertDialog.Builder(this);
        dialogxoa.setMessage("Bạn có muốn xoá chủ đề " + tenchude + " này không?");
        dialogxoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.xoachude(machude);
                loadChude();
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
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = (Bitmap) BitmapFactory.decodeStream(inputStream);
                btnimageadd.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == REQUEST_CODE_FOLDER_1 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = (Bitmap) BitmapFactory.decodeStream(inputStream);
                btnimageupdate.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == REQUEST_CODE_FOLDER_2 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = (Bitmap) BitmapFactory.decodeStream(inputStream);
                btnimageaddbh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void ThembaihocDialog(int machude) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_bai_hoc);
        btnimageaddbh = dialog.findViewById(R.id.btnthemhinhbaihoc);
        EditText edttenbaihoc = dialog.findViewById(R.id.edttenbaihoc);
        EditText edtmota = dialog.findViewById(R.id.edtmota);
        EditText edtnoidung = dialog.findViewById(R.id.edtnoidung);
        EditText edtlinkvideo = dialog.findViewById(R.id.edtvideo);
        btnaddbh = dialog.findViewById(R.id.btnthembaihoc);
        btnexitbh = dialog.findViewById(R.id.btnthoatthembaihoc);
        btnimageaddbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER_2);
            }
        });
        btnaddbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenbaihoc = edttenbaihoc.getText().toString();
                String mota = edtmota.getText().toString();
                String noidung = edtnoidung.getText().toString();
                String video = edtlinkvideo.getText().toString();
                try {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) btnimageaddbh.getDrawable();
                    if (tenbaihoc.equals("")) {
                        Toast.makeText(ChudeDAO.this, "Hãy nhập tên bài học", Toast.LENGTH_SHORT).show();
                    }
                    else if (mota.equals("")) {
                        Toast.makeText(ChudeDAO.this, "Hãy nhập mô tả bài học", Toast.LENGTH_SHORT).show();
                    }
                    else if (noidung.equals("")) {
                        Toast.makeText(ChudeDAO.this, "Hãy nhập nội dung bài học", Toast.LENGTH_SHORT).show();
                    }
                    else if (video.equals("")) {
                        Toast.makeText(ChudeDAO.this, "Hãy nhập link video bài học", Toast.LENGTH_SHORT).show();
                    } else if (bitmapDrawable != null) {
                        Bitmap bitmap = bitmapDrawable.getBitmap();
                        byte[] hinh = BitmapUtils.getBytes(bitmap);
                        db.thembaihoc(tenbaihoc, mota, noidung, hinh, video, machude);
                        Toast.makeText(ChudeDAO.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                } catch (ClassCastException e) {
                    Toast.makeText(ChudeDAO.this, "Hãy thêm hình chủ đề", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnexitbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void kttaikhoan() {
        if (DangNhap.taikhoan.equals("Admin") && DangNhap.matkhau.equals("1")) {
            ttmenu = "hien_menu";
            registerForContextMenu(gvchude);
            invalidateOptionsMenu();
        } else {
            ttmenu = "an_menu";
            invalidateOptionsMenu();
        }
    }
}