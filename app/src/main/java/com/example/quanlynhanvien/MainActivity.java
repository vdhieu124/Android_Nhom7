package com.example.quanlynhanvien;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final  String DATABASE_NAME = "QLNV.sqlite";
    SQLiteDatabase database;

    ListView listView;
    ArrayList<NhanVien> list;
    AdapterNhanVien adapter;
    Button btnThemNhanVien, btnTimKiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        readData();
        addEvents();

    }




    private void addControls() {
        btnThemNhanVien = (Button) findViewById(R.id.btnThemNhanVien);
        btnTimKiem = (Button) findViewById(R.id.btnOK);
        btnTimKiem = (Button) findViewById(R.id.btnOK);
        listView = (ListView) findViewById(R.id.listview);
        list = new ArrayList<>();
        adapter = new AdapterNhanVien(this, list);
        listView.setAdapter(adapter);
    }

    private void readData() {

        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM NhanVien", null);
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
        btnThemNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent themnv = new Intent(MainActivity.this, AddActivity.class);
                startActivity(themnv);
            }
        });

        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(MainActivity.this, Timkiem.class);

                startActivity(i);
            }
        });

    }



}
