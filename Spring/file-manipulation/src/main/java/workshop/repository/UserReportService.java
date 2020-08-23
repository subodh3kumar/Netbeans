package workshop.repository;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import workshop.model.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Service
public class UserReportService {

    public String getUserExcelReport() {
        log.info("getUserExcelReport() method called");

        List<User> users = readFromCsv();
        log.info("users size: {}", users.size());

        Path path = null;
        if (CollectionUtils.isNotEmpty(users)) {
            path = writeToExcel(users);
            log.info("file name: {}", path.getFileName().toString());
        }
        return path.getFileName().toString();
    }

    private Path writeToExcel(List<User> users) {
        log.info("writeToExcel() method called");

        String[] columnNames = {"Name", "Email", "Gender"};
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        for (User user : users) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getName());
            row.createCell(1).setCellValue(user.getEmail());
            row.createCell(2).setCellValue(user.getGender());
        }

        for (int i = 0; i < columnNames.length; i++) {
            sheet.autoSizeColumn(i);
        }
        Path path = Paths.get("C:/Development/Files/Output/books.xlsx");
        try (FileOutputStream outputStream = new FileOutputStream(path.toFile())) {
            workbook.write(outputStream);
        } catch (IOException e) {
            log.error("exception occurred while creating xlsx file", e);
        }
        log.info("excel file created: {}", Files.exists(path));
        return path;
    }

    private List<User> readFromCsv() {
        log.info("readFromCsv() method called");
        CsvToBean<User> csvToBean = null;
        try {
            Path path = Paths.get("C:/Development/Files/Input/CSV/users1.csv");
            Reader reader = Files.newBufferedReader(path);
            CsvToBeanBuilder<User> builder = new CsvToBeanBuilder<User>(reader);
            builder.withType(User.class);
            builder.withIgnoreLeadingWhiteSpace(true);
            csvToBean = builder.build();
        } catch (IOException e) {
            log.error("exception occurred while reading csv file", e);
        }
        assert csvToBean != null;
        return csvToBean.parse();
    }
}
