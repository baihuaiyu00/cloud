var totalFileLength, totalUploaded, fileCount, filesUploaded;

function onUploadFailed(e) {
    alert("服务器繁忙！");
}

function uploadNext(e) {
    var xhr = new XMLHttpRequest();
    var fd = new FormData();
    var file = document.getElementById('files').files[filesUploaded];
    fd.append("multipartFile",e);
    xhr.addEventListener("load",onUploadComplete,false);
    xhr.addEventListener("error", onUploadFailed,false);
    xhr.open("POST","file",true);
    xhr.send(fd);
}

function startUpload(e) {
    totalUploaded = filesUploaded = 0;
    uploadNext(e);
}

window.onload = function () {
        document.getElementsByClassName('fileinput-upload-button')[0].addEventListener('click',startUpload,false);
        alert("button:"+document.getElementsByClassName('fileinput-upload-button')[0]);
    }