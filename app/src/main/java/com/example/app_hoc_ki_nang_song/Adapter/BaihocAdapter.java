package com.example.app_hoc_ki_nang_song.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_hoc_ki_nang_song.BitmapUtils;
import com.example.app_hoc_ki_nang_song.DTO.Baihoc;
import com.example.app_hoc_ki_nang_song.DTO.Cauhoi;
import com.example.app_hoc_ki_nang_song.R;

import java.util.List;

public class BaihocAdapter extends BaseAdapter {
    private Context context;
    private int Layout;
    private List<Baihoc> baihocList;

    public BaihocAdapter(Context context, int layout, List<Baihoc> baihocList) {
        this.context = context;
        Layout = layout;
        this.baihocList = baihocList;
    }

    @Override
    public int getCount() {
        return baihocList.size();
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
        TextView txttenbaihoc,txtmota;
        ImageView hinhbaihoc;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BaihocAdapter.ViewHolder holder;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(Layout,null);
            holder = new BaihocAdapter.ViewHolder();
            holder.txttenbaihoc = view.findViewById(R.id.txttenbaihoc);
            holder.txtmota = view.findViewById(R.id.txtmota);
            holder.hinhbaihoc = view.findViewById(R.id.hinhbaihoc);
            view.setTag(holder);
        }else{
            holder = (BaihocAdapter.ViewHolder) view.getTag();
        }
        Baihoc baihoc = baihocList.get(i);
        holder.txttenbaihoc.setText(baihoc.getTenbaihoc());
        holder.txtmota.setText(baihoc.getMota());
        byte[] hinh = baihoc.getHinh();
        holder.hinhbaihoc.setImageBitmap(BitmapUtils.getImage(hinh));
        return view;
    }
}
