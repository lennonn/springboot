package com.zl.service;

import com.zl.entity.DtsFtpFile;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */
public interface FTPClientOperations {
    List<List<DtsFtpFile>> showList(String hostname, int port, String username, String password, String pathname) ;

    public boolean downFile(String url, int port, String username, String password, String remotePath, String fileName, String localPath);
}
