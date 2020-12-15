package com.dumv.tiemcattoc;

import com.dumv.tiemcattoc.object.KhachHang;
import com.dumv.tiemcattoc.object.ThoCatToc;

import java.util.ArrayList;

public class Data {
    static Data data;
    static {
        data= new Data();
    }

    public static Data getData() {
        return data;
    }

    public ArrayList<KhachHang> arrKhachHang = new ArrayList<>();

    // -1 la chuyen sang manf dde them khach hang
    // != -1 la sau thogn tin khach hang
    public int idKhacHangCanSua =-1;

    public ArrayList<ThoCatToc> arrThoCatTOc= new ArrayList<>();

}
