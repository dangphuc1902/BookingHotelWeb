package com.bookinghotel.bookinghotel.controller;

import com.bookinghotel.bookinghotel.payload.response.BaseResponse;
import com.bookinghotel.bookinghotel.service.Imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileServiceImp fileServiceImp;
    @GetMapping("/{name:.+}")
    private ResponseEntity<?> getFile(@PathVariable String name){
//      HttpHeaders.CONTENT_DISPOSITION là header HTTP giúp trình duyệt hiểu rằng nội dung trả về là file đính kèm.
//      attachment thông báo cho trình duyệt rằng file cần được tải xuống (download) thay vì hiển thị trực tiếp
//        filename="name" chỉ định tên file sẽ xuất hiện khi tải xuống. Ở đây, tên file sẽ là tên của file đã tải lên (name).
        Resource resource = fileServiceImp.load(name);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + name + "\"").body(resource);
    }
}
