package com.rest.jwtdemo.Service;

import com.rest.jwtdemo.Model.Document;
import com.rest.jwtdemo.Model.Folder;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface DocumentService {
    List<Document> getAllDocuments();
    List<Document> getDocumentsByName(String username);
    Document findByFileName(String name);
    void deleteByFileName(String name);
}
