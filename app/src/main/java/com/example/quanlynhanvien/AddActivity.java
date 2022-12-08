package com.example.quanlynhanvien;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddActivity extends AppCompatActivity {
    final String DATABASE_NAME = "QLNV.sqlite";
    final int RESQUEST_TAKE_PHOTO = 123;
    final int RESQUEST_CHOOSE_PHOTO = 321;


    Button btnChupHinh,btnLuu,btnHuy,btnChonHinh;
    EditText edtHoTen, edtCmt, edtNgaySinh, edtGioiTinh, edtEmail, edtSdt, edtDiaChi, edtChucVu, edtPhongBan, edtHopDong,
            edtNgayBatDau, edtNgayKetThuc, edtTaiKhoan, edtMatKhau, edtThongTinThem;
    ImageView imgHinhDaiDien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        addControls();
        addEvents();
    }



    private void addControls() {
        btnChonHinh = (Button) findViewById(R.id.btnChonHinh);
        btnChupHinh = (Button) findViewById(R.id.btnChupHinh);
        btnLuu = (Button) findViewById(R.id.btnLuu);
        btnHuy = (Button) findViewById(R.id.btnHuy);
        edtHoTen = (EditText) findViewById(R.id.edtHoTen);
        edtCmt = (EditText) findViewById(R.id.edtCmt);
        edtNgaySinh = (EditText) findViewById(R.id.edtNgaySinh);
        edtGioiTinh = (EditText) findViewById(R.id.edtGioiTinh);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSdt = (EditText) findViewById(R.id.edtSdt);
        edtDiaChi = (EditText) findViewById(R.id.edtDiaChi);
        edtChucVu = (EditText) findViewById(R.id.edtChucVu);
        edtPhongBan = (EditText) findViewById(R.id.edtPhongBan);
        edtHopDong = (EditText) findViewById(R.id.edtHopDong);
        edtNgayBatDau = (EditText) findViewById(R.id.edtNgayBatDau);
        edtNgayKetThuc = (EditText) findViewById(R.id.edtNgayKetThuc);
        edtTaiKhoan = (EditText) findViewById(R.id.edtTaiKhoan);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        edtThongTinThem = (EditText) findViewById(R.id.edtThongTinThem);
        imgHinhDaiDien = (ImageView) findViewById(R.id.imgHinhDaiDien);
    }


    private void addEvents(){
        btnChonHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto ();
            }
        });

        btnChupHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });


        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });


    }

    private void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, RESQUEST_TAKE_PHOTO );
    }

    private void choosePhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, RESQUEST_CHOOSE_PHOTO);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode==RESQUEST_CHOOSE_PHOTO){

                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgHinhDaiDien.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            } else if (requestCode==RESQUEST_TAKE_PHOTO){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgHinhDaiDien.setImageBitmap(bitmap);
            }

        }
    }

    private void insert(){
        String hoten = edtHoTen.getText().toString();
        String cmt = edtCmt.getText().toString();
        String ngaysinh = edtNgaySinh.getText().toString();
        String gioitinh = edtGioiTinh.getText().toString();
        String email = edtEmail.getText().toString();
        String sdt = edtSdt.getText().toString();
        String diachi = edtDiaChi.getText().toString();
        String chucvu = edtChucVu.getText().toString();
        String phongban = edtPhongBan.getText().toString();
        String hopdong = edtHopDong.getText().toString();
        String ngaybatdau = edtNgayBatDau.getText().toString();
        String ngayketthuc = edtNgayKetThuc.getText().toString();
        String taikhoan = edtTaiKhoan.getText().toString();
        String matkhau = edtMatKhau.getText().toString();
        String thongtinthem = edtThongTinThem.getText().toString();
        byte[] anh = getByteArrayFromImageView(imgHinhDaiDien);


        ContentValues contentValues = new ContentValues();
        contentValues.put("HoTen", hoten);
        contentValues.put("CMT", cmt);
        contentValues.put("NgaySinh", ngaysinh);
        contentValues.put("GioiTinh", gioitinh);
        contentValues.put("Email", email);
        contentValues.put("SDT", sdt);
        contentValues.put("DiaChi", diachi);
        contentValues.put("Anh", anh);
        contentValues.put("ChucVu", chucvu);
        contentValues.put("PhongBan", phongban);
        contentValues.put("HopDong", hopdong);
        contentValues.put("NgayBatDau", ngaybatdau);
        contentValues.put("NgayKetThuc", ngayketthuc);
        contentValues.put("TaiKhoan", taikhoan);
        contentValues.put("MatKhau", matkhau);
        contentValues.put("ThongTinThem", thongtinthem);


        SQLiteDatabase database = Database.initDatabase(this, "QLNV.sqlite");
        database.insert("NhanVien", null, contentValues);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void cancel(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private byte[] getByteArrayFromImageView(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
