function dangky() {
    var hoTen = document.getElementById("hoTen").value;
    var ngaySinh = document.getElementById("ngaySinh").value;
    var gioiTinh = document.getElementById("gioiTinh").value;
    var dienThoai = document.getElementById("dienThoai").value;
    var email = document.getElementById("email").value;
    var tenDangNhap = document.getElementById("tenDangNhap").value;
    var matKhau = document.getElementById("matKhau").value;
    var re_matKhau = document.getElementById("re_matKhau").value;
    if (hoTen == '' || ngaySinh == '' || gioiTinh == '' || dienThoai == '' || email == '' || tenDangNhap == '' || matKhau == '' || re_matKhau == '')
        document.getElementById("loi").innerHTML = "Vui lòng nhập đủ tất cả các trường dữ liệu";
    else if (matKhau != re_matKhau)
        document.getElementById("loi").innerHTML = "Mật khẩu không trùng khớp";
    else {
        var tieude = new Headers();
        tieude.append("Content-Type", "application/json");

        var tuyChonYC = {
            method: 'POST',
            headers: tieude,
            body: JSON.stringify({
                "nguoidung": {
                    "hoTen": hoTen,
                    "ngaySinh": ngaySinh,
                    "gioiTinh": gioiTinh,
                    "dienThoai": dienThoai,
                    "email": email
                },
                "taikhoan": {
                    "tenDangNhap": tenDangNhap,
                    "matKhau": matKhau
                }
            }),
            redirect: 'follow'
        };

        fetch("/WebTheThao365/api/dangky", tuyChonYC)
            .then(TraVe => TraVe.json())
            .then(DL => {
                if (DL.ma == "OK") {
                    window.alert(DL.noiDung)
                    window.location = "DangNhap.html"
                } else {
                    document.getElementById("loi").innerHTML = DL.noiDung
                }
            })
            .catch(error => console.log('error', error));
    }
}