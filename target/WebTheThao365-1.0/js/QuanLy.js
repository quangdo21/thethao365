var s = '<table border="1"><thead><tr><th>ID</th><th>Ngày đăng</th><th>Hình ảnh</th><th>Tiêu đề</th><th>Loại tin</th><th>Tóm tắt</th><th>Hành động</th></tr></thead><tbody>'
fetch("/WebTheThao365/api/tintuc?danhsach=all")
    .then(TraVe => TraVe.json())
    .then(data => {
        var htmls = data.map(function (d) {
            return '<tr><td>' + d.id + '</td><td>' + dinhDang(d.ngayTao) + '</td><td style="width: 10%;"><img src="' + d.hinh + '" alt="Ảnh"></td><td >' + d.tieuDe + '</td><td >' + (d.theLoaiId == 1 ? "Bóng đá" : d.theLoaiId == 2 ? "Bóng chuyền" : d.theLoaiId == 3 ? "Bóng rổ" : "Esports") + '</td><td >' + d.tomTat + '</td><td><a href = "ThemTinTuc.html?id=' + d.id + '" >Sửa</a><button id="nutXoa" href="#" value="' + d.id + '" onclick="xoa(this)" >Xoá</button></td></tr>';
        })
        s += htmls.join(' ');
        s += '</tbody></table>'
        document.getElementById("ndTin").innerHTML = s;
    })
    .catch(error => console.log('error', error));


function timTin() {
    var noidung = document.getElementById('ipTK').value;
    if (noidung) {
        s = '<table border="1"><thead><tr><th>ID</th><th>Ngày đăng</th><th>Hình ảnh</th><th>Tiêu đề</th><th>Loại tin</th><th>Tóm tắt</th><th>Hành động</th></tr></thead><tbody>'
        fetch("/WebTheThao365/api/tintuc?timkiem=" + noidung)
            .then(TraVe => TraVe.json())
            .then(data => {
                var htmls = data.map(function (d) {
                    return '<tr><td>' + d.id + '</td><td>' + dinhDang(d.ngayTao) + '</td><td style="width: 10%;"><img src="' + d.hinh + '" alt="Ảnh"></td><td >' + d.tieuDe + '</td><td >' + (d.theLoaiId == 1 ? "Bóng đá" : d.theLoaiId == 2 ? "Bóng chuyền" : d.theLoaiId == 3 ? "Bóng rổ" : "Esports") + '</td><td >' + d.tomTat + '</td><td><a href="ThemTinTuc.html?id=' + d.id + '">Sửa</a><a href="#" value="' + d.id + '" onclick="xoa(this)" >Xoá</a></td></tr>';
                })
                s += htmls.join(' ');
                s += '</tbody></table>'
                document.getElementById("ndTin").innerHTML = s;
            })
            .catch(error => console.log('error', error));
    }
}

function dinhDang(thoiGian) {
    thoiGian = new Date(thoiGian);
    var ngay = ('0' + thoiGian.getDate()).slice(-2);
    var thang = ('0' + (thoiGian.getMonth() + 1)).slice(-2);
    var nam = thoiGian.getFullYear();
    return ngay + '/' + thang + '/' + nam;
}
function xoa(GT) {
    if (window.confirm("Bạn muốn xóa tin?")) {
        var TuyChinhYC = {
            method: 'DELETE',
            redirect: 'follow'
        };

        fetch("/WebTheThao365/api/tintuc?id=" + GT.value, TuyChinhYC)
            .then(TraVe => TraVe.json())
            .then(DL => {
                if (DL.ma == "OK") {
                    window.alert(DL.noiDung)
                    window.location.reload()
                } else {
                    window.alert(DL.noiDung)
                    window.location.reload()
                }
            })
    }
}