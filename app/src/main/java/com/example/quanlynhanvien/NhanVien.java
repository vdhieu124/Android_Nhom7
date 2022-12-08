package com.example.quanlynhanvien;

public class NhanVien {

    public  int id;
    public String hoten;
    public String cmt;
    public String ngaysinh;
    public String giotinh;
    public String email;
    public String sdt;
    public String diachi;
    public  byte[] anh;
    public String chucvu;
    public String phongban;
    public String hopdong;
    public String ngaybatdau;
    public String ngayketthuc;
    public String taikhoan;
    public String matkhau;
    public String thongtinthem;

    public NhanVien(int id, String hoten, String cmt, String ngaysinh, String giotinh, String email, String sdt, String diachi, byte[] anh, String chucvu, String phongban, String hopdong, String ngaybatdau, String ngayketthuc, String taikhoan, String matkhau, String thongtinthem) {
        this.id = id;
        this.hoten = hoten;
        this.cmt = cmt;
        this.ngaysinh = ngaysinh;
        this.giotinh = giotinh;
        this.email = email;
        this.sdt = sdt;
        this.diachi = diachi;
        this.anh = anh;
        this.chucvu = chucvu;
        this.phongban = phongban;
        this.hopdong = hopdong;
        this.ngaybatdau = ngaybatdau;
        this.ngayketthuc = ngayketthuc;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.thongtinthem = thongtinthem;
    }
}

