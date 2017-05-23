package com.zl.entity;

/**
 * Created by Administrator on 2017/5/23.
 */
public class FtpAttr {
    private String url;// FTP服务器hostname
    private int port;// FTP服务器端口
    private String username;// FTP登陆账号
    private String password;// FTP登陆密码
    private String remotePath;// FTP服务器上的相对路径
    private String fileName;// 要下载的文件名
    private String localPath;// 下载后保存到本地的路劲

    public FtpAttr(String url, int port, String username, String password, String remotePath, String fileName, String localPath) {
        this.url = url;
        this.port = port;
        this.username = username;
        this.password = password;
        this.remotePath = remotePath;
        this.fileName = fileName;
        this.localPath = localPath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
