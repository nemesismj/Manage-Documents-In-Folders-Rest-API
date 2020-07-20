package com.rest.jwtdemo.Service;

import com.rest.jwtdemo.Model.Document;
import com.rest.jwtdemo.Model.Folder;
import com.rest.jwtdemo.Repository.AuthorityRepository;
import com.rest.jwtdemo.Repository.DocumentAuthorityRepository;
import com.rest.jwtdemo.Repository.DocumentRepository;
import com.rest.jwtdemo.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class DocumentServiceImpl implements DocumentService{
    Logger log = LoggerFactory.getLogger(DocumentServiceImpl.class);
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final DocumentRepository documentRepository;
    private final FolderService folderService;
    private final DocumentAuthorityRepository documentAuthorityRepository;
    @Autowired
    public DocumentServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, BCryptPasswordEncoder passwordEncoder, DocumentRepository documentRepository, FolderService folderService, DocumentAuthorityRepository documentAuthorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.documentRepository = documentRepository;
        this.folderService = folderService;
        this.documentAuthorityRepository = documentAuthorityRepository;
    }
    public List<Document> getAllDocuments() {
        List<Document> result = documentRepository.findAll();
        log.info("IN getAllDocuments - {} documents found", result.size());
        return result;
    }

    public List<Document> getDocumentsByName(String username){
        List<Document> result = documentRepository.findByUserName(username);
        log.info("IN getDocumentsByName - document: {} found by username: {}", result, username);
        return result;
    }

    public Document findByFileName(String name){
         Document result = documentRepository.findByFileName(name);
         log.info("IN findByFileName - document: {} found by name: {}", result, name);
         return result;
        }
    @Transactional
    @Override
    public void deleteByFileName(String name) {
        documentAuthorityRepository.deleteByFileName(name);
        documentRepository.deleteByFileName(name);
        log.info("IN delete - document with name: {} successfully deleted", name);
    }

}
