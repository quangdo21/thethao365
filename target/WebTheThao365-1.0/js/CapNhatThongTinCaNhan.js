fetch("/WebTheThao365/api/nguoidung")
    .then(TraVe => TraVe.json())
    .then(DL => {
        document.getElementById("hoTen").value = DL.hoTen
        document.getElementById("ngaySinh").value = dinhDang(DL.ngaySinh)
        document.getElementById("dienThoai").value = DL.soDT
        document.getElementById("email").value = DL.email
        if (DL.gioiTinh == true) {
            document.getElementsByClassName("nam").item(0).checked = true
        } else {
            document.getElementsByClassName("nu").item(0).checked = true
        }
    })
    .catch(error => console.log('error', error));

function luu() {
    var hoTen = document.getElementById("hoTen").value;
    var ngaySinh = document.getElementById("ngaySinh").value;
    var gioiTinh;
    if (document.getElementsByClassName("nam").item(0).checked == true) {
        gioiTinh = true
    } else {
        gioiTinh = false
    }
    console.log(gioiTinh)
    var dienThoai = document.getElementById("dienThoai").value;
    var email = document.getElementById("email").value;
    if (hoTen == '' || ngaySinh == '' || dienThoai == '' || email == '')
        document.getElementById("loi").innerHTML = "Vui lòng nhập đủ tất cả các trường dữ liệu";
    else {
        var tieude = new Headers();
        tieude.append("Content-Type", "application/json");

        var TuyChonYC = {
            method: 'PUT',
            headers: tieude,
            body: JSON.stringify({
                "hoTen": hoTen,
                "gioiTinh": gioiTinh,
                "ngaySinh": ngaySinh,
                "email": email,
                "soDT": dienThoai
            }),
            redirect: 'follow'
        };
        fetch("/WebTheThao365/api/nguoidung", TuyChonYC)
            .then(TraVe => TraVe.json())
            .then(DL => {
                console.log(DL)
                if (DL.ma == "OK") {
                    window.alert(DL.noiDung)
                    window.location = "ThongTinCaNhan.html"
                } else {
                    document.getElementById("loi").innerHTML = DL.noiDung
                }
            })
            .catch(error => console.log('error', error));
    }
}

function dinhDang(thoiGian) {
    thoiGian = new Date(thoiGian);
    var ngay = ('0' + thoiGian.getDate()).slice(-2);
    var thang = ('0' + (thoiGian.getMonth() + 1)).slice(-2);
    var nam = thoiGian.getFullYear();
    return nam + '-' + thang + '-' + ngay;
}