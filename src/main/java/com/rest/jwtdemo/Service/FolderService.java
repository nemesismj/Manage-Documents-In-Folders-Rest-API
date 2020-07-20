package com.rest.jwtdemo.Service;

import com.rest.jwtdemo.Model.Folder;
import com.rest.jwtdemo.Model.User;

import java.util.List;

public interface FolderService {
    Folder getFolderById(Integer id);
    List<Folder> allFolders();
    Folder getFolderByName(String name);
    void deleteByName(String name);
    void editFolder(String name, Folder folder);
}