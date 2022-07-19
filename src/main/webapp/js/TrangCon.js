var url = location.href.split('?id=');
var giaTri = url[url.length - 1][0];
console.log(giaTri)
if (giaTri == 0) {
    var chuoi = url[url.length - 1].split("nd=");
    var noiDung = chuoi[chuoi.length - 1]
    fetch('/WebTheThao365/api/tintuc?timkiem=' + noiDung)
        .then(TraVe => TraVe.json())
        .then(duLieu => {
            console.log(duLieu)
            var htmls = duLieu.map(function (d) {
                return '<div class="bao"><img src="' + d.hinh + '" alt="Ảnh"><div class="chiTiet"><div class="tieuDe"><a href="TrangTin.html?id=' + d.id + '">' + d.tieuDe + '</a></div><div class="thoiGian">' + dinhDang(d.ngayTao) + '</div><div class="moTaNgan">' + d.tomTat + '</div></div></div>';
            })
            html = htmls.join(' ');
            document.getElementById('ndTrai').innerHTML = html;
        })
} else {
    fetch('/WebTheThao365/api/tintuc?theloai=' + giaTri)
        .then(response => response.json())
        .then(data => {
            var htmls = data.map(function (d) {
                return '<div class="bao"><img src="' + d.hinh + '" alt="Ảnh"><div class="chiTiet"><div class="tieuDe"><a href="TrangTin.html?id=' + d.id + '">' + d.tieuDe + '</a></div><div class="thoiGian">' + dinhDang(d.ngayTao) + '</div><div class="moTaNgan">' + d.tomTat + '</div></div></div>';
            })
            html = htmls.join(' ');
            document.getElementById('ndTrai').innerHTML = html;
        })
}
fetch('/WebTheThao365/api/tintuc?danhsach=10')
    .then(response => response.json())
    .then(data => {
        var htmls = data.map(function (d) {
            return '<div class="TDTin"><a href="TrangTin.html?id=' + d.id + '">' + d.tieuDe + '</a></div>';
        })
        html = htmls.join(' ');
        document.getElementById('ndPhai').innerHTML = html;
    })
function dinhDang(thoiGian) {
    thoiGian = new Date(thoiGian);
    var ngay = ('0' + thoiGian.getDate()).slice(-2);
    var thang = ('0' + (thoiGian.getMonth() + 1)).slice(-2);
    var nam = thoiGian.getFullYear();
    return ngay + '/' + thang + '/' + nam;
}