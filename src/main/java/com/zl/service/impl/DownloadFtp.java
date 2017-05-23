package com.zl.service.impl;

import com.zl.entity.FtpAttr;
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

    public boolean downFile(FtpAttr ftpAttr) {
        FtpClientOpr ftpClientOpr = new FtpClientOpr(ftpAttr);
        ftpClientOpr.connect();
        ftpClientOpr.login();
        ftpClientOpr.getReplyCode();
        ftpClientOpr.changeWorkingDirectory();
        boolean isSuccess=ftpClientOpr.download();
        return isSuccess;
    }
}
