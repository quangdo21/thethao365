fetch('/WebTheThao365/api/nguoidung')
    .then(TraVe => TraVe.json())
    .then(data => {
        html = '<p>Họ và Tên: ' + data.hoTen + '</p><p>Ngày sinh: ' + dinhDang(data.ngaySinh) + '</p><p>Giới tính: ' + ((data.gioiTinh) ? "Nam" : "Nữ") + '</p><p>Số điện thoại: ' + data.soDT + '</p><p>Email: ' + data.email + '</p><p><a href="CapNhatThongTinCaNhan.html">Chỉnh sửa thông tin</a></p>';
        console.log(html);
        document.getElementById('info').innerHTML = html;
    })

fetch("/WebTheThao365/api/taikhoan")
    .then(Trave => Trave.json())
    .then(DL => {
        document.getElementsByClassName("TTTK").item(0).innerHTML = "Tên tài khoản: " + DL.dulieu;
    })

function dinhDang(thoiGian) {
    thoiGian = new Date(thoiGian);
    var ngay = ('0' + thoiGian.getDate()).slice(-2);
    var thang = ('0' + (thoiGian.getMonth() + 1)).slice(-2);
    var nam = thoiGian.getFullYear();
    return ngay + '/' + thang + '/' + nam;
}