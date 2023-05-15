package com.jyz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Allinfos {
    private String studentid;

    private String username;

    private String department;

    private String specialty;

    private String topicdepict;

    public void setTopicdepict(String topicdepict) {
        this.topicdepict = topicdepict;
    }

    public String getTopicdepict() {
        return topicdepict;
    }

    private int blindscore;

    private int defensescore;

    private String teacherid;
    private int status;

    @TableId("topictitle")
    private String topictitle;


    private String fileurl;
    @TableId(value = "paperid", type = IdType.AUTO)
    private Integer paperid;
    private String filename;
    public String getStudentid() {
        return studentid;
    }

    public String getUsername() {
        return username;
    }

    public String getDepartment() {
        return department;
    }

    public String getSpecialty() {
        return specialty;
    }

    public int getBlindscore() {
        return blindscore;
    }

    public int getDefensescore() {
        return defensescore;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public int getStatus() {
        return status;
    }

    public String getTopictitle() {
        return topictitle;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setBlindscore(int blindscore) {
        this.blindscore = blindscore;
    }

    public void setDefensescore(int defensescore) {
        this.defensescore = defensescore;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTopictitle(String topictitle) {
        this.topictitle = topictitle;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public Integer getPaperid() {
        return paperid;
    }

    public String getFilename() {
        return filename;
    }

    public void setPaperid(Integer paperid) {
        this.paperid = paperid;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}


