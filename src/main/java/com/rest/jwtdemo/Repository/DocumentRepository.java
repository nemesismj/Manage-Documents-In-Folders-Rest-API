package com.rest.jwtdemo.Repository;


import com.rest.jwtdemo.Model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, String> {

    @Query("select distinct d From Document d " +
            "join d.documentAuthorities auth " +
            "where auth.fileAuthority in :authorities")
    List<Document> findAllByAuthorities(List<String> authorities);
    @Query(value = "SELECT u FROM Document u where u.userUploader like %?1%")
    List<Document> findByUserName(String username);
    @Query(value = "SELECT u FROM Document u where u.userUploader like %?1% and u.folderName like %?2%")
    List<Document> getDocumentsByFolder(String username, String foldername);
    @Query(value = "SELECT u FROM Document u where u.userUploader like %?1% and u.folderName like %?2%")
    List<Document> getDocumentByPackage(String username, String packagename);

    Document findByFileName(String name);
    void deleteByFileName(String username);
    void deleteByFolderName(String name);
}
