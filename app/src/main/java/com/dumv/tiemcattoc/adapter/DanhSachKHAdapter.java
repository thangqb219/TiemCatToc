package com.dumv.tiemcattoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dumv.tiemcattoc.R;
import com.dumv.tiemcattoc.object.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class DanhSachKHAdapter extends ArrayAdapter<KhachHang> {
    Context myCt;
    public ArrayList<KhachHang> myArr;
    public DanhSachKHAdapter(Context context, int resource, List<KhachHang> objects) {
        super(context, resource, objects);
        this.myCt = context;
        this.myArr = new ArrayList<>(objects);
    }
    public void updateKhachHang(ArrayList<KhachHang> arr){
        this.myArr=arr;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.myArr.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater =(LayoutInflater)myCt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_khach_hang,null);
        }
        if(myArr.size()>0){
            TextView txvTenKhachHang = convertView.findViewById(R.id.txvTenKhachHang);
            TextView txvGtKhachHang = convertView.findViewById(R.id.txvGtKhachHang);
            TextView txvNgaySinhKhachHang = convertView.findViewById(R.id.txvNgaySinhKhachHang);
            TextView txvSdtKhachHang = convertView.findViewById(R.id.txvSdtKhachHang);
            TextView txvSoLanCatKhachHang = convertView.findViewById(R.id.txvSoLanCatKhachHang);

            KhachHang kh= myArr.get(position);
            txvSdtKhachHang.setText(kh.sdt);
            txvTenKhachHang.setText(kh.ten);
            if(kh.gt){
                txvGtKhachHang.setText("NAM");
            }else{
                txvGtKhachHang.setText("NU");
            }
            txvNgaySinhKhachHang.setText(kh.ngaySinh);
            txvSoLanCatKhachHang.setText(""+kh.solanCat+ " lần");
        }
        return convertView;
    }
}
