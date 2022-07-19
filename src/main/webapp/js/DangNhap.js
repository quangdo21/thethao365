function dangnhap() {
    var tenDangNhap = document.getElementById('tenDangNhap').value;
    var matKhau = document.getElementById('matKhau').value;
    if (!tenDangNhap || !matKhau) {
        document.getElementById("loi").innerHTML = "Vui lòng nhập đủ tên đăng nhập và mật khẩu";
    } else {
        var tieude = new Headers();
        tieude.append("Content-Type", "application/json");
        var duLieu = JSON.stringify({
            "tenDangNhap": tenDangNhap,
            "matKhau": matKhau
        });

        var tuyChonYC = {
            method: 'POST',
            headers: tieude,
            body: duLieu,
            redirect: 'follow'
        };

        fetch("/WebTheThao365/api/dangnhap", tuyChonYC)
            .then(traVe => traVe.json())
            .then(dl => {
                if (dl.ma == "OK") {
                    window.alert(dl.noiDung)
                    window.location = "TrangChu.html";
                } else {
                    document.getElementById("loi").innerHTML = dl.noiDung;
                }
            })
            .catch(loi => console.log('loi', loi));
    }
}