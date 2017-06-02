package com.zl.controller;

import com.zl.entity.DtsFtpFile;
import com.zl.entity.FtpAttr;
import com.zl.service.AbstractFtpClientOpr;
import com.zl.service.impl.DownloadFtp;
import com.zl.service.impl.ListMapFtp;
import com.zl.util.FileOperater;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/7.
 */
@Controller
@RequestMapping("/ftp")
public class FTPFileListController {
    private Logger logger = LogManager.getLogger(FTPFileListController.class);

    @Autowired
    private ListMapFtp listMapFtp;
    @Autowired
    private DownloadFtp downloadFtp;

    @RequestMapping("/portal")
    public String portal(){
        return "portal";
    }

    @RequestMapping("/showFTPList")
    public String showList(HttpServletRequest request, Model model) throws IOException {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        String remotePath = request.getParameter("remotePath");// 获得当前路径
        if (remotePath != null) {
            logger.debug("remotePath--->" + remotePath);
            session.setAttribute("sessionPath", remotePath);// 将当前路径保存到session中
        }
        if (remotePath == null) {
            remotePath = "";
        }
        String filename = request.getParameter("filename");// 获得当前文件的名称
        if (filename != null) {
            logger.debug("filename:---> " + filename);
        }
        FtpAttr fa= new FtpAttr("127.0.0.1", 21, "admin", "admin",
                remotePath, filename, "D:/test/download/");
        FTPClient ftpClient =new FTPClient();
        AbstractFtpClientOpr afco =new ListMapFtp(fa,ftpClient, FileOperater.DOWNLOAD);
        afco.procesor();
        List<List<DtsFtpFile>> list = afco.getListMap();// 获得ftp对应路径下的所有目录和文件信息
        List<DtsFtpFile> listDirectory = list.get(0);// 获得ftp该路径下的所有目录信息
        List<DtsFtpFile> listFile = list.get(1);// 获得ftp该路径下所有的文件信息

        if (remotePath != null && filename == null) {// 如果前台点击的是目录则显示该目录下的所有目录和文件
            model.addAttribute("listDirectory", listDirectory);
            model.addAttribute("listFile", listFile);
        } /*else if (filename != null) {// 如果前台点击的是文件，则下载该文件
            String sessionPath = (String) session.getAttribute("sessionPath");// 获得保存在session中的当前路径信息
            downloadFtp.downFile("192.168.50.23", 21, "admin", "123456",
                    sessionPath, filename, "D:/test/download/");
        }*/
        return "portal";
    }
    @RequestMapping("ftpDownload")
    public void downloadFile(HttpServletRequest request,HttpServletResponse response){
        OutputStream out=null;
        ServletOutputStream sout=null;
        InputStream is=null;
        try {
            String filename = request.getParameter("filename");// 获得当前文件的名称
            String remotePath = request.getParameter("remotePath");// 获得当前路径
            FtpAttr fa= new FtpAttr("127.0.0.1", 21, "admin", "admin",
                    remotePath, filename, "D:/test/download/");
            FTPClient ftpClient =new FTPClient();
            AbstractFtpClientOpr afco =new DownloadFtp(fa,ftpClient, FileOperater.DOWNLOAD);
            afco.procesor();
            //下载机器码文件
            response.setHeader("conent-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(filename.getBytes("ISO-8859-1"), "UTF-8"));
            sout= response.getOutputStream();
            is= new FileInputStream(new File("F:\\FromGit\\springboot\\src\\main\\resources\\static\\file\\"+filename));
            byte[] b =new byte[1024];
            int n;
            while((n=is.read(b))!=-1){
                sout.write(b,0,n);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(is!=null)
                    is.close();
                if(sout!=null)
                    sout.flush();
                    sout.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }
    @RequestMapping("/test")
    public String index(Model map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "http://blog.didispace.com");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "test";
    }
    @RequestMapping(value = "directJsp")
    public String directJsp() {
        logger.debug("--->into FtpAttr/FtpAttr-list.jsp");
        return "FtpAttr/FtpAttr-list";
    }
}
