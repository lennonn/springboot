package com.zl.service.impl;

import com.zl.entity.FtpAttr;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/5/23.
 */
public class FtpClientOpr {
    private static final Log log = LogFactory.getLog(FtpClientOpr.class);
    private FtpAttr ftpAttr;
    private FTPClient ftpClient;

    public FtpClientOpr(FtpAttr ftpAttr) {
        this.ftpAttr = ftpAttr;
        ftpClient = new FTPClient();
    }

    public void connect() {
        try {
            ftpClient.connect(ftpAttr.getUrl(), ftpAttr.getPort());
        } catch (IOException ie) {
            log.error("can not connected ftp");
        }
    }

    public boolean login() {
        boolean isSuccess = false;
        try {
            isSuccess = ftpClient.login(ftpAttr.getUsername(), ftpAttr.getPassword());
        } catch (IOException e) {
            log.error("login ftp failed!");
        }
        return isSuccess;
    }

    public void getReplyCode() {
        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                log.error("when ftpClient disconnected,have a error occcur!");
            }
        }
    }

    public void changeWorkingDirectory() {
        try {
            if (ftpAttr.getRemotePath().endsWith("/"))
                ftpClient.changeWorkingDirectory(ftpAttr.getRemotePath());// 转移到FTP服务器目录
            else
                ftpClient.changeWorkingDirectory(ftpAttr.getRemotePath() + "/");// 转移到FTP服务器目录
        } catch (IOException e) {
            log.error("change Working Directory is failed");
        }
    }

    public void logout() {
        try {
            ftpClient.logout();
        } catch (IOException e) {
            log.error("ftpClient logout falied");
        }
    }

    public void close() {
        try {
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        } catch (IOException ioe) {
            log.error("close ftpClient have a error");
        }
    }

    public boolean download() {
        OutputStream os = null;
        boolean isDownload =false;
        try {

            FTPFile[] fs = ftpClient.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(ftpAttr.getFileName())) {
                    File localFile = new File("F:\\FromGit\\springboot\\src\\main\\resources\\static\\file\\" + ff.getName());
                    if (!localFile.exists())
                        localFile.createNewFile();
                    os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(ff.getName(), os);
                    if(os!=null)
                        isDownload=true;
                    os.close();
                    break;
                }
            }

        } catch (IOException e) {
            log.error("failed!");
        }finally {
            if(ftpClient.isConnected())
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return isDownload;
    }
}
