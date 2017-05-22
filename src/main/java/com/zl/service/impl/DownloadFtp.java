package com.zl.service.impl;

import com.zl.util.StreamConvert;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by Administrator on 2017/5/7.
 */
@Component
public class DownloadFtp {
    /**
     * Description:从ftp服务器下载文件 version1.0
     *
     * @param url
     *            FTP服务器hostname
     * @param port
     *            FTP服务器端口
     * @param username
     *            FTP登陆账号
     * @param password
     *            FTP登陆密码
     * @param path
     *            FTP服务器上的相对路径
     * @param filename
     *            要下载的文件名
     * @return 成功返回true，否则返回false
     */
    public InputStream downFile(String url,// FTP服务器hostname
                                int port,// FTP服务器端口
                                String username,// FTP登陆账号
                                String password,// FTP登陆密码
                                String remotePath,// FTP服务器上的相对路径
                                String fileName,// 要下载的文件名
                                String localPath// 下载后保存到本地的路劲
    ) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        OutputStream os=null;
        InputStream is =null;
        try {
            int reply;
            ftp.connect(url, port);
// 如果采用端口默认，可以使用ftp.connect（url）的方式直接连接FTP服务器
            ftp.login(username, password);// 登陆
// System.out.println("login!");
            reply = ftp.getReplyCode();
// System.out.println("reply:" + reply);
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
               // return success;
            }
            ftp.changeWorkingDirectory(remotePath+"/");// 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
// System.out.println("名称："+ff.getName()+"类型："+ff.getType());
                if (ff.getName().equals(fileName)) {
                    //File localFile = new File(localPath + '/' + ff.getName());
                     os = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
                    ftp.retrieveFile(ff.getName(), os);
                   is= StreamConvert.parse(os);
                    os.close();
                }
            }
            System.out.println("upload success!");
            ftp.logout();
// System.out.println("logout!");
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            if(is!=null)
                is.close();
            if (ftp.isConnected()) {

                ftp.disconnect();
                }
            }catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return is;
    }
}
