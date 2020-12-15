package com.dumv.tiemcattoc.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.dumv.tiemcattoc.R;
import com.dumv.tiemcattoc.creen.DanhSachKhachHangActivity;

public class LuaChonThemDialog extends Dialog {
    DanhSachKhachHangActivity ds;
    public LuaChonThemDialog(Context context) {
        super(context);
        this.ds=(DanhSachKhachHangActivity)context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dg_nua_chon_them);
        TextView txvThemKH = findViewById(R.id.txvThemKH);
        TextView txvThemThoCat = findViewById(R.id.txvThemThoCat);
        txvThemKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ds.chuyenDenManThemKhachHang();
                dismiss();
            }
        });
        txvThemThoCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ds.themThoCat();
                dismiss();
            }
        });
    }
}
