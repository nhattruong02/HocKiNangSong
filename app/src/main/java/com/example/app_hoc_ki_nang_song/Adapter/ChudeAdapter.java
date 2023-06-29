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

import com.example.app_hoc_ki_nang_song.BitmapUtils;
import com.example.app_hoc_ki_nang_song.DTO.Chude;
import com.example.app_hoc_ki_nang_song.R;

import java.util.ArrayList;
import java.util.List;

public class ChudeAdapter extends BaseAdapter {
    private Context context;
    private int Layout;
    private List<Chude> chudeList;

    public ChudeAdapter(Context context, int layout, List<Chude> chudeList) {
        this.context = context;
        Layout = layout;
        this.chudeList = chudeList;
    }

    public void filterList(ArrayList<Chude> filterlist) {
        chudeList = filterlist;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return chudeList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView tenchude;
        ImageView hinhchude;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(Layout, null);
            holder = new ViewHolder();
            holder.tenchude = view.findViewById(R.id.tenchude);
            holder.hinhchude = view.findViewById(R.id.hinhchude);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Chude chude = chudeList.get(i);
        holder.tenchude.setText(chude.getTenchude());
        byte[] hinh = chude.getHinhchude();
        holder.hinhchude.setImageBitmap(BitmapUtils.getImage(hinh));
        return view;
    }
}
