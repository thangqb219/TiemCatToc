package com.dumv.tiemcattoc.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.dumv.tiemcattoc.Data;
import com.dumv.tiemcattoc.R;
import com.dumv.tiemcattoc.api.RunSQL;
import com.dumv.tiemcattoc.creen.CatTocChoKhachActivity;
import com.dumv.tiemcattoc.creen.DanhSachKhachHangActivity;
import com.dumv.tiemcattoc.creen.ThemKhachHangActivity;
import com.dumv.tiemcattoc.object.KhachHang;

public class LuaChonKhachHang extends Dialog {

    KhachHang ks;
    DanhSachKhachHangActivity ds;

    public LuaChonKhachHang(Context context, KhachHang khachHang) {
        super(context);

        this.ks = khachHang;
        this.ds=(DanhSachKhachHangActivity)context;

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dg_nua_chon_khach_hang);

        //anh xa
        TextView txvTenKhachHang = findViewById(R.id.txvTenKhachHang);
        TextView txvSuaThongTinKhachHang = findViewById(R.id.txvSuaThongTinKhachHang);
        TextView txvXoaKhacHang = findViewById(R.id.txvXoaKhacHang);
        TextView txvCatTocChoKhach = findViewById(R.id.txvCatTocChoKhach);
      //set thong tin
        txvTenKhachHang.setText(ks.ten);
        txvSuaThongTinKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data.getData().idKhacHangCanSua = ks.id;
                ds.startActivityForResult(new Intent(ds, ThemKhachHangActivity.class),ds.ID_MAN_SUA_KHACH_HANG);
                dismiss();
            }
        });
        txvXoaKhacHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql="DELETE FROM `khachhang` WHERE `khachhang`.`id` = "+ks.id;
                new RunSQL(sql,ds).execute();
                dismiss();
            }
        });
        txvCatTocChoKhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data.getData().idKhacHangCanSua = ks.id;
                ds.startActivityForResult(new Intent(ds, CatTocChoKhachActivity.class),ds.ID_MAN_CAT_TOC_KHACH_HANG);
                dismiss();
            }
        });
    }
}
