package com.example.quanlynhanvien;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AdapterNhanVien extends BaseAdapter{
    AppCompatActivity context;
    ArrayList<NhanVien> list;

    public AdapterNhanVien(AppCompatActivity context, ArrayList<NhanVien> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listview_row, null);
        ImageView imgHinhDaiDien = row.findViewById(R.id.imgHinhDaiDien);
        TextView txtId = row.findViewById(R.id.txtId);
        TextView txtHoTen = row.findViewById(R.id.txtHoTen);
        TextView txtCmt = row.findViewById(R.id.txtCmt);
        TextView txtNgaySinh = row.findViewById(R.id.txtNgaySinh);
        TextView txtGioiTinh = row.findViewById(R.id.txtGioiTinh);
        TextView txtEmail = row.findViewById(R.id.txtEmail);
        TextView txtSdt = row.findViewById(R.id.txtSdt);
        TextView txtDiaChi = row.findViewById(R.id.txtDiaChi);
        TextView txtChucVu = row.findViewById(R.id.txtChucVu);
        TextView txtPhongBan = row.findViewById(R.id.txtPhongBan);
        TextView txtHopDong = row.findViewById(R.id.txtHopDong);
        TextView txtNgayBatDau = row.findViewById(R.id.txtNgayBatDau);
        TextView txtNgayKetThuc = row.findViewById(R.id.txtNgayKetThuc);
        TextView txtTaiKhoan = row.findViewById(R.id.txtTaiKhoan);
        TextView txtMatKhau = row.findViewById(R.id.txtMatKhau);
        TextView txtThongTinThem = row.findViewById(R.id.txtThongTinThem);
        Button btnXoa = row.findViewById(R.id.btnXoa);
        Button btnSua = row.findViewById(R.id.btnSua);

        final NhanVien nhanVien = list.get(position);

        txtId.setText(nhanVien.id + "");
        txtHoTen.setText(nhanVien.hoten);
        txtCmt.setText(nhanVien.cmt);
        txtNgaySinh.setText(nhanVien.ngaysinh);
        txtGioiTinh.setText(nhanVien.giotinh);
        txtEmail.setText(nhanVien.email);
        txtSdt.setText(nhanVien.sdt);
        txtDiaChi.setText(nhanVien.diachi);
        txtChucVu.setText(nhanVien.chucvu);
        txtPhongBan.setText(nhanVien.phongban);
        txtHopDong.setText(nhanVien.hopdong);
        txtNgayBatDau.setText(nhanVien.ngaybatdau);
        txtNgayKetThuc.setText(nhanVien.ngayketthuc);
        txtTaiKhoan.setText(nhanVien.taikhoan);
        txtMatKhau.setText(nhanVien.matkhau);
        txtThongTinThem.setText(nhanVien.thongtinthem);

        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(nhanVien.anh, 0 ,nhanVien.anh.length);
        imgHinhDaiDien.setImageBitmap(bmHinhDaiDien);
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("ID",nhanVien.id );
                context.startActivity(intent);
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setTitle("Xác Nhận Xóa");
                builder.setMessage("Bạn có chắc muốn xóa nhân viên này không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(nhanVien.id);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return  row;

    }

    private void delete(int idNhanVien) {
        SQLiteDatabase database = Database.initDatabase(context, "QLNV.sqlite");
        database.delete("NhanVien", "MaNV = ?", new String[]{idNhanVien+""} );
        list.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM NhanVien", null);
        while (cursor.moveToNext()){
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
        notifyDataSetChanged();

    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}

