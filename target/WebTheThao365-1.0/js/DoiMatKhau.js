function doiMK() {
    var MK = document.getElementById("MK").value;
    var reMK = document.getElementById("reMK").value;
    var MKcu = document.getElementById("MKcu").value;
    if (!MK || !reMK || !MKcu) {
        document.getElementsByClassName("TBloi").item(0).innerHTML = "Vui lòng nhập đủ tất cả các trường"
    } else if (MK != reMK)
        document.getElementsByClassName("TBloi").item(0).innerHTML = "Mật khẩu mới phải trùng với xác nhận mật khẩu mới!"
    else {
        var tieude = new Headers();
        tieude.append("Content-Type", "application/json");

        var requestOptions = {
            method: 'PUT',
            headers: tieude,
            body: JSON.stringify([
                MKcu,
                MK
            ]),
            redirect: 'follow'
        };

        fetch("/WebTheThao365/api/taikhoan", requestOptions)
            .then(TraVe => TraVe.json())
            .then(DL => {
                if (DL.ma == "E") {
                    document.getElementsByClassName("TBloi").item(0).innerHTML = DL.noiDung
                } else {
                    window.alert(DL.noiDung)
                    window.location = "DangNhap.html";
                }
            })
    }
}