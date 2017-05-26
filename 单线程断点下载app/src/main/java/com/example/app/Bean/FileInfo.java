package com.example.app.Bean;

import java.io.Serializable;

/**
 * 文件实体类
 * Created by vonym on 17-3-14.
 */

public class FileInfo implements Serializable{
    private int id;
    private String fileName;
    private String url;
    private int length;
    private int finish;

    public FileInfo() {
    }

    public FileInfo(int id, String fileName, String url, int length, int finish) {
        this.id = id;
        this.fileName = fileName;
        this.url = url;
        this.length = length;
        this.finish = finish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", length=" + length +
                ", finish=" + finish +
                '}';
    }
}
