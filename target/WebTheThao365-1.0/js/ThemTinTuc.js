var url = location.href.split('?id=');
var id = null
if (url.length > 1) {
    var giaTri = url[url.length - 1];
    id = giaTri
    fetch("/WebTheThao365/api/tintuc?id=" + giaTri)
        .then(TraVe => TraVe.json())
        .then(DL => {
            document.getElementById("tieuDe").value = DL.tieuDe
            document.getElementById("loai").value = DL.theLoaiId
            document.getElementById("tomTat").value = DL.tomTat
            document.getElementById("hinh").value = DL.hinh
            document.getElementById("noiDungT").value = DL.noiDung
        })
}
function luu() {
    var tieuDe = document.getElementById("tieuDe").value
    var loai = document.getElementById("loai").value
    var tomTat = document.getElementById("tomTat").value
    var hinh = document.getElementById("hinh").value
    var noiDungT = document.getElementById("noiDungT").value
    if (!tieuDe || !tomTat || !hinh || !noiDungT) {
        document.getElementById("loi").innerHTML = "Vui lòng nhập đầy đủ thông tin của tin!"
    } else {
        var Tieude = new Headers();
        Tieude.append("Content-Type", "application/json");

        var TuyChinhYC = {
            method: 'POST',
            headers: Tieude,
            body: JSON.stringify({
                "id": id,
                "tieuDe": tieuDe,
                "tomTat": tomTat,
                "noiDung": noiDungT,
                "hinh": hinh,
                "theLoaiId": loai
            }),
            redirect: 'follow'
        };

        fetch("/WebTheThao365/api/tintuc", TuyChinhYC)
            .then(TraVe => TraVe.json())
            .then(DL => {
                if (DL.ma == "OK") {
                    window.alert(DL.noiDung)
                    window.location = "QuanLy.html";
                } else {
                    document.getElementById("loi").innerHTML = DL.noiDung
                }
            })
    }
}