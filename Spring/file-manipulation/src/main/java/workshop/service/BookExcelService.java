package workshop.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshop.entity.BookEntity;
import workshop.model.Book;
import workshop.repository.BookExcelRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookExcelService {

    @Autowired
    private BookExcelRepository excelRepository;

    public List<Book> getBooksDetailsFromExcelColumnIndex() {
        log.info("getBooksDetailsFromExcel() method called");

        List<Book> books = new ArrayList<>();

        try {
            Path path = Paths.get("C:/Development/Files/Input/Excel/Books.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(Files.newInputStream(path));
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                int rowNum = row.getRowNum();
                if (rowNum == 0) {
                    continue;
                }

                Iterator<Cell> cellIterator = row.cellIterator();
                Book book = new Book();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getColumnIndex()) {
                        case 0:
                            book.setId((int) Math.round((Double) getCellValue(cell)));
                            break;
                        case 1:
                            book.setName((String) getCellValue(cell));
                            break;
                        case 2:
                            book.setAuthor((String) getCellValue(cell));
                            break;
                        case 3:
                            book.setPublicationYear((int) Math.round((Double) getCellValue(cell)));
                            break;
                        case 4:
                            book.setPrice(Math.round((Double) getCellValue(cell)));
                            break;
                        case 5:
                            book.setPublisher((String) getCellValue(cell));
                            break;
                        case 6:
                            book.setLanguage((String) getCellValue(cell));
                            break;
                        case 7:
                            book.setIsbn((String) getCellValue(cell));
                            break;
                    }
                }
                books.add(book);
            }

        } catch (IOException e) {
            log.error("exception occurred while reading the excel file", e);
        }
        log.info("total books available: " + books.size());
        return books;
    }

    public List<BookEntity> storeBookDetails(List<Book> books) {
        log.info("storeBookDetails() method called");
        log.info("books size: {}", books.size());
        List<BookEntity> entities = getEntities(books);
        return excelRepository.saveAll(entities);
    }

    private List<BookEntity> getEntities(List<Book> books) {
        return books.stream().map(this::convert).collect(Collectors.toList());
    }

    public BookEntity convert(Book book) {
        BookEntity entity = new BookEntity();
        entity.setId(book.getId());
        entity.setName(book.getName());
        entity.setAuthor(book.getAuthor());
        entity.setPublicationYear(book.getPublicationYear());
        entity.setPrice(book.getPrice());
        entity.setPublisher(book.getPublisher());
        entity.setLanguage(book.getLanguage());
        entity.setIsbn(book.getIsbn());
        return entity;
    }

    public List<Book> getBooksDetailsFromExcelColumnName() {
        log.info("getBooksDetailsFromExcelColumnName() method called");

        List<Book> books = new ArrayList<>();
        try {
            Path path = Paths.get("C:/Development/Files/Input/Excel/Books.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(Files.newInputStream(path));
            XSSFSheet sheet = workbook.getSheetAt(0);

            Map<String, Integer> map = getExcelColumnNames(sheet);

            int totalRows = sheet.getPhysicalNumberOfRows();
            log.info("physical number of rows: {}", totalRows);

            int lastRowNum = sheet.getLastRowNum();
            log.info("last row num: " + lastRowNum);

            final int idIndex = map.get("BOOK_ID") == null ? -1 : map.get("BOOK_ID");
            final int bookNameIndex = map.get("BOOK_NAME") == null ? -1 : map.get("BOOK_NAME");
            final int authorNameIndex = map.get("AUTHOR_NAME") == null ? -1 : map.get("AUTHOR_NAME");
            final int pubYearIndex = map.get("PUB_YEAR") == null ? -1 : map.get("PUB_YEAR");
            final int priceIndex = map.get("PRICE") == null ? -1 : map.get("PRICE");
            final int publisherIndex = map.get("PUBLISHER") == null ? -1 : map.get("PUBLISHER");
            final int languageIndex = map.get("LANGUAGE") == null ? -1 : map.get("LANGUAGE");
            final int isbnIndex = map.get("ISBN") == null ? -1 : map.get("ISBN");

            log.info("isbn index: " + isbnIndex);

            for (int index = 1; index < totalRows; index++) {
                Book book = new Book();
                XSSFRow row = sheet.getRow(index);
                boolean isRowEmpty = isRowEmpty(row);
                if (isRowEmpty) {
                    continue;
                }
                if (idIndex >= 0) {
                    book.setId((int) Math.round((Double) getCellValue(row.getCell(idIndex))));
                }
                if (bookNameIndex >= 0) {
                    book.setName((String) getCellValue(row.getCell(bookNameIndex)));
                }
                if (authorNameIndex >= 0) {
                    book.setAuthor((String) getCellValue(row.getCell(authorNameIndex)));
                }
                if (pubYearIndex >= 0) {
                    book.setPublicationYear((int) Math.round((Double) getCellValue(row.getCell(pubYearIndex))));
                }
                if (priceIndex >= 0) {
                    book.setPrice(Math.round((Double) getCellValue(row.getCell(priceIndex))));
                }
                if (publisherIndex >= 0) {
                    book.setPublisher((String) getCellValue(row.getCell(publisherIndex)));
                }
                if (languageIndex >= 0) {
                    book.setLanguage((String) getCellValue(row.getCell(languageIndex)));
                }
                if (isbnIndex >= 0) {
                    book.setIsbn((String) getCellValue(row.getCell(isbnIndex)));
                }
                log.info(book.toString());
                books.add(book);
            }
        } catch (IOException e) {
            log.error("exception occurred while reading excel file", e);
        }
        return books;
    }

    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
        }
        return null;
    }

    private boolean isRowEmpty(XSSFRow row) {
        if (row == null) {
            log.info("row is null");
            return true;
        }
        if (row.getLastCellNum() <= 0) {
            log.info("row.getLastCellNum() <= 0");
            return true;
        }
        for (int index = row.getFirstCellNum(); index < row.getLastCellNum(); index++) {
            Cell cell = row.getCell(index);
            if (cell != null && cell.getCellType() != CellType.BLANK && StringUtils.isNotBlank(cell.toString())) {
                log.info("cell is null or empty");
                return false;
            }
        }
        return true;
    }

    private Map<String, Integer> getExcelColumnNames(XSSFSheet sheet) {
        Map<String, Integer> result = new HashMap<>();
        XSSFRow header = sheet.getRow(0);
        short firstCellNum = header.getFirstCellNum();
        short lastCellNum = header.getLastCellNum();
        for (int index = firstCellNum; index < lastCellNum; index++) {
            XSSFCell cell = header.getCell(index);
            result.put(cell.getStringCellValue(), cell.getColumnIndex());
        }
        result.forEach((k, v) -> log.info("column name: " + k + ", column index: " + v));
        return result;
    }
}
