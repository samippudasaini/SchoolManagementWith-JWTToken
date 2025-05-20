package np.schoolmanagementsystem.repository;

import np.schoolmanagementsystem.entity.PendingTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PendingTeacherRepository extends JpaRepository<PendingTeacher, Long> {
    Optional<PendingTeacher> findByUserName(String userName);
    Optional<PendingTeacher> findByEmail(String email);
}
