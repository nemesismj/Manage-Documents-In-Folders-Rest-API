package com.rest.jwtdemo.Controller;

import com.rest.jwtdemo.Model.Document;
import com.rest.jwtdemo.Model.Folder;
import com.rest.jwtdemo.Model.User;
import com.rest.jwtdemo.Service.DocumentService;
import com.rest.jwtdemo.Service.FolderService;
import com.rest.jwtdemo.Service.UserService;
import com.rest.jwtdemo.Service.UserServiceImpl;
import com.rest.jwtdemo.dto.AdminUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/admin/")
public class AdminRestController {
    Logger log = LoggerFactory.getLogger(AdminRestController.class);
    private final UserService userService;
    private final DocumentService documentService;
    private final FolderService folderService;

    @Autowired
    public AdminRestController(UserService userService, DocumentService documentService, FolderService folderService) {
        this.userService = userService;
        this.documentService = documentService;
        this.folderService = folderService;
    }

    @GetMapping(value = "users/{name}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "name") String name) {
        User user = userService.findByUsername(name);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);
        log.debug(String.format("User with specific name request: %s ",name));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> listAllUsers()
    {

        List<User> users;
        try {
            users = userService.getAll();
            log.debug(String.format("List all users request"));
        } catch (PersistenceException e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (users.isEmpty())
        {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);

    }
    @RequestMapping(value = "/documents", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Document>> listAllDocuments()
    {

        List<Document> documents;
        try {
            documents = documentService.getAllDocuments();
            log.debug(String.format("List all documents request"));
        } catch (PersistenceException e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (documents.isEmpty())
        {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }
    @RequestMapping(value = "/folders", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Folder>> listAllFolders()
    {

        List<Folder> folders;
        try {
            folders = folderService.allFolders();
            log.debug(String.format("List all folders request"));
        } catch (PersistenceException e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (folders.isEmpty())
        {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(folders, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/delete/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("name") String name)
    {
        try {
            User users = userService.findByUsername(name);

            if (users == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            userService.deleteByUsername(name);
            log.debug(String.format("User with name: %s deleted by admin",name));
        } catch (PersistenceException e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(value = "/documents/delete/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Document> deleteDocument(@PathVariable("name") String name)
    {
        try {
            Document document = documentService.findByFileName(name);

            if (document == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            documentService.deleteByFileName(name);
            log.debug(String.format("Document with name: %s deleted by admin",name));
        } catch (PersistenceException e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(value = "/folders/delete/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Folder> deleteFolder(@PathVariable("name") String name)
    {
        try {
            Folder folder = folderService.getFolderByName(name);
            if (folder == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            folderService.deleteByName(name);
            log.debug(String.format("Folder with name: %s deleted by admin",name));
        } catch (PersistenceException e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(value = "/users/new", method = RequestMethod.POST)
    public ResponseEntity newUser(@RequestBody User users,
                                  BindingResult bindingResult,
                                  Model model)
    {

        if (bindingResult.hasErrors()) {
            log.error(String.valueOf(bindingResult.getFieldError()));
            model.addAttribute("method", "new");
        }
        userService.saveUser(users);
        log.debug(String.format("User with name: %s successfully created.", users.getUsername()));
        Map<Object, Object> response = new HashMap<>();
        response.put("users",users);

        return ResponseEntity.ok(response);
    }
    @RequestMapping(value = "/users/edit/{name}", method = RequestMethod.PUT)
    public ResponseEntity<User> editUser(@PathVariable("name") String name,
                                                 @RequestBody User user)
    {
        try {
            userService.edit(name,user);
            log.debug(String.format("User with name: %s successfully edited",name));
        } catch (PersistenceException e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/folders/edit/{name}", method = RequestMethod.PUT)
    public ResponseEntity<Folder> editFolder(@PathVariable("name") String name,
                                         @RequestBody Folder folder)
    {
        try {
            folderService.editFolder(name,folder);
            log.debug(String.format("folder with name: %s successfully edited",name));
        } catch (PersistenceException e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(folder, HttpStatus.OK);
    }

}