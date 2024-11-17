package np.schoolmanagementsystem.repository;

import np.schoolmanagementsystem.dto.SubjectDto;
import np.schoolmanagementsystem.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findById(Long id);
}
