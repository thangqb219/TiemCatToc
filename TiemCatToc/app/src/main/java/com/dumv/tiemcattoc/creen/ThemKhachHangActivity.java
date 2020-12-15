package com.dumv.tiemcattoc.creen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dumv.tiemcattoc.Data;
import com.dumv.tiemcattoc.R;
import com.dumv.tiemcattoc.api.ApiRunSQL;
import com.dumv.tiemcattoc.api.RunSQL;
import com.dumv.tiemcattoc.object.KhachHang;

import java.util.Calendar;

public class ThemKhachHangActivity extends AppCompatActivity implements ApiRunSQL {
    EditText edtTenKhachHang, edtNgaySinhKhachHang, edtSdtKhachHang, edtDiaChiKhachHang, edtMoTaKhachHang;
    TextView txvNam, txvNu;
    boolean gt = true;
    KhachHang khachHang = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_khach_hang);
        init();
        anhXa();
        setUp();
        setClick();
    }

    private void init() {
        if (Data.getData().idKhacHangCanSua == -1) {
            //chuyen qua tu nut them
            ((TextView) findViewById(R.id.txvthemOrSua)).setText("THÊM");
            khachHang = null;
        } else {
            ((TextView) findViewById(R.id.txvthemOrSua)).setText("SỬA");
            for (KhachHang k : Data.getData().arrKhachHang) {
                if (k.id == Data.getData().idKhacHangCanSua) {
                    khachHang = k;
                    break;
                }
            }
        }
    }

    private void anhXa() {
        edtTenKhachHang = findViewById(R.id.edtTenKhachHang);
        edtNgaySinhKhachHang = findViewById(R.id.edtNgaySinhKhachHang);
        edtSdtKhachHang = findViewById(R.id.edtSdtKhachHang);
        edtDiaChiKhachHang = findViewById(R.id.edtDiaChiKhachHang);
        edtMoTaKhachHang = findViewById(R.id.edtMoTaKhachHang);
        txvNam = findViewById(R.id.txvNam);
        txvNu = findViewById(R.id.txvNu);
    }

    private void setUp() {
        if (khachHang != null) {
            edtTenKhachHang.setText(khachHang.ten);
            edtNgaySinhKhachHang.setText(khachHang.ngaySinh);
            edtSdtKhachHang.setText(khachHang.sdt);
            edtDiaChiKhachHang.setText(khachHang.diaChi);
            edtMoTaKhachHang.setText(khachHang.moTa);
            if (khachHang.gt) {
                gt = true;
                txvNam.setTextColor(ThemKhachHangActivity.this.getResources().getColor(R.color.vang));
                txvNam.setBackgroundColor(ThemKhachHangActivity.this.getResources().getColor(R.color.den));
                txvNu.setTextColor(ThemKhachHangActivity.this.getResources().getColor(R.color.den));
                txvNu.setBackgroundColor(ThemKhachHangActivity.this.getResources().getColor(R.color.trang));
            } else {
                gt = false;
                txvNu.setTextColor(ThemKhachHangActivity.this.getResources().getColor(R.color.vang));
                txvNu.setBackgroundColor(ThemKhachHangActivity.this.getResources().getColor(R.color.den));
                txvNam.setTextColor(ThemKhachHangActivity.this.getResources().getColor(R.color.den));
                txvNam.setBackgroundColor(ThemKhachHangActivity.this.getResources().getColor(R.color.trang));
            }
        }
    }

    private void setClick() {
        txvNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gt = true;
                txvNam.setTextColor(ThemKhachHangActivity.this.getResources().getColor(R.color.vang));
                txvNam.setBackgroundColor(ThemKhachHangActivity.this.getResources().getColor(R.color.den));
                txvNu.setTextColor(ThemKhachHangActivity.this.getResources().getColor(R.color.den));
                txvNu.setBackgroundColor(ThemKhachHangActivity.this.getResources().getColor(R.color.trang));
            }
        });
        txvNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gt = false;
                txvNu.setTextColor(ThemKhachHangActivity.this.getResources().getColor(R.color.vang));
                txvNu.setBackgroundColor(ThemKhachHangActivity.this.getResources().getColor(R.color.den));
                txvNam.setTextColor(ThemKhachHangActivity.this.getResources().getColor(R.color.den));
                txvNam.setBackgroundColor(ThemKhachHangActivity.this.getResources().getColor(R.color.trang));
            }
        });
    }

    private boolean chekNullInfor(EditText e) {
        String s = "" + e.getText();
        if (s.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    private void thongBao(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void themKhachHang(View view) {
        if (!chekNullInfor(edtTenKhachHang)) {
            thongBao("Bị Thiếu Tên");
            return;
        }
        String ten = edtTenKhachHang.getText().toString();
        if (!chekNullInfor(edtNgaySinhKhachHang)) {
            thongBao("Bị Thiếu Ngay Sinh");
            return;
        }
        String ngaySinh = edtNgaySinhKhachHang.getText().toString();
        if (!chekNullInfor(edtSdtKhachHang)) {
            thongBao("Bị Thiếu Số Điện Thoại");
            return;
        }
        String sdt = edtSdtKhachHang.getText().toString();
        if (!chekNullInfor(edtDiaChiKhachHang)) {
            thongBao("Bị Thiếu Địa Chỉ");
            return;
        }
        String diachi = edtDiaChiKhachHang.getText().toString();
        if (!chekNullInfor(edtMoTaKhachHang)) {
            thongBao("Bị Thiếu Mô Tả");
            return;
        }
        String mota = edtMoTaKhachHang.getText().toString();

        String gt = "1";
        if (!this.gt) {
            gt = "0";
        }

        String sql;
        if(khachHang==null) {
            sql = "INSERT INTO `khachhang` (`id`, `ten`, `ngaysinh`, `sdt`, `diachi`, `goitinh`, `mota`) VALUES (NULL, '" +
                    ten +
                    "', '" +
                    ngaySinh +
                    "', '" +
                    sdt +
                    "', '" +
                    diachi +
                    "', '" +
                    gt +
                    "', '" +
                    mota +
                    "')";
        }else{
            sql="UPDATE `khachhang` SET " +
                    "`ten` = '" +
                    ten +
                    "', `ngaysinh` = '" +
                    ngaySinh +
                    "', `sdt` = '" +
                    sdt +
                    "', `diachi` = '" +
                    diachi +
                    "', `goitinh` = '" +
                    gt +
                    "', `mota` = '" +
                    mota +
                    "' WHERE `khachhang`.`id` = "+khachHang.id;
        }
        new RunSQL(sql, this).execute();
    }

    public void chonNgaySinh(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                edtNgaySinhKhachHang.setText(date);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void batDauChayDauSQL() {
        thongBao("Bắt Đầu run sql");
    }

    @Override
    public void ketThuc() {
        thongBao("Đã Kết Thúc");
        Data.getData().arrKhachHang.clear();
        onBackPressed();
    }

    @Override
    public void biloi() {
        thongBao("run sql bị lỗi");
    }
}
