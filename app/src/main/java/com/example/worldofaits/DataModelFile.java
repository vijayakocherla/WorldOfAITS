package com.example.worldofaits;

public class DataModelFile {
    String  name,url,file_name,time;
    public DataModelFile() {
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DataModelFile(String name, String url, String file_name, String time) {
        this.name = name;
        this.url = url;
        this.file_name = file_name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
