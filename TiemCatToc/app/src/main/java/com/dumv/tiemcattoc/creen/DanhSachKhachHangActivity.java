package com.dumv.tiemcattoc.creen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dumv.tiemcattoc.Data;
import com.dumv.tiemcattoc.KEY;
import com.dumv.tiemcattoc.R;
import com.dumv.tiemcattoc.adapter.DanhSachKHAdapter;
import com.dumv.tiemcattoc.api.ApiGetDatFromTableRun;
import com.dumv.tiemcattoc.api.ApiRunSQL;
import com.dumv.tiemcattoc.api.GetDataFromTable;
import com.dumv.tiemcattoc.dialog.LuaChonKhachHang;
import com.dumv.tiemcattoc.dialog.LuaChonThemDialog;
import com.dumv.tiemcattoc.dialog.ThemThoCatDialog;
import com.dumv.tiemcattoc.object.KhachHang;
import com.dumv.tiemcattoc.tool.Convert;

import java.util.ArrayList;

public class DanhSachKhachHangActivity extends AppCompatActivity implements ApiGetDatFromTableRun, ApiRunSQL {
    Convert convert = new Convert();
    DanhSachKHAdapter danhSachKHAdapter;
    ListView lsvDSKhachHang;

    EditText edtTenKhachHang;
    String nameKhachHang = "";

    EditText edtSDT;
    String sdt = "";

    TextView txvGtAll, txvGtNam, txvGtNu;
    TextView arrTxvGt[];
    int chonGt = 0;// 0 all 1 nam 2 nu

