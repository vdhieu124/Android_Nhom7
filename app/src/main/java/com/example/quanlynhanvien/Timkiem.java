package com.example.quanlynhanvien;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Timkiem extends AppCompatActivity {
    final  String DATABASE_NAME = "QLNV.sqlite";
    SQLiteDatabase database;

    ListView listview2;
    EditText edtKey;
    ArrayList<NhanVien> list;
    AdapterNhanVien adapter;
    Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timkiem);

        addControls();

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });

    }

    private void addControls() {
        listview2 = (ListView) findViewById(R.id.listview2);
        btnOK = (Button) findViewById(R.id.btnOK);
        edtKey = (EditText) findViewById(R.id.edtKey);

        list = new ArrayList<>();
        adapter = new AdapterNhanVien(this, list);
        listview2.setAdapter(adapter);
    }

    private void readData() {

        String t=edtKey.getText().toString();
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM NhanVien Where MaNV=?" ,new String[]{t + ""});
        list.clear();
        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String hoten = cursor.getString(1);
            String cmt = cursor.getString(2);
            String ngaysinh = cursor.getString(3);
            String gioitinh = cursor.getString(4);
            String email = cursor.getString(5);
            String sdt = cursor.getString(6);
            String diachi = cursor.getString(7);
            byte[] anh = cursor.getBlob(8);
            String chucvu = cursor.getString(9);
            String phongban = cursor.getString(10);
            String hopdong = cursor.getString(11);
            String ngaybatdau = cursor.getString(12);
            String ngayketthuc = cursor.getString(13);
            String taikhoan = cursor.getString(14);


            String matkhau = cursor.getString(15);
            String thongtinthem = cursor.getString(16);
            list.add(new NhanVien(id ,hoten, cmt, ngaysinh, gioitinh, email, sdt, diachi, anh, chucvu, phongban, hopdong, ngaybatdau, ngayketthuc, taikhoan, matkhau, thongtinthem));


        }
        adapter.notifyDataSetChanged();
    }

    private void addEvents() {

    }
}
