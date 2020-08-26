package workshop.service;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

import lombok.extern.slf4j.Slf4j;
import workshop.entity.UserEntity;
import workshop.model.User;
import workshop.repository.FileUploadRepository;

@Slf4j
@Service
public class FileUploadService {

    @Autowired
    private FileUploadRepository repository;

    @Value("${local.output.directory}")
    private String localDirectory;

    public List<User> getUsers(MultipartFile file) {
        log.info("getUsers() method call start.");

        Path path = write(file);
        List<User> users = read(path);
        delete(path);

        log.info("user size: {}", users.size());
        return users;
    }

    private Path write(MultipartFile multipartFile) {
        log.info("creating a temp file");
        Path path = null;
        BufferedOutputStream outputStream = null;
        try {
            byte[] bytes = multipartFile.getBytes();
            String fileName = multipartFile.getOriginalFilename();
            log.info("file name: {}", fileName);
            path = Paths.get(localDirectory + fileName);
            outputStream = new BufferedOutputStream(Files.newOutputStream(path));
            outputStream.write(bytes);
            outputStream.close();
        } catch (IOException e) {
            log.error("exception occurred while file creation", e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(FileUploadService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        log.info("file created sucessfully: {}", Files.exists(path));
        return path;
    }

    private List<User> read(Path path) {
        log.info("reading the created file.");
        CsvToBean<User> csvToBean = null;
        try {
            Reader reader = Files.newBufferedReader(path);
            csvToBean = new CsvToBeanBuilder(reader)
                    .withType(User.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
        } catch (IOException e) {
            log.error("exception occurred while reading the csv file {}", e);
            throw new RuntimeException("exception occurred while reading the csv file");
        }
        return csvToBean.parse();
    }

    private void delete(Path path) {
        log.info("deleting the created file.");
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            log.error("exception occurred while deleting file", e);
        }
    }

    @Async
    public int save(List<User> users) {
        log.info("saving user entities");
        List<UserEntity> entities = users.stream()
                .map(this::convert)
                .collect(Collectors.toList());
        entities = repository.saveAll(entities);
        return entities.size();
    }

    private UserEntity convert(User user) {
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setGender(user.getGender());
        return entity;
    }
}
