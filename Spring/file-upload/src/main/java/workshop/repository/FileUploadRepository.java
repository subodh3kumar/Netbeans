package workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workshop.entity.UserEntity;

@Repository
public interface FileUploadRepository extends JpaRepository<UserEntity, Integer> {
}
