package com.example.app_hoc_ki_nang_song.DAO;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_hoc_ki_nang_song.Adapter.CauhoiAdapter;
import com.example.app_hoc_ki_nang_song.Adapter.ChudeAdapter;
import com.example.app_hoc_ki_nang_song.BitmapUtils;
import com.example.app_hoc_ki_nang_song.DTO.Cauhoi;
import com.example.app_hoc_ki_nang_song.DTO.Chude;
import com.example.app_hoc_ki_nang_song.R;
import com.example.app_hoc_ki_nang_song.database.Database;

import java.util.ArrayList;
import java.util.List;

public class QuanlycauhoiDAO extends AppCompatActivity {
    Button btnadd, btnexit, btnupdate;
    ListView lvcauhoi;
    CauhoiAdapter adapter;
    List<Cauhoi> cauhoiList;
    EditText timkiem;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quan_ly_cau_hoi);
        Anhxa();
        loadCauhoi();
        registerForContextMenu(lvcauhoi);
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
        ArrayList<Cauhoi> filteredList = new ArrayList<>();
        for (Cauhoi ch : cauhoiList) {
            if (ch.getCauhoi().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(ch);
            }
        }
        adapter.filterList(filteredList);
    }
    private void Anhxa(){
        timkiem = findViewById(R.id.txttimkiemch);
        lvcauhoi = findViewById(R.id.lvqlcauhoi);
        db = new Database(this);
    }
    private void loadCauhoi() {
        cauhoiList = new ArrayList<>();
        cauhoiList = db.getListCauhoi();
        adapter = new CauhoiAdapter(this, R.layout.dong_cau_hoi, cauhoiList);
        lvcauhoi.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            ThemcauhoiDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_4, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo i = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = i.position;
        switch (item.getItemId()) {
            case R.id.updatech:
                Cauhoi cauhoi = cauhoiList.get(index);
                int macauhoi = cauhoi.getMacauhoi();
                String ch = cauhoi.getCauhoi();
                String dapana = cauhoi.getDapanA();
                String dapanb=cauhoi.getDapanB();
                String dapanc=cauhoi.getDapanC();
                String dapand=cauhoi.getDapanD();
                String dapandung=cauhoi.getDapandung();
                SuacauhoiDialog(macauhoi,ch,dapana,dapanb,dapanc,dapand,dapandung);
                return true;
            case R.id.deletech:
                Cauhoi cauhoi1 = cauhoiList.get(index);
                int macauhoi1 = cauhoi1.getMacauhoi();
                String ndcauhoi1 = cauhoi1.getCauhoi();
                Xoacauhoi(ndcauhoi1, macauhoi1);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void ThemcauhoiDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_cau_hoi);
        EditText edtthemcauhoi = dialog.findViewById(R.id.edtthemcauhoi);
        EditText edtdapanA = dialog.findViewById(R.id.edtthemdapanA);
        EditText edtdapanB = dialog.findViewById(R.id.edtthemdapanB);
        EditText edtdapanC = dialog.findViewById(R.id.edtthemdapanC);
        EditText edtdapanD = dialog.findViewById(R.id.edtthemdapanD);
        EditText edtdapandung = dialog.findViewById(R.id.edtthemdapandung);
        btnadd = dialog.findViewById(R.id.btnthemcauhoi);
        btnexit = dialog.findViewById(R.id.btnthoatthemcauhoi);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cauhoi = edtthemcauhoi.getText().toString();
                String dapanA = edtdapanA.getText().toString();
                String dapanB = edtdapanB.getText().toString();
                String dapanC = edtdapanC.getText().toString();
                String dapanD = edtdapanD.getText().toString();
                String dapandung = edtdapandung.getText().toString();
                db.themcauhoi(cauhoi,dapanA,dapanB,dapanC,dapanD,dapandung);
                dialog.dismiss();
                loadCauhoi();
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

    private void SuacauhoiDialog(int macauhoi,String ch,String a,String b,String c,String d,String dapandung) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua_cau_hoi);
        EditText edtsuacauhoi = dialog.findViewById(R.id.edtsuacauhoi);
        EditText edtdapanA = dialog.findViewById(R.id.edtsuadapanA);
        EditText edtdapanB = dialog.findViewById(R.id.edtsuadapanB);
        EditText edtdapanC = dialog.findViewById(R.id.edtsuadapanC);
        EditText edtdapanD = dialog.findViewById(R.id.edtsuadapanD);
        EditText edtdapandung = dialog.findViewById(R.id.edtsuadapandung);
        edtsuacauhoi.setText(ch);
        edtdapanA.setText(a);
        edtdapanB.setText(b);
        edtdapanC.setText(c);
        edtdapanD.setText(d);
        edtdapandung.setText(dapandung);
        btnupdate = dialog.findViewById(R.id.btnsuacauhoi);
        btnexit = dialog.findViewById(R.id.btnthoatsuacauhoi);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cauhoi = edtsuacauhoi.getText().toString();
                String dapanA = edtdapanA.getText().toString();
                String dapanB = edtdapanB.getText().toString();
                String dapanC = edtdapanC.getText().toString();
                String dapanD = edtdapanD.getText().toString();
                String dapandung = edtdapandung.getText().toString();
                db.suacauhoi(macauhoi,cauhoi,dapanA,dapanB,dapanC,dapanD,dapandung);
                dialog.dismiss();
                loadCauhoi();
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

    private void Xoacauhoi(String tencauhoi, int macauhoi) {
        AlertDialog.Builder dialogxoa = new AlertDialog.Builder(this);
        dialogxoa.setMessage("Bạn có muốn xoá câu hỏi " + tencauhoi + " này không?");
        dialogxoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.xoacauhoi(macauhoi);
                loadCauhoi();
            }
        });
        dialogxoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogxoa.show();
    }
}
