package com.rest.jwtdemo.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String foldername;

    String username;

    String folderAccess;

    String packagename;

    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    public Folder() {
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id=" + id +
                ", name='" + foldername + '\'' +
                ", username='" + username + '\'' +
                ", folderAccess='" + folderAccess + '\'' +
                ", packagename='" + packagename + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    public String getPackagename() {
        return packagename;
    }

    public String setPackagename(String packagename) {
        this.packagename = packagename;
        return packagename;
    }

    public Folder(String foldername, String username, Date creationDate) {
        this.foldername = foldername;
        this.username = username;
        this.creationDate = creationDate;
    }

    public String getName() {
        return foldername;
    }

    public void setName(String foldername) {
        this.foldername = foldername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFolderAccess() {
        return folderAccess;
    }

    public void setFolderAccess(String folderAccess) {
        this.folderAccess = folderAccess;
    }

}
