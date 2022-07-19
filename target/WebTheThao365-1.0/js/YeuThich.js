var tieude = new Headers();
tieude.append("Content-Type", "application/json");

var TuyChinhYC = {
    method: 'POST',
    headers: tieude,
    body: layCookieTheoTen("DSYeuThich"),
    redirect: 'follow'
};

fetch("/WebTheThao365/api/yeuthich", TuyChinhYC)
    .then(TraVe => TraVe.json())
    .then(DL => {
        var htmls = DL.map(function (d) {
            return '<div class="bao"><img src="' + d.hinh + '"alt="Ảnh"><div class="chiTiet"><div class="tieuDe"><a href="TrangTin.html?id=' + d.id + '">' + d.tieuDe + '</a></div><div class="thoiGian">' + dinhDang(d.ngayTao) + '</div><div class="moTaNgan">' + d.tomTat + '</div></div></div>';
        })
        html = htmls.join(' ');
        document.getElementById('ndYeuThich').innerHTML = html;
    })

function layCookieTheoTen(ten) {
    var cookie = document.cookie
    if (cookie.length < 1)
        return null
    var mangGT = cookie.split(";")
    for (var i = 0; i < mangGT.length; i++) {
        var giaTri = mangGT[i].split("=")
        if (giaTri[0] == ten)
            return giaTri[1];
    }
    return null
}

function dinhDang(thoiGian) {
    thoiGian = new Date(thoiGian);
    var ngay = ('0' + thoiGian.getDate()).slice(-2);
    var thang = ('0' + (thoiGian.getMonth() + 1)).slice(-2);
    var nam = thoiGian.getFullYear();
    return ngay + '/' + thang + '/' + nam;
}