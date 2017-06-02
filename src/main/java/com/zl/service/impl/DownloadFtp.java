package com.zl.service.impl;

import com.zl.entity.FtpAttr;
import com.zl.service.AbstractFtpClientOpr;
import com.zl.entity.FileOperater;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/5/7.
 */
public class DownloadFtp extends AbstractFtpClientOpr{
    private static final Log log = LogFactory.getLog(DownloadFtp.class);


    public DownloadFtp(FtpAttr ftpAttr, FTPClient ftpClient, FileOperater fileOperater) {
        super(ftpAttr,  fileOperater);
    }

    public  boolean download(){
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
            this.logout();
        } catch (IOException e) {
            log.error("failed!");
        }
        return isDownload;
    }

}

