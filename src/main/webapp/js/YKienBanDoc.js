var s = '<table border="1"><thead><tr><th>ID</th><th>Ngày gửi</th><th>Họ Tên</th><th>Email</th><th>Nội dung</th></tr></thead><tbody>'
fetch("/WebTheThao365/api/ykien")
    .then(TraVe => TraVe.json())
    .then(dl => {
        var htmls = dl.map(function (d) {
            return '<tr><td>' + d.id + '</td><td>' + dinhDang(d.ngay) + '</td><td>' + d.hoTen + '</td><td >' + d.email + '</td><td style="width: 50%">' + d.noiDung + '</td></tr>';
        })
        s += htmls.join(' ');
        s += '</tbody></table>'
        document.getElementById("ndTin").innerHTML = s;
    })
    .catch(error => console.log('error', error));
function dinhDang(thoiGian) {
    thoiGian = new Date(thoiGian);
    var ngay = ('0' + thoiGian.getDate()).slice(-2);
    var thang = ('0' + (thoiGian.getMonth() + 1)).slice(-2);
    var nam = thoiGian.getFullYear();
    return ngay + '/' + thang + '/' + nam;
}