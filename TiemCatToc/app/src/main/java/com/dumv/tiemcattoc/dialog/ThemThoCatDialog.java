package com.dumv.tiemcattoc.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dumv.tiemcattoc.R;
import com.dumv.tiemcattoc.api.RunSQL;
import com.dumv.tiemcattoc.creen.DanhSachKhachHangActivity;

public class ThemThoCatDialog extends Dialog {
    DanhSachKhachHangActivity ds;
    EditText edtTenThoCatToc;
    public ThemThoCatDialog(Context context) {
        super(context);
        this.ds=(DanhSachKhachHangActivity)context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dg_them_tho_cat);

        TextView txvThemThoCatToc = findViewById(R.id.txvThemThoCatToc);
        edtTenThoCatToc = findViewById(R.id.edtTenThoCatToc);

        txvThemThoCatToc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ""+edtTenThoCatToc.getText().toString();
                if(name.length()==0){
                    Toast.makeText(ds,"Thiếu Tên Thợ Cắt Tóc",Toast.LENGTH_SHORT).show();
                    return;
                }
                String sql="INSERT INTO `tho` (`id`, `ten`) VALUES (NULL, '" +
                        name +
                        "')";
                new RunSQL(sql,ds).execute();
                dismiss();
            }
        });
    }
}
