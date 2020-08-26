package workshop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import workshop.model.User;
import workshop.service.FileUploadService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private FileUploadService service;

    @PostMapping(value = "/users", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestParam("files") MultipartFile[] files) {
        log.info("save() method call start.");

        List<User> users = new ArrayList<>();
        for (MultipartFile file : files) {
            log.info("file name: {}", file.getOriginalFilename());
            List<User> list = service.getUsers(file);
            users.addAll(list);
        }
        log.info("user list size: {}", users.size());
        int size = service.save(users);
        log.info("users size: {}", size);
        if (size == 0) {
            return new ResponseEntity("users not saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("users saved", HttpStatus.CREATED);
    }
}
