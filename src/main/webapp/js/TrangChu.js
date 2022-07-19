// Lấy tin tức mới nhất theo loại
fetch('/WebTheThao365/api/tintuc?hot1id=1')
    .then(response => response.json())
    .then(data => {
        html = '<img src="' + data.hinh + '" alt="Ảnh"><div class="tieuDe"><a href="TrangTin.html?id=' + data.id + '">' + data.tieuDe + '</a></div><div class="thoiGian">' + dinhDang(data.ngayTao) + '</div><div class="moTaNgan">' + data.tomTat + '</div>'
        document.getElementById('traiBD').innerHTML = html;
    })
fetch('/WebTheThao365/api/tintuc?hot1id=2')
    .then(response => response.json())
    .then(data => {
        html = '<img src="' + data.hinh + '" alt="Ảnh"><div class="tieuDe"><a href="TrangTin.html?id=' + data.id + '">' + data.tieuDe + '</a></div><div class="thoiGian">' + dinhDang(data.ngayTao) + '</div><div class="moTaNgan">' + data.tomTat + '</div>'
        document.getElementById('traiBC').innerHTML = html;
    })
fetch('/WebTheThao365/api/tintuc?hot1id=3')
    .then(response => response.json())
    .then(data => {
        html = '<img src="' + data.hinh + '" alt="Ảnh"><div class="tieuDe"><a href="TrangTin.html?id=' + data.id + '">' + data.tieuDe + '</a></div><div class="thoiGian">' + dinhDang(data.ngayTao) + '</div><div class="moTaNgan">' + data.tomTat + '</div>'
        document.getElementById('traiBR').innerHTML = html;
    })
fetch('/WebTheThao365/api/tintuc?hot1id=4')
    .then(response => response.json())
    .then(data => {
        html = '<img src="' + data.hinh + '" alt="Ảnh"><div class="tieuDe"><a href="TrangTin.html?id=' + data.id + '">' + data.tieuDe + '</a></div><div class="thoiGian">' + dinhDang(data.ngayTao) + '</div><div class="moTaNgan">' + data.tomTat + '</div>'
        document.getElementById('traiE').innerHTML = html;
    })

// Lấy 3 tin tức mới tiếp theo theo loại
fetch('/WebTheThao365/api/tintuc?hot3id=1')
    .then(response => response.json())
    .then(data => {
        var htmls = data.map(function (d) {
            return '<div class="bao"><img src="' + d.hinh + '" alt="Ảnh"><div class="noiDung"><div class="tieuDe"><a href="TrangTin.html?id=' + d.id + '">' + d.tieuDe + '</a></div><div class="thoiGian">' + dinhDang(d.ngayTao) + '</div><div class="moTaNgan">' + d.tomTat + '</div></div></div>';
        })
        html = htmls.join(' ');
        document.getElementById('phaiBD').innerHTML = html;
    })
fetch('/WebTheThao365/api/tintuc?hot3id=2')
    .then(response => response.json())
    .then(data => {
        var htmls = data.map(function (d) {
            return '<div class="bao"><img src="' + d.hinh + '" alt="Ảnh"><div class="noiDung"><div class="tieuDe"><a href="TrangTin.html?id=' + d.id + '">' + d.tieuDe + '</a></div><div class="thoiGian">' + dinhDang(d.ngayTao) + '</div><div class="moTaNgan">' + d.tomTat + '</div></div></div>';
        })
        html = htmls.join(' ');
        document.getElementById('phaiBC').innerHTML = html;
    })
fetch('/WebTheThao365/api/tintuc?hot3id=3')
    .then(response => response.json())
    .then(data => {
        var htmls = data.map(function (d) {
            return '<div class="bao"><img src="' + d.hinh + '" alt="Ảnh"><div class="noiDung"><div class="tieuDe"><a href="TrangTin.html?id=' + d.id + '">' + d.tieuDe + '</a></div><div class="thoiGian">' + dinhDang(d.ngayTao) + '</div><div class="moTaNgan">' + d.tomTat + '</div></div></div>';
        })
        html = htmls.join(' ');
        document.getElementById('phaiBR').innerHTML = html;
    })
fetch('/WebTheThao365/api/tintuc?hot3id=4')
    .then(response => response.json())
    .then(data => {
        var htmls = data.map(function (d) {
            return '<div class="bao"><img src="' + d.hinh + '" alt="Ảnh"><div class="noiDung"><div class="tieuDe"><a href="TrangTin.html?id=' + d.id + '">' + d.tieuDe + '</a></div><div class="thoiGian">' + dinhDang(d.ngayTao) + '</div><div class="moTaNgan">' + d.tomTat + '</div></div></div>';
        })
        html = htmls.join(' ');
        document.getElementById('phaiE').innerHTML = html;
    })
function dinhDang(thoiGian) {
    thoiGian = new Date(thoiGian);
    var ngay = ('0' + thoiGian.getDate()).slice(-2);
    var thang = ('0' + (thoiGian.getMonth() + 1)).slice(-2);
    var nam = thoiGian.getFullYear();
    return ngay + '/' + thang + '/' + nam;
}