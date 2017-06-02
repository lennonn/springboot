package com.zl.dao;

import com.zl.entity.FtpAttr;
import com.zl.entity.FileOperater;
import org.apache.commons.net.ftp.FTPClient;

/**
 * Created by Administrator on 2017/6/2.
 */
public interface FtpAttrDao {
    public FtpAttr getFtpAttr(String remotePath,String fileName);
    public FTPClient getFTPClient();
    public FileOperater getOperators(FileOperater fo);
}
