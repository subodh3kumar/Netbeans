package workshop.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import workshop.entity.BookEntity;
import workshop.model.Book;
import workshop.service.BookExcelService;

import java.util.List;

@Slf4j
@RestController
public class BookExcelController {

    @Autowired
    private BookExcelService excelService;

    @GetMapping("/excel/books")
    public ResponseEntity<String> books() {
        log.info("books() method called");

        //List<Book> books = excelService.getBooksDetailsFromExcelColumnIndex();
        List<Book> books = excelService.getBooksDetailsFromExcelColumnName();

        List<BookEntity> entities = null;
        if (CollectionUtils.isNotEmpty(books)) {
            entities = excelService.storeBookDetails(books);
        }
        if (CollectionUtils.isNotEmpty(entities)) {
            return ResponseEntity.ok("Books information stored successfully");
        }
        return ResponseEntity.ok("No book information available");
    }
}
