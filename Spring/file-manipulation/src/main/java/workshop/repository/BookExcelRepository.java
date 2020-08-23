package workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workshop.entity.BookEntity;

@Repository
public interface BookExcelRepository extends JpaRepository<BookEntity, Integer> {
}
