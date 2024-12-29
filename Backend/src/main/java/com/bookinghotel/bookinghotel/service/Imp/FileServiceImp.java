package com.bookinghotel.bookinghotel.service.Imp;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileServiceImp {
    void saveFile(MultipartFile file);
    Resource load(String fileName);
    void deleteFile(String fileName);
}
