package com.zl.service;

import com.zl.entity.DtsFtpFile;
import com.zl.entity.FtpAttr;
import com.zl.entity.FileOperater;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */
public abstract class AbstractFtpClientOpr {
    private static final Log log = LogFactory.getLog(AbstractFtpClientOpr.class);
    protected FtpAttr ftpAttr;
    protected FTPClient ftpClient=new FTPClient();
    FileOperater fileOperater;

    protected List<List<DtsFtpFile>> listMap;
    public List<List<DtsFtpFile>> getListMap() {
        return listMap;
    }

    public void setListMap(List<List<DtsFtpFile>> listMap) {
        this.listMap = listMap;
    }
    public AbstractFtpClientOpr(FtpAttr ftpAttr, FileOperater fileOperater) {
        this.ftpAttr = ftpAttr;
        this.ftpClient = ftpClient;
        this.fileOperater = fileOperater;
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

    public   FTPFile[] getFtpFiles(){
        FTPFile[] ftpFiles = null;
        try {
            ftpFiles = ftpClient.listFiles();
        }catch (IOException e){
            log.error("error!");
        }
        return  ftpFiles;
    }

    public  boolean download(){
        return false;
    };

    public  List<List<DtsFtpFile>> showList(){
        return null;
    };

    public void OperaterType(){
        switch (fileOperater){
            case SHOWLIST :
                this.showList();
                break;
            case UPLOAD:
                this.download();
                break;
        }
    }

    public void procesor(){
        this.connect();
        this.login();
        this.getReplyCode();
        this.changeWorkingDirectory();
        this.OperaterType();
        this.logout();
    }

}
