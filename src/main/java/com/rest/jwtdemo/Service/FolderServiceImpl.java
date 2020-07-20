package com.rest.jwtdemo.Service;

import com.rest.jwtdemo.Model.Folder;
import com.rest.jwtdemo.Model.User;
import com.rest.jwtdemo.Repository.AuthorityRepository;
import com.rest.jwtdemo.Repository.DocumentRepository;
import com.rest.jwtdemo.Repository.FolderRepository;
import com.rest.jwtdemo.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FolderServiceImpl implements FolderService{
    Logger log = LoggerFactory.getLogger(FolderServiceImpl.class);
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final DocumentRepository documentRepository;
    private final FolderRepository folderRepository;
    private final UserService userService;
    @Autowired
    public FolderServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, BCryptPasswordEncoder passwordEncoder, DocumentRepository documentRepository, FolderRepository folderRepository, UserService userService) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.documentRepository = documentRepository;
        this.folderRepository = folderRepository;
        this.userService = userService;
    }
    public Folder getFolderById(Integer id){
        Folder result = folderRepository.getOne(id);
        log.info("IN getFolderById - {} Folder found by id - {}", result, id);
        return result;
    }
    public List<Folder> allFolders()
    {
        List<Folder> result = folderRepository.findAll();
        log.info("IN allFolders - {} Folders found", result.size());
        return result;

    }
    public Folder getFolderByName(String name){
        Folder result = folderRepository.getFolderByName(name);
        log.info("IN getFolderByName - {} Folders found by name - {}", result, name);
        return result;


    }
    @Transactional
    @Override
    public void deleteByName(String name){
        documentRepository.deleteByFolderName(name);
        folderRepository.deleteByFoldername(name);
        log.info("IN delete - folder with name: {} successfully deleted", name);
    }


    @Transactional
    @Override
    public void editFolder(String name, Folder folder) {
        Folder editFolder= folderRepository.getFolderByFoldername(name);
        editFolder.setId(folder.getId());
        editFolder.setName(folder.getName());
        editFolder.setUsername(folder.getUsername());
        editFolder.setCreationDate(folder.getCreationDate());
        editFolder.setFolderAccess(folder.getFolderAccess());
        editFolder.setPackagename(folder.getPackagename());
        log.info("IN editFolder - folder with name: {} successfully edited to {}", name,editFolder.getName());
        folderRepository.save(editFolder);
    }
}
