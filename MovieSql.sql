create database Cinema;
go
use Cinema
go
set dateformat dmy
create table QuanLi(
	maQL varchar(20) primary key,
	hoTen nvarchar(50) not null,
	sdt char(10) not null ,
	gioiTinh bit,
	ngaySinh date,
	diaChi nvarchar(50),
	cccd bigint,
	tinhTrang bit default 1,
	tenTK varchar(30) not null ,
	matKhau varchar(30) not null,
	CONSTRAINT U_ql_user unique(tenTK,matKhau),
	CONSTRAINT U_ql_sdt unique(sdt),
	CONSTRAINT U_ql_cccd unique(cccd),
	CONSTRAINT CK_ql_sdt CHECK(sdt LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
)
create table NhanVien(
	maNV varchar(20) primary key,
	hoTen nvarchar(50) not null,
	sdt char(10) not null ,
	gioiTinh bit,
	ngaySinh date,
	diaChi nvarchar(50),
	cccd bigint,
	tinhTrang bit default 1,
	tenTK varchar(30) not null ,
	matKhau varchar(30) not null,
	maQL varchar(20) not null,
	foreign key (maQL) references QuanLi(maQL) on update cascade on delete cascade,--thêm mã quản lí vào table nhân viên 
	CONSTRAINT U_nv_user unique(tenTK,matKhau),
	CONSTRAINT U_nv_sdt unique(sdt),
	CONSTRAINT U_nv_cccd unique(cccd),
	CONSTRAINT CK_nv_sdt CHECK(sdt LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
)

create table KhachHang(
	maKH varchar(20) primary key,
	hoTen nvarchar(50) null,
	sdt char(10) null,
	ngaySinh date null,
	email varchar(30) null,
	diaChi nvarchar(50) null,
	tinhTrang bit default 1 null,
	tenTK varchar(30) null,
	matKhau varchar(30) null,
	CONSTRAINT U_kh_KH unique(sdt,tenTK,matKhau),
	CONSTRAINT U_kh_user unique(tenTK,matKhau),
	CONSTRAINT U_kh_sdt unique(sdt),
	CONSTRAINT CK_kh_sdt CHECK(sdt LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
)
create table TheLoaiPhim(
	maLPhim varchar(20) primary key,
	tenLPhim nvarchar(50) not null,
	moTaLP nvarchar(100)
)
create table Phim(
	maPhim varchar(20) primary key,
	maLPhim varchar(20),
	tenPhim nvarchar(50) not null,
	daoDien nvarchar(50)not null,
	doTuoiYeuCau int not null,
	ngayKhoiChieu date not null,
	thoiLuong int not null,
	tinhTrang bit default 1,
	hinhDaiDien varchar(50),
	video varchar(50),
	moTa nvarchar(100),
	foreign key (maLPhim) references TheLoaiPhim(maLPhim) ON UPDATE CASCADE ON DELETE CASCADE
)
create table PhongChieu(
	maPhong varchar(20) primary key,
	tongSoGhe int not null,
	loaiMayChieu char(1),
	loaiAmThanh char(1),
	dienTich decimal,
	tinhTrang bit default 1,
	CONSTRAINT CK_mayChieu check (loaiMayChieu in ('A','B','C')),
	CONSTRAINT CK_amThanh check (loaiAmThanh in ('A','B','C'))
)
create table CaChieu(
	maCa varchar(20) primary key,
	tenCa varchar(20), 
	thoiGianBatDau Time,
	thoiGianKetThuc Time,
	CONSTRAINT U_caChieu unique(tenCa),
	CONSTRAINt CK_thoiGianCa check(thoiGianBatDau < thoiGianKetThuc)
)
create table SuatChieu(
	maSuat varchar(20) primary key,
	maPhim varchar(20) not null,
	maPhong varchar(20) not null,
	maCa varchar(20),
	ngayChieu date not null,
	tinhTrang bit not null, 
	foreign key (maCa) references CaChieu(maCa) ON UPDATE CASCADE ON DELETE CASCADE,
	foreign key (maPhim) references Phim(maPhim) ON UPDATE CASCADE ON DELETE CASCADE,
	foreign key (maPhong) references PhongChieu(maPhong) ON UPDATE CASCADE ON DELETE CASCADE
)
create table Ghe(
	maGhe varchar(20) primary key,
	tinhTrang bit not null
)


--bảng vé thêm số lượng tối đa
create table Ve(
	maVe varchar(20) primary key ,
	maPhim varchar(20) not null,
	maNV varchar(20) not null,
	tinhTrang bit default 1,
	soLuongToiDa int ,
	soLuongDaBan int ,
	tien float null
)
--nối bảng nhân viên với ghế vô vô bookve
create table BookVe(
	maBook varchar(20) primary key,
	maKH varchar(20)  null,
	maNV varchar(20)  not null,
	maSuat varchar(20) not null,
	maVe varchar(20)  null,
	tongTien float  null, 
	ngayMua date null,
	foreign key (maKH) references KhachHang(maKH) ON UPDATE CASCADE ON DELETE CASCADE,
	foreign key (maNV) references Nhanvien(maNV) ON UPDATE CASCADE ON DELETE CASCADE,
	foreign key (maSuat) references SuatChieu(maSuat) ON UPDATE CASCADE ON DELETE CASCADE,
	foreign key (maVe) references Ve(maVe) ON UPDATE CASCADE ON DELETE CASCADE
)



CREATE TABLE BookGhe (
    maGhe varchar(20),
    maBook varchar(20),
    PRIMARY KEY (maGhe, maBook),
    FOREIGN KEY (maGhe) REFERENCES Ghe(maGhe) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (maBook) REFERENCES BookVe(maBook) ON UPDATE CASCADE ON DELETE CASCADE
);

GO
CREATE TRIGGER TG_CreIdNhanVien
ON NhanVien
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Prefix VARCHAR(20) = 'NV'; 

    INSERT INTO NhanVien(maNV,hoTen,sdt,gioiTinh,ngaySinh,diaChi,cccd,tinhTrang,tenTK,matKhau,maQL)
    SELECT 
        @Prefix + CONVERT(VARCHAR(20), COALESCE((SELECT MAX(CAST(SUBSTRING(maNV, 3, LEN(maNV)) AS INT)) FROM NhanVien), 0) + ROW_NUMBER() OVER (ORDER BY (SELECT NULL))),
        hoTen,sdt,gioiTinh,ngaySinh,diaChi,cccd,tinhTrang,tenTK,matKhau,maQL
    FROM inserted;
END;


go
CREATE TRIGGER TG_CreIdQuanLi
ON QuanLi
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Prefix VARCHAR(20) = 'QL'; 

    INSERT INTO QuanLi(maQL,hoTen,sdt,gioiTinh,ngaySinh,diaChi,cccd,tinhTrang,tenTK,matKhau )
    SELECT 
        @Prefix + CONVERT(VARCHAR(20), COALESCE((SELECT MAX(CAST(SUBSTRING(maQL, 3, LEN(maQL)) AS INT)) FROM QuanLi), 0) + ROW_NUMBER() OVER (ORDER BY (SELECT NULL))),
        hoTen,sdt,gioiTinh,ngaySinh,diaChi,cccd,tinhTrang,tenTK,matKhau
    FROM inserted;
END;

go
CREATE TRIGGER TG_CreIdKhachHang
ON KhachHang
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Prefix VARCHAR(20) = 'KH'; 
	CREATE TABLE #InsertedValues (
        maKH VARCHAR(50) -- Thay đổi kiểu dữ liệu nếu cần thiết
    );

    INSERT INTO KhachHang(maKH,hoTen,sdt,ngaySinh,email,diaChi,tinhTrang,tenTK,matKhau)
	OUTPUT inserted.maKH INTO #InsertedValues
    SELECT 
        @Prefix + CONVERT(VARCHAR(20), COALESCE((SELECT MAX(CAST(SUBSTRING(maKH, 3, LEN(maKH)) AS INT)) FROM KhachHang), 0) + ROW_NUMBER() OVER (ORDER BY (SELECT NULL))),
        hoTen,sdt,ngaySinh,email,diaChi,tinhTrang,tenTK,matKhau
    FROM inserted;

	SELECT maKH FROM #InsertedValues;

    DROP TABLE #InsertedValues;
END;
go
CREATE TRIGGER TG_CreIdTLPhim
ON TheLoaiPhim
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Prefix VARCHAR(20) = 'LP'; 

    INSERT INTO TheLoaiPhim(maLPhim,tenLPhim,moTaLP)
    SELECT 
        @Prefix + CONVERT(VARCHAR(20), COALESCE((SELECT MAX(CAST(SUBSTRING(maLPhim, 2, LEN(maLPhim)) AS INT)) FROM TheLoaiPhim), 0) + ROW_NUMBER() OVER (ORDER BY (SELECT NULL))),
         tenLPhim,moTaLP
    FROM inserted;
END;

go
CREATE TRIGGER TG_CreIdPhim
ON Phim
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Prefix VARCHAR(20) = 'P'; 

    INSERT INTO Phim(maPhim,tenPhim,daoDien,maLPhim,doTuoiYeuCau,ngayKhoiChieu,thoiLuong,tinhTrang,hinhDaiDien,video,moTa)
    SELECT 
        @Prefix + CONVERT(VARCHAR(20), COALESCE((SELECT MAX(CAST(SUBSTRING(maPhim, 2, LEN(maPhim)) AS INT)) FROM Phim), 0) + ROW_NUMBER() OVER (ORDER BY (SELECT NULL))),
         tenPhim,daoDien,maLPhim,doTuoiYeuCau,ngayKhoiChieu,thoiLuong,tinhTrang,hinhDaiDien,video,moTa
    FROM inserted;
END;

go
CREATE TRIGGER TG_CreIdPhongChieu
ON PhongChieu
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Prefix VARCHAR(20) = 'PC'; 

    INSERT INTO PhongChieu(maPhong, tongSoGhe, loaiMayChieu, loaiAmThanh, dienTich,tinhTrang)
    SELECT 
        @Prefix + CONVERT(VARCHAR(20), COALESCE((SELECT MAX(CAST(SUBSTRING(maPhong, 3, LEN(maPhong)) AS INT)) FROM PhongChieu), 0) + ROW_NUMBER() OVER (ORDER BY (SELECT NULL))),
        tongSoGhe, loaiMayChieu, loaiAmThanh, dienTich,tinhTrang
    FROM inserted;
END;

go
CREATE TRIGGER TG_CreIdCaChieu
ON CaChieu
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Prefix VARCHAR(20) = 'CC'; 

    INSERT INTO CaChieu(maCa,tenCa,thoiGianBatDau,thoiGianKetThuc)
    SELECT 
        @Prefix + CONVERT(VARCHAR(20), COALESCE((SELECT MAX(CAST(SUBSTRING(maCa, 3, LEN(maCa)) AS INT)) FROM CaChieu), 0) + ROW_NUMBER() OVER (ORDER BY (SELECT NULL))),
         tenCa,thoiGianBatDau,thoiGianKetThuc
    FROM inserted;
END;

go
CREATE TRIGGER TG_CreIdSuatChieu
ON SuatChieu
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Prefix VARCHAR(20) = 'SC'; 

    INSERT INTO SuatChieu(maSuat,maPhim,maPhong,maCa,ngayChieu,tinhTrang)
    SELECT 
        @Prefix + CONVERT(VARCHAR(20), COALESCE((SELECT MAX(CAST(SUBSTRING(maSuat, 3, LEN(maSuat)) AS INT)) FROM SuatChieu), 0) + ROW_NUMBER() OVER (ORDER BY (SELECT NULL))),
         maPhim,maPhong,maCa,ngayChieu,tinhTrang
    FROM inserted;
END;

--thay doi 
go
CREATE TRIGGER TG_CreIdBookVe
ON BookVe
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Prefix VARCHAR(20) = 'BV'; 

	CREATE TABLE #InsertedValues (
        maBook VARCHAR(50) -- Thay đổi kiểu dữ liệu nếu cần thiết
    );

    INSERT INTO BookVe(maBook, maKH,maNV, maSuat,maVe, tongTien, ngayMua)
	OUTPUT inserted.maBook INTO #InsertedValues
    SELECT 
        @Prefix + CONVERT(VARCHAR(20), COALESCE((SELECT MAX(CAST(SUBSTRING(maBook, 3, LEN(maBook)) AS INT)) FROM BookVe), 0) + ROW_NUMBER() OVER (ORDER BY (SELECT NULL))),
         maKH,maNV, maSuat ,maVe, tongTien, ngayMua
    FROM inserted;

	-- Lấy các giá trị từ bảng tạm thời
    SELECT maBook FROM #InsertedValues;

    -- Xóa bảng tạm thời sau khi sử dụng
    DROP TABLE #InsertedValues;
END;
go
CREATE TRIGGER TG_CreIdVe
ON Ve
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Prefix VARCHAR(20) = 'V'; 

    INSERT INTO Ve(maVe,maPhim,maNV,soLuongToiDa,soLuongDaBan,tinhTrang, tien)
    SELECT 
        @Prefix + CONVERT(VARCHAR(20), COALESCE((SELECT MAX(CAST(SUBSTRING(maVe, 2, LEN(maVe)) AS INT)) FROM Ve), 0) + ROW_NUMBER() OVER (ORDER BY (SELECT NULL))),
         maPhim,maNV,soLuongToiDa,soLuongDaBan,tinhTrang, tien
    FROM inserted;
END;
GO


--Không cho tentk matkhau null đc vì hai dòng null nó tính trùng nhau
insert into KhachHang (hoTen,sdt,ngaySinh,email,diaChi,tinhTrang,tenTK,matKhau)
values	(N'Lê A','0900000001','5/5/2004','A0900000001@gmail.com','Đà Nẵng',  1,'leduy','ad123'),
		(N'Lê B','0900000002','5/5/2004','B0900000002@gmail.com','Đà Nẵng',  1,'B0900000002','B09002'),
		(N'Lê C','0900000003','5/5/2004','C0900000003@gmail.com','Đà Nẵng',  0,'C0900000003','C09003'),
		(N'Lê D','0900000004','5/5/2004','D0900000004@gmail.com','Đà Nẵng',  1,'D0900000004','D09004'),
		(N'Lê E','0900000005','5/5/2004','E0900000005@gmail.com','Đà Nẵng',  1,'E0900000005','E09005'),
		(N'Lê F','0900000006','5/5/2004','F0900000006@gmail.com','Quảng Nam',1,'F0900000006','F09006'),
		(N'Lê G','0900000007','5/5/2004','G0900000007@gmail.com','Quảng Nam',1,'G0900000007','G09007'),
		(N'Lê H','0900000008','5/5/2004','H0900000008@gmail.com','Quảng Nam',1,'H0900000008','H09008'),
		(N'Lê I','0900000009','5/5/2004','I0900000009@gmail.com','Quảng Nam',0,'I0900000009','I09009'),
		(N'Lê K','0900000010','5/5/2004','K0900000010@gmail.com','Quảng Nam',1,'K0900000010','K09010'),
		(N'Lê L','0900000011','5/5/2004','L0900000011@gmail.com','Quảng Nam',1,'L0900000011','L09011')

insert into QuanLi(hoTen,sdt,gioiTinh,ngaySinh,diaChi,cccd,tinhTrang,tenTK,matKhau)
values	(N'Lê Ha Ha','0910000001',1,'5/5/2004','Đà Nẵng',04920492 , 1,'admin','ad123'),
		(N'Lê Hi Hi','0920000002',1,'5/5/2004','Đà Nẵng',04920493 , 1,'Hi0920000002','Hi092002')

insert into CaChieu(tenCa,thoiGianBatDau)
values	('Ca 1','7:00:00'),
		('Ca 2','9:00:00'),
		('Ca 3','10:00:00'),
		('Ca 4','14:00:00'),
		('Ca 5','16:00:00'),
		('Ca 6','19:00:00')

insert into NhanVien (hoTen,sdt,gioiTinh,ngaySinh,diaChi,cccd,tinhTrang,tenTK,matKhau,maQL)
values	(N'Trần A','0100000001',1,'1-1-2001',N'Đà Nẵng',04920481,1,'leduy','ad123','QL1'),
		(N'Trần D','0100000002',0,'1-3-2001',N'Đà Nẵng',04920482,1,'D0100000002','D012','QL1'),
		(N'Trần C','0100000003',1,'1-9-2001',N'Đà Nẵng',04920483,0,'C0100000003','C013','QL1'),
		(N'Trần B','0100000004',1,'1-5-2001',N'Đà Nẵng',04920484,1,'B0100000004','B014','QL2')
		
insert into TheLoaiPhim(tenLPhim)
values  (N'Tình cảm'),
		(N'Hành động'),
		(N'Kinh dị'),
		(N'Hài kịch'),
		(N'Chính kịch'),
		(N'Hoạt hình'),
		(N'Viễn tưởng')

insert into Phim (maLPhim,tenPhim,daoDien,doTuoiYeuCau,ngayKhoiChieu,thoiLuong,tinhTrang,hinhDaiDien,video,moTa)
values	('LP1',N'Titanic',N'Ngọc Huân',18 ,'1-1-2024',145 ,1,'a','b','c'),
		('LP2',N'Avenger',N'Phước Lý',06 ,'1-1-2024',130 ,1,'d','e','f'),
		('LP7',N'Avatar',N'Tam Quốc',18 ,'1-1-2024',180 ,1,'g','h','i'),
		('LP5',N'Hồ Thiên Nga',N'Phương Trinh',18 ,'1-1-2024',125 ,1,'k','l','m')

insert into PhongChieu (tongSoGhe, loaiMayChieu, loaiAmThanh, dienTich,tinhTrang)
values	(60,'a','a',70,1),
		(60,'b','b',70,1),
		(60,'c','a',70,1),
		(60,'a','c',70,1)

insert into SuatChieu (maPhim,maPhong,maCa,ngayChieu,tinhTrang)
values	('P1','PC1','CC1','6-2-2024',1),
		('P2','PC2','CC2','6-2-2024',1),
		('P3','PC3','CC3','6-2-2024',1)

insert into Ghe (maGhe,tinhTrang)
values  ('A1',1),
		('A2',1),
		('A3',1),
		('A4',1),
		('A5',1),
		('B1',1),
		('B2',1),
		('B3',1),
		('B4',1),
		('B5',1),
		('C1',1),
		('C2',1),
		('C3',1),
		('C4',1),
		('C5',1)


insert into Ve (maPhim, maNV, tinhTrang, soLuongToiDa, soLuongDaBan, tien)
values  ('P1','NV1', 1, 50, 23, 25000),
		('P3','NV4', 1, 50, 13, 25000),
		('P2','NV2', 1, 50, 23, 25000),
		('P1','NV1', 1, 50, 23, 25000),
		('P1','NV2', 1, 50, 33, 25000),
		('P4','NV1', 1, 50, 23, 25000),
		('P4','NV3', 1, 50, 13, 25000),
		('P1','NV1', 1, 50, 23, 25000),
		('P2','NV3', 1, 50, 23, 25000),
		('P3','NV1', 1, 50, 31, 25000)

insert into BookVe (maKH,maNV,maVe,maSuat, tongTien, ngayMua)
values	('KH8' ,'NV1','V1','SC1',50000,'6-2-2024'),
		('KH9' ,'NV1','V2','SC2',50000,'6-2-2024'),
		('KH10','NV1','V3','SC1',50000,'6-2-2024'),
		('KH2','NV1','V4','SC3',50000,'6-2-2024'),
		('KH1' ,'NV1','V5','SC3',350000,'6-2-2024')

insert into BookGhe (maBook,maGhe)
values	('BV1','A1'),
		('BV1','A2'),
		('BV1','B5'),
		('BV2','B1'),
		('BV2','C2'),
		('BV3','C4'),
		('BV4','A1')
						   
	--hàm tính tuổi của khách hàng từ ngày sinh của khách hàng
go
create function fTinhTuoi(
	@maKH varchar(20)
)
returns int
as 
begin 
	declare @tuoi int 
	select @tuoi= datediff(MONTH,KhachHang.ngaySinh ,GETDATE())
	from KhachHang
	where maKH=@maKH
	return @tuoi
end 

--trigger đảm bảo tuổi đủ mới được mua vé 
go
create trigger tDuTuoi
on BookVe
after insert
as 
begin 
	declare @check int, @doTuoi int  
	select @check=dbo.fTinhTuoi(i.maKH)
	from  inserted i
	select @doTuoi=doTuoiYeuCau
	from Phim p
	if(@check < @doTuoi)
	begin
		print N'Chưa đủ tuổi!'
		rollback;
	end
end
go
--trigger cap nhat ngay mua 
create trigger tNgayMua
on BookVe
after insert 
as
begin
	update BookVe
	set ngayMua = GETDATE()
	from BookVe, inserted
	where BookVe.maBook = inserted.maBook
end

go
--trigger tinh tong tien khi book ve 
/*create trigger tTongTien
on BookVe
after insert 
as 
begin
	update BookVe
	set tongTien= 50000*i.tongSoVe
	from BookVe b, inserted i
	where b.maBook = i.maBook
end 
go*/

-- số lượng phim đã và đang chiếu 
--select count(maPhim) as soPhimDaChieu
--from Phim 

--khách hàng từ trước đến nay
--select count(maKH) as soKhachHangDaDatVe
--from KhachHang 

--nhân viên đang làm việc
--select maNV, hoTen
--from NhanVien
--where tinhTrang = 1

go
CREATE FUNCTION TongHopThongKe
(
    @ngayBatDau DATE,
    @ngayKetThuc DATE
)
RETURNS TABLE
AS 
RETURN (
    SELECT tenPhim,
        COUNT(DISTINCT SuatChieu.ngayChieu) AS tongSoNgayChieu,
        ISNULL(COUNT(BookVe.maBook),0) AS tongSoVeDaBan,
        ISNULL(COUNT(BookVe.maBook),0) * 50000 AS doanhThu
    FROM 
        SuatChieu
    LEFT JOIN
        BookVe ON SuatChieu.maSuat= BookVe.maSuat
	LEFT JOIN 
		Phim ON Phim.maPhim = SuatChieu.maPhim
    WHERE 
        SuatChieu.ngayChieu BETWEEN @ngayBatDau AND @ngayKetThuc
    GROUP BY
        tenPhim
);

go
--SELECT * 
--FROM TongHopThongKe('2-6-2024', '3-6-2024');

--trong 1 tháng trong năm có bao nhiêu vé được bán
GO
CREATE FUNCTION fThongKeTungThangTrongNam
(@nam int)
RETURNS @ThongKe TABLE (
    Thang int,
    TongSoVe int
)
AS
BEGIN
    DECLARE @i int = 1;
    
    WHILE (@i <= 12)
    BEGIN
        INSERT INTO @ThongKe (Thang, TongSoVe)
        SELECT @i, isnull(COUNT(maBook),0) as TongSoVe
        FROM BookVe
        WHERE YEAR(ngayMua) = @nam AND MONTH(ngayMua) = @i;

        SET @i = @i + 1;
    END;

    RETURN;
END;
go
--select *
--from dbo.fThongKeTungThangTrongNam(2024)

/*viết function 
trả về true/false kiểm tra ngày chiếu đã full 6 ca hay chưa
*/
GO
create function fKTfullCa
(@ngayKiemTra date)
returns int
as
begin
	declare @kq int
	if exists(
		select *
		from SuatChieu
		where ngayChieu = @ngayKiemTra and
			maCa = 'Ca 1' and
			maCa = 'Ca 2'  and
			maCa = 'Ca 3'  and
			maCa = 'Ca 4'  and
			maCa = 'Ca 5'  and
			maCa = 'Ca 6'  
			)
	begin
		set @kq= 1;
	end
	else
	begin
		set @kq= 0;
	end
	return @kq
end
go
--declare @kt int
--set @kt= dbo.fKTfullCa('2-6-2024')
--print @kt

/*trả về bảng chứa các ca chưa chiếu khi nhập ngày chiếu
*/
GO
create function fCaChuaChieu
( @ngayKT date)
returns table
as return 
(
	select maCa, tenCa
	from CaChieu
	where maCa not in(select maca
						from SuatChieu
						where ngayChieu= @ngayKT)
)
go
--select *
--from fCaChuaChieu('2-6-2024')

--trả về bảng chứa các phòng chưa chiếu khi nhập ngày và ca chiếu
GO
CREATE FUNCTION fPhongChuaChieu
(	
    @ngayKT DATE,
    @caKT VARCHAR(20)
)
RETURNS TABLE
AS 
RETURN 
(
    SELECT PhongChieu.maPhong
    FROM PhongChieu
    WHERE PhongChieu.maPhong NOT IN
    (
        SELECT SuatChieu.maPhong
        FROM SuatChieu
        WHERE SuatChieu.ngayChieu = @ngayKT
        AND SuatChieu.maCa = @caKT
    )
);

go
--select *
--from fPhongChuaChieu('2-6-2024','CC1')

--viết function khi nhập ngày chiếu thì xuất các ca chiếu mà tại ca chiếu nớ chưa full 4 phòng chiếu
go
CREATE FUNCTION fKTTheoCa
(
    @ngayKT DATE
)
RETURNS TABLE
AS 
RETURN (
    SELECT c.maCa,c.tenCa, c.thoiGianBatDau
    FROM CaChieu c
    LEFT JOIN SuatChieu s ON c.maCa = s.maCa AND s.ngayChieu = @ngayKT
    GROUP BY c.maCa,c.tenCa, c.thoiGianBatDau
    HAVING COUNT(s.maPhong) < (SELECT COUNT(*) FROM PhongChieu)
);
GO

go
select *
from fKTTheoCa('4-4-2024')
-----------------------------------------------------------------------------------------------------///////////////////////////////////////////////////////////////////////

/*ADD SOLUONGTOIDA VAO TABLE VE TRIGGER = TONG SO GHE TABLE PHONG CHIEU , BO TONG SO VE TRONG BOOKVE*/ 
--trigger bang so luong ghe
go 
create trigger tLaySoLuongGhe
on SuatChieu
after insert
as
begin
	update Ve
	set soLuongToiDa = p.tongSoGhe
	from Ve v, inserted i, PhongChieu p,BookVe b
	where v.maVe = b.maVe and b.maSuat = i.maSuat and p.maPhong = i.maPhong
end
go
--insert into SuatChieu (maPhim,maPhong,maCa,ngayChieu)
--values	('P1','PC1','CC4','6-2-2024')

--trigger giam so luong ghe 
go
CREATE TRIGGER tGiamSoGhe
ON Ve
AFTER INSERT
AS 
BEGIN
    UPDATE Ve
    SET soLuongDaBan = soLuongDaBan + (
        SELECT COUNT(i.maVe)
        FROM inserted i
        JOIN BookVe b ON i.maVe = b.maVe
        WHERE i.maVe = ve.maVe 
    )
    FROM Ve
    JOIN BookVe ON BookVe.maVe = ve.maVe
END;

go
--suat chieu nhap vao da co bao nhieu ghe da dat 
go
create function fSoGheDaDat
( @maSuat varchar(20) )
returns table  
as 
return ( 
	select bg.maGhe, g.tinhTrang
	from SuatChieu s, BookGhe bg, BookVe bv, Ghe g
	where s.maSuat = bv.maSuat 
			and bv.maBook = bg.maBook 
			and g.maGhe = bg.maGhe
			and s.maSuat = @maSuat 
); 
go

declare @masuatTEST varchar(20)
set @masuatTEST = 'SC1'
select *
from dbo.fSoGheDaDat(@masuatTEST)
--

--khi nhập số điện thoại thì xuất ra tên kh, ghế, maSC
go
ALTER function fXuatTT
( @sdt char(10) )
returns table
as 
return(
	select k.hoTen, g.maGhe,b.ngayMua ,s.maCa, s.maPhim, s.maPhong
	from KhachHang k, SuatChieu s, BookGhe g,BookVe b
	where k.sdt = @sdt and 
		b.maKH = k.maKH and
		b.maSuat = s.maSuat and
		b.maBook = g.maBook
);

declare @sdt char(10)
set @sdt= '0900000008'

select * 
from dbo.fXuatTT(@sdt)