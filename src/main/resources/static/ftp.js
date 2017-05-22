function showList(url){
    $.ajax({
        url : url,
        method:'post',
        async:false,
        success : function(response) {
            var listDirectory = (response.responseText).listDirectory;
            var listFile =(response.responseText).listFile;
            $("#container").empty();
            for(var i = 0;i<listDirectory.length();i++){
                $("#container").append("<img src="+ctx+"/static/images/ftp/directory.jpg width='25px' height='25px'> <a onclick='showList(this.id)' href='#' style='color:blue' name ='"+listDirectory[i].localPath+" 'id='"+ctx+"/ftp/showList?remotePath="+listDirectory[i].localPath+"'>"+listDirectory[i].name+"</a><br/>");
            }
            for(var i = 0;i<listFile.length();i++){
                $("#container").append("<img src="+ctx+"/static/images/ftp/file.jpg width='25px' height='25px'> <a onclick='showList(this.id)' href='#' style='color:green' id='"+ctx+"/ftp/showList?filename="+listFile[i].name+"'>"+listFile[i].name+"</a><br/>");
            }
        }
    });
}