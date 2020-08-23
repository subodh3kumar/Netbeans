package workshop.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import workshop.repository.UserReportService;

@Slf4j
@RestController
public class UserReportController {

    @Autowired
    private UserReportService userReportService;

    @GetMapping("/excel/report")
    public ResponseEntity<String> getUsers() {
        String fileName = userReportService.getUserExcelReport();
        if (StringUtils.isEmpty(fileName)) {
            return ResponseEntity.ok("file not created");
        }
        return ResponseEntity.ok("file " + fileName + " created");
    }
}
