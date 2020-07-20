package com.rest.jwtdemo.Repository;


import com.rest.jwtdemo.Model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Integer> {
    @Query(value = "SELECT u FROM Folder u where u.username like %?1%")
    List<Folder> findByFolderName(String username);

    @Query(value = "SELECT u FROM Folder u WHERE u.foldername = :name")
    Folder getFolderByName(@Param("name") String username);

    @Query(value = "SELECT u FROM Folder u WHERE u.folderAccess like %?1% and u.username like %?2%")
    Folder getFolderByA(String folderA, String username);


    @Query(value = "SELECT u FROM Folder u WHERE u.folderAccess like %?1% and u.username like %?2%")
    List<Folder> getFolderByAccess(String acess, String username );
    @Query(value = "SELECT u FROM Folder u WHERE u.packagename like %?1% and u.username like %?2%")
    List<Folder> getFolderByPackage(String packages, String username );
    @Query(value = "SELECT u FROM Folder u WHERE u.packagename is not null and u.username like %?1%")
    List<Folder> getFoldersWithNoPackage(String username);
    @Query(value = "SELECT u FROM Folder u WHERE u.packagename is null and u.username like %?1%")
    List<Folder> getFoldersWithNoPackage1(String username );
    void deleteByFoldername(String name);
    Folder getFolderByFoldername(String name);


}
