// kt tài khoản đã đăng nhập:
fetch("/WebTheThao365/api/taikhoan")
        .then(TraVe => TraVe.json())
        .then(duLieu => {
            if (duLieu.ma == "OK") {
                document.getElementsByClassName("taiKhoan").item(0).innerHTML = '<i class="ti-user"></i><ul><li><a href="ThongTinCaNhan.html">Tài khoản</a></li><li><a href="YeuThich.html">Yêu thích</a></li><li><a href="#" onclick="dangxuat()">Đăng xuất</a></li></ul>'
                if (duLieu.noiDung == "0") {
                    document.getElementsByClassName("menuNgang").item(0).innerHTML = '<ul><li><a href="TrangChu.html">Trang chủ</a></li><li><a href="QuanLy.html">Quản lý</a></li><li><a href="TrangCon.html?id=1">Bóng đá</a></li><li><a href="TrangCon.html?id=2">Bóng chuyền</a></li><li><a href="TrangCon.html?id=3">Bóng rổ</a></li><li><a href="TrangCon.html?id=4">Esports</a></li></ul>'
                    document.getElementsByClassName("menuDoc").item(0).innerHTML = '<ul><li><a href="TrangChu.html">Trang chủ</a></li><li><a href="QuanLy.html">Quản lý</a></li><li><a href="TrangCon.html?id=1">Bóng đá</a></li><li><a href="TrangCon.html?id=2">Bóng chuyền</a></li><li><a href="TrangCon.html?id=3">Bóng rổ</a></li><li><a href="TrangCon.html?id=4">Esports</a></li></ul>'
                } else {
                    document.getElementsByClassName("menuNgang").item(0).innerHTML = '<ul><li><a href="TrangChu.html">Trang chủ</a></li><li><a href="TrangCon.html?id=1">Bóng đá</a></li><li><a href="TrangCon.html?id=2">Bóng chuyền</a></li><li><a href="TrangCon.html?id=3">Bóng rổ</a></li><li><a href="TrangCon.html?id=4">Esports</a></li></ul>'
                    document.getElementsByClassName("menuDoc").item(0).innerHTML = '<ul><li><a href="TrangChu.html">Trang chủ</a></li><li><a href="TrangCon.html?id=1">Bóng đá</a></li><li><a href="TrangCon.html?id=2">Bóng chuyền</a></li><li><a href="TrangCon.html?id=3">Bóng rổ</a></li><li><a href="TrangCon.html?id=4">Esports</a></li></ul>'
                }
            } else {
                document.getElementsByClassName("taiKhoan").item(0).innerHTML = '<i class="ti-user"></i><ul><li><a href="DangNhap.html">Đăng nhập</a></li><li><a href="DangKy.html">Đăng ký</a></li></ul>';
                document.getElementsByClassName("menuNgang").item(0).innerHTML = '<ul><li><a href="TrangChu.html">Trang chủ</a></li><li><a href="TrangCon.html?id=1">Bóng đá</a></li><li><a href="TrangCon.html?id=2">Bóng chuyền</a></li><li><a href="TrangCon.html?id=3">Bóng rổ</a></li><li><a href="TrangCon.html?id=4">Esports</a></li></ul>'
                document.getElementsByClassName("menuDoc").item(0).innerHTML = '<ul><li><a href="TrangChu.html">Trang chủ</a></li><li><a href="TrangCon.html?id=1">Bóng đá</a></li><li><a href="TrangCon.html?id=2">Bóng chuyền</a></li><li><a href="TrangCon.html?id=3">Bóng rổ</a></li><li><a href="TrangCon.html?id=4">Esports</a></li></ul>'
            }
        })
        .catch(loi => console.log('loi', loi));
// Tìm kiếm
function timkiem() {
    var noidung = document.getElementById('inpSearch').value;
    if (noidung)
        window.location = 'TrangCon.html?id=0&nd=' + noidung;
}
function enTimKiem() {
    if (event.keyCode == 13) {
        timkiem();
    }
}
// Gửi phản hồi của người dùng
function gui() {
    var hoTen = document.getElementById("hoTen").value;
    var email = document.getElementById("email").value;
    var noiDung = document.getElementById("noiDung").value;
    if (!hoTen || !email || !noiDung)
        window.alert("Vui lòng nhập đủ thông tin vào khung phản hồi");
    else {
        var TieuDe = new Headers();
        TieuDe.append("Content-Type", "application/json");
        var duLieu = JSON.stringify({
            "hoTen": hoTen,
            "email": email,
            "noiDung": noiDung
        });
        var TuyChinhYC = {
            method: 'POST',
            headers: TieuDe,
            body: duLieu,
            redirect: 'follow'
        };
        fetch("/WebTheThao365/api/ykien", TuyChinhYC)
                .then(TraVe => TraVe.json())
                .then(duLieu => {
                    if (duLieu.ma == 'OK')
                        window.alert("Gửi phản hồi thành công. Cảm ơn bạn đã đóng góp ý kiến với chúng tôi!");
                    window.location.reload()
                })
                .catch(error => console.log('error', error));
    }
}

// Đăng xuất
function dangxuat() {
    fetch("/WebTheThao365/api/dangxuat")
            .then(TraVe => TraVe.json())
            .then(DL => {
                if (DL.ma == "OK") {
                    window.location = "DangNhap.html"
                } else {
                    window.alert("Lỗi!")
                }
            })
            .catch(error => console.log('error', error));
}