package com.bookinghotel.bookinghotel.service;

import com.bookinghotel.bookinghotel.service.Imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@Service
public class FileService implements FileServiceImp {
    @Value("${upload.file.path}")
    private String path;
    @Override
    public void saveFile(MultipartFile file) {
        try {
            Path root = Paths.get(path);
            // Check directory existed if don't exist, create new Directory.
            if(!Files.exists(root)){
                Files.createDirectory(root);
            }
            // Copy File into this Directory.
//          TODO: root.resolve: Tùy vào hdh nào để thêm vào.
//          root.resolve(file.getOriginalFilename()): Lấy path.
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            throw new RuntimeException("Error save file: "+ e.getClass());
        }
    }

    @Override
    public Resource load(String fileName) {
        try{
            // Lấy đường dẫn Folder lưu trữ file
            Path root = Paths.get(path);
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists()){
                return resource;
            }else throw new RuntimeException("Don't find file");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Không tìm thấy file " + e.getMessage());
        }
    }

    @Override
    public void deleteFile(String fileName) {
        try {
            // Lấy đường đẫn folder lưu trữ file
            Path root = Paths.get(path);
            Path file = root.resolve(fileName);
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