    int thucHienCauLenh = 0;
    //0 thuc hien cau lenh them tho cat toc
    //1 thuc hien dau lanh xoa khach hang
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_khach_hang);
        init();
        anhXa();
        setUp();
        setClick();
        String s = checkDatainData();
        if (s.length() > 0) {
            new GetDataFromTable(s, this).execute();
        }
    }

    public void init() {

    }

    public void anhXa() {
        lsvDSKhachHang = findViewById(R.id.lsvDSKhachHang);
        edtTenKhachHang = findViewById(R.id.edtTenKhachHang);
        edtSDT = findViewById(R.id.edtSDT);

        txvGtAll = findViewById(R.id.txvGtAll);
        txvGtNam = findViewById(R.id.txvGtNam);
        txvGtNu = findViewById(R.id.txvGtNu);
    }

    public void setUp() {
        arrTxvGt = new TextView[]{txvGtAll, txvGtNam, txvGtNu};
        setUpGt();
    }

    public void setClick() {
        edtTenKhachHang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = edtTenKhachHang.getText().toString();
                if (s.length() >= 2) {
                    s = s.toLowerCase();
                    //tien hanh kiem tra va update lai cai list
                    nameKhachHang = s;
                } else {
                    nameKhachHang = "";
                }
                tienHanhKiemTra();
            }
        });
        edtSDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = edtSDT.getText().toString();
                if (s.length() == 3) {
                    sdt = s;
                } else {
                    sdt = "";
                }
                tienHanhKiemTra();
            }
        });
        txvGtAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonGt = 0;
                setUpGt();
                tienHanhKiemTra();
            }
        });
        txvGtNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonGt = 1;
                setUpGt();
                tienHanhKiemTra();
            }
        });
        txvGtNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonGt = 2;
                setUpGt();
                tienHanhKiemTra();
            }
        });
        lsvDSKhachHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                thucHienCauLenh = 1;
                new LuaChonKhachHang(DanhSachKhachHangActivity.this,danhSachKHAdapter.myArr.get(i)).show();
            }
        });
    }

    private void tienHanhKiemTra() {
        //kiem tra ten ma nguoi chu quan nhap vao
        ArrayList<KhachHang> arrTen;
        if (nameKhachHang.length() >= 2) {
            arrTen = new ArrayList<>();
            for (KhachHang k : Data.getData().arrKhachHang) {
                String tenKhach = k.ten.toLowerCase();
                if (tenKhach.indexOf(nameKhachHang) >= 0) {
                    arrTen.add(k);
                }
            }
        } else {
            arrTen = Data.getData().arrKhachHang;
        }
        //kiem tra sdt
        ArrayList<KhachHang> arrSDT;
        if (sdt.length() == 3) {
            arrSDT = new ArrayList<>();
            for (KhachHang k : arrTen) {
                String sdts = k.sdt;
                ////1234567 567
                if (sdts.indexOf(sdt) == sdts.length() - 3) {
                    arrSDT.add(k);
                }
            }
        } else {
            arrSDT = arrTen;
        }

        ///tien hanh kiem tra gt
        ArrayList<KhachHang> arrGt;
        if (chonGt != 0) {
            arrGt = new ArrayList<>();
            Boolean gt = false;
            if (chonGt == 1) {
                gt = true;
            }
            for (KhachHang k : arrSDT) {
                if (k.gt == gt) {
                    arrGt.add(k);
                }
            }
        } else {
            arrGt = arrSDT;
        }

        danhSachKHAdapter.updateKhachHang(arrGt);
    }

    public void setUpGt() {
        for (TextView txv : arrTxvGt) {
            txv.setTextColor(this.getResources().getColor(R.color.den));
            txv.setBackgroundColor(this.getResources().getColor(R.color.trang));
        }
        arrTxvGt[chonGt].setTextColor(this.getResources().getColor(R.color.vang));
        arrTxvGt[chonGt].setBackgroundColor(this.getResources().getColor(R.color.den));
    }

    public String checkDatainData() {
        if (Data.getData().arrKhachHang.size() == 0) {
            return KEY.TABLE_KHACH_HANG;
        }else if(Data.getData().arrThoCatTOc.size()==0){
            return KEY.TABLE_THO_CAT_TOC;
        }
        return "";
    }

    public void setUpShowData() {
        danhSachKHAdapter = new DanhSachKHAdapter(this, 0, Data.getData().arrKhachHang);
        lsvDSKhachHang.setAdapter(danhSachKHAdapter);
    }

    @Override
    public void batDauLay() {
        Toast.makeText(this, "bat dau lay", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data, String tableName) {
        if (tableName.equals(KEY.TABLE_KHACH_HANG)) {
            Data.getData().arrKhachHang.clear();
            Data.getData().arrKhachHang.addAll(convert.jsonToKhachHang(data));
        }else if (tableName.equals(KEY.TABLE_THO_CAT_TOC)) {
            Data.getData().arrThoCatTOc.clear();
            Data.getData().arrThoCatTOc.addAll(convert.jsonToThoCatToc(data));
        }
        String s = checkDatainData();
        if (s.length() > 0) {
            new GetDataFromTable(s, this).execute();
        } else {
            setUpShowData();
        }
    }

    public static int ID_MAN_THEM_KHACH_HANG = 99;
    public static int ID_MAN_SUA_KHACH_HANG = 100;
    public static int ID_MAN_CAT_TOC_KHACH_HANG = 101;

    @Override
    public void batDauChayDauSQL() {

    }

    @Override
    public void ketThuc() {
        if (thucHienCauLenh == 0) {
            Toast.makeText(this, "Thêm Thợ Cắt Tóc Thành Công", Toast.LENGTH_SHORT).show();
            Data.getData().arrThoCatTOc.clear();
            String s = checkDatainData();
            if (s.length() > 0) {
                new GetDataFromTable(s, this).execute();
            }
        }else if(thucHienCauLenh==1){
            Toast.makeText(this, "Xóa Khách Hàng Thành Công", Toast.LENGTH_SHORT).show();
            Data.getData().arrKhachHang.clear();
            String s = checkDatainData();
            if (s.length() > 0) {
                new GetDataFromTable(s, this).execute();
            }
        }
    }

    @Override
    public void biloi() {
        Toast.makeText(this, "Noi Ket noi", Toast.LENGTH_SHORT).show();
    }

    ///lin quan đen nut add
    public void luaChonThem(View view) {
        new LuaChonThemDialog(this).show();
    }

    public void chuyenDenManThemKhachHang() {
        Data.getData().idKhacHangCanSua =-1;
        Intent i = new Intent(this, ThemKhachHangActivity.class);
        startActivityForResult(i, ID_MAN_THEM_KHACH_HANG);
    }

    public void themThoCat() {
        thucHienCauLenh = 0;
        new ThemThoCatDialog(this).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ID_MAN_THEM_KHACH_HANG) {
            String s = checkDatainData();
            if (s.length() > 0) {
                new GetDataFromTable(s, this).execute();
            }
        }else if(requestCode == ID_MAN_SUA_KHACH_HANG){
            String s = checkDatainData();
            if (s.length() > 0) {
                new GetDataFromTable(s, this).execute();
            }
        }
    }
}
