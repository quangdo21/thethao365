var url = location.href.split('?id=');
var tin = url[url.length - 1];
fetch('/WebTheThao365/api/tintuc?id=' + tin)
    .then(response => response.json())
    .then(d => {
        var html = '<div class="bao"><div class="tieuDe">' + d.tieuDe + '</div><div class="thoiGian">' + dinhDang(d.ngayTao) + '</div><div class="moTaN">' + d.tomTat + '</div><img src="' + d.hinh + '" alt="Ảnh"><div class="chiTiet"><p>' + d.noiDung + '</p></div></div>';
        document.getElementById('ndTrai').innerHTML = html;
    })
fetch('/WebTheThao365/api/tintuc?danhsach=10')
    .then(response => response.json())
    .then(data => {
        var htmls = data.map(function (d) {
            return '<div class="TDTin"><a href="TrangTin.html?id=' + d.id + '">' + d.tieuDe + '</a></div>';
        })
        html = htmls.join(' ');
        document.getElementById('ndPhai').innerHTML = html;
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

function luuDanhSachYeuThich() {
    var DSYeuThich = []
    if (layCookieTheoTen("DSYeuThich") != null)
        DSYeuThich = JSON.parse(layCookieTheoTen("DSYeuThich"))
    if (DSYeuThich.includes(tin)) {
        DSYeuThich = DSYeuThich.filter(gt => gt != tin)
    } else {
        DSYeuThich.push(tin)
    }
    document.cookie = "DSYeuThich=" + JSON.stringify(DSYeuThich)
}

yeuthich();

function chuyenTim() {
    luuDanhSachYeuThich();
    yeuthich();
}

function yeuthich() {
    var DSYeuThich = []
    if (layCookieTheoTen("DSYeuThich") != null)
        DSYeuThich = JSON.parse(layCookieTheoTen("DSYeuThich"))
    if (DSYeuThich.includes(tin)) {
        document.getElementById("tim").innerHTML = '<div class="yeuThich" style="color: red; font-weight: bold"><i class="ti-heart"> Yêu thích</i></div>'
    } else {
        document.getElementById("tim").innerHTML = '<div class="yeuThich" style="color: black; font-weight: bold"><i class="ti-heart"> Yêu thích</i></div>'
    }
}

function dinhDang(thoiGian) {
    thoiGian = new Date(thoiGian);
    var ngay = ('0' + thoiGian.getDate()).slice(-2);
    var thang = ('0' + (thoiGian.getMonth() + 1)).slice(-2);
    var nam = thoiGian.getFullYear();
    return ngay + '/' + thang + '/' + nam;
}