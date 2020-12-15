package com.dumv.tiemcattoc.creen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.dumv.tiemcattoc.Data;
import com.dumv.tiemcattoc.R;
import com.dumv.tiemcattoc.object.KhachHang;
import com.dumv.tiemcattoc.object.ThoCatToc;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class CatTocChoKhachActivity extends AppCompatActivity {

    KhachHang khachHang;
    TextView txvTenKhachHang;
    Spinner spnThoCatToc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_toc_cho_khach);
        init();
        anhXa();
        setUp();
        setClick();
    }

    private void init() {
        for (KhachHang k : Data.getData().arrKhachHang) {
            if (k.id == Data.getData().idKhacHangCanSua) {
                khachHang = k;
                break;
            }
        }


    }

    private void anhXa() {
        txvTenKhachHang = findViewById(R.id.txvTenKhachHang);
        spnThoCatToc = findViewById(R.id.spnThoCatToc);

    }

    private void setUp() {
        txvTenKhachHang.setText(khachHang.ten);
        spnThoCatToc.setAdapter(new ArrayAdapter<ThoCatToc>(this,R.layout.item_text_tho_cat,Data.getData().arrThoCatTOc));

    }

    private void setClick() {
    }


}
