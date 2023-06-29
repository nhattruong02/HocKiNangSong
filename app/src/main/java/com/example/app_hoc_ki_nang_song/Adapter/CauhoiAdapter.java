package com.example.app_hoc_ki_nang_song.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_hoc_ki_nang_song.DTO.Cauhoi;
import com.example.app_hoc_ki_nang_song.DTO.Chude;
import com.example.app_hoc_ki_nang_song.R;

import java.util.ArrayList;
import java.util.List;

public class CauhoiAdapter extends BaseAdapter {
    private Context context;
    private int Layout;
    private List<Cauhoi> cauhoiList;

    public CauhoiAdapter(Context context, int layout, List<Cauhoi> cauhoiList) {
        this.context = context;
        Layout = layout;
        this.cauhoiList = cauhoiList;
    }
    public void filterList(ArrayList<Cauhoi> filterlist) {
        cauhoiList = filterlist;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return cauhoiList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView cauhoi,dapanA,dapanB,dapanC,dapanD,dapandung;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CauhoiAdapter.ViewHolder holder;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(Layout,null);
            holder = new CauhoiAdapter.ViewHolder();
            holder.cauhoi = view.findViewById(R.id.txtdongcauhoi);
            holder.dapanA = view.findViewById(R.id.txtdapanA);
            holder.dapanB = view.findViewById(R.id.txtdapanB);
            holder.dapanC = view.findViewById(R.id.txtdapanC);
            holder.dapanD = view.findViewById(R.id.txtdapanD);
            holder.dapandung = view.findViewById(R.id.txtdapandung);
            view.setTag(holder);
        }else{
            holder = (CauhoiAdapter.ViewHolder) view.getTag();
        }
        Cauhoi cauhoi = cauhoiList.get(i);
        holder.cauhoi.setText(cauhoi.getCauhoi());
        holder.dapanA.setText(cauhoi.getDapanA());
        holder.dapanB.setText(cauhoi.getDapanB());
        holder.dapanC.setText(cauhoi.getDapanC());
        holder.dapanD.setText(cauhoi.getDapanD());
        holder.dapandung.setText(cauhoi.getDapandung());
        return view;
    }
}
