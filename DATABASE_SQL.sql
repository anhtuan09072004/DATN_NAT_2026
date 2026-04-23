CREATE DATABASE DATN_2026_RiVo;
GO

USE DATN_2026_RiVo;
GO

CREATE TABLE kieu_dang (
    id BIGINT IDENTITY PRIMARY KEY,
    ten NVARCHAR(255)
);

CREATE TABLE chat_lieu (
    id BIGINT IDENTITY PRIMARY KEY,
    ten NVARCHAR(255)
);

CREATE TABLE nha_cung_cap (
    id BIGINT IDENTITY PRIMARY KEY,
    ten NVARCHAR(255)
);

CREATE TABLE san_pham (
    id BIGINT IDENTITY PRIMARY KEY,
    ten NVARCHAR(255),
    mo_ta NVARCHAR(MAX),
    kieu_dang_id BIGINT,
    chat_lieu_id BIGINT,
    nha_cung_cap_id BIGINT,
    trang_thai INT,
    created_at DATETIME,
    updated_at DATETIME,

    FOREIGN KEY (kieu_dang_id) REFERENCES kieu_dang(id),
    FOREIGN KEY (chat_lieu_id) REFERENCES chat_lieu(id),
    FOREIGN KEY (nha_cung_cap_id) REFERENCES nha_cung_cap(id)
);


CREATE TABLE mau_sac (
    id BIGINT IDENTITY PRIMARY KEY,
    ten NVARCHAR(100)
);

CREATE TABLE size (
    id BIGINT IDENTITY PRIMARY KEY,
    ten NVARCHAR(50)
);

CREATE TABLE san_pham_chi_tiet (
    id BIGINT IDENTITY PRIMARY KEY,
    san_pham_id BIGINT,
    mau_sac_id BIGINT,
    size_id BIGINT,
    gia DECIMAL(18,2),
    so_luong INT,
    trang_thai INT,

    FOREIGN KEY (san_pham_id) REFERENCES san_pham(id),
    FOREIGN KEY (mau_sac_id) REFERENCES mau_sac(id),
    FOREIGN KEY (size_id) REFERENCES size(id)
);


CREATE TABLE hinh_anh (
    id BIGINT IDENTITY PRIMARY KEY,
    url NVARCHAR(500),
    san_pham_id BIGINT,

    FOREIGN KEY (san_pham_id) REFERENCES san_pham(id)
);


CREATE TABLE khach_hang (
    id BIGINT IDENTITY PRIMARY KEY,
    ten NVARCHAR(255),
    sdt NVARCHAR(20),
    email NVARCHAR(255)
);

CREATE TABLE tai_khoan (
    id BIGINT IDENTITY PRIMARY KEY,
    username NVARCHAR(100),
    password NVARCHAR(255),
    role NVARCHAR(50),
    khach_hang_id BIGINT,

    FOREIGN KEY (khach_hang_id) REFERENCES khach_hang(id)
);


CREATE TABLE dia_chi (
    id BIGINT IDENTITY PRIMARY KEY,
    khach_hang_id BIGINT,
    dia_chi NVARCHAR(500),
    sdt NVARCHAR(20),

    FOREIGN KEY (khach_hang_id) REFERENCES khach_hang(id)
);


CREATE TABLE gio_hang (
    id BIGINT IDENTITY PRIMARY KEY,
    khach_hang_id BIGINT,

    FOREIGN KEY (khach_hang_id) REFERENCES khach_hang(id)
);

CREATE TABLE gio_hang_chi_tiet (
    id BIGINT IDENTITY PRIMARY KEY,
    gio_hang_id BIGINT,
    san_pham_chi_tiet_id BIGINT,
    so_luong INT,

    FOREIGN KEY (gio_hang_id) REFERENCES gio_hang(id),
    FOREIGN KEY (san_pham_chi_tiet_id) REFERENCES san_pham_chi_tiet(id)
);

CREATE TABLE chuc_vu (
    id BIGINT IDENTITY PRIMARY KEY,
    ten NVARCHAR(100)
);

CREATE TABLE nhan_vien (
    id BIGINT IDENTITY PRIMARY KEY,
    ten NVARCHAR(255),
    chuc_vu_id BIGINT,

    FOREIGN KEY (chuc_vu_id) REFERENCES chuc_vu(id)
);


CREATE TABLE voucher (
    id BIGINT IDENTITY PRIMARY KEY,
    ten NVARCHAR(255),
    giam_gia DECIMAL(10,2),
    max_giam DECIMAL(18,2),
    ngay_bat_dau DATETIME,
    ngay_ket_thuc DATETIME
);

CREATE TABLE hoa_don (
    id BIGINT IDENTITY PRIMARY KEY,
    khach_hang_id BIGINT,
    nhan_vien_id BIGINT,
    voucher_id BIGINT,
    tong_tien DECIMAL(18,2),
    trang_thai INT,
    created_at DATETIME,

    FOREIGN KEY (khach_hang_id) REFERENCES khach_hang(id),
    FOREIGN KEY (nhan_vien_id) REFERENCES nhan_vien(id),
    FOREIGN KEY (voucher_id) REFERENCES voucher(id)
);


CREATE TABLE hoa_don_chi_tiet (
    id BIGINT IDENTITY PRIMARY KEY,
    hoa_don_id BIGINT,
    san_pham_chi_tiet_id BIGINT,
    so_luong INT,
    gia DECIMAL(18,2),

    FOREIGN KEY (hoa_don_id) REFERENCES hoa_don(id),
    FOREIGN KEY (san_pham_chi_tiet_id) REFERENCES san_pham_chi_tiet(id)
);

CREATE TABLE thanh_toan (
    id BIGINT IDENTITY PRIMARY KEY,
    hoa_don_id BIGINT,
    phuong_thuc NVARCHAR(100),
    trang_thai INT,

    FOREIGN KEY (hoa_don_id) REFERENCES hoa_don(id)
);