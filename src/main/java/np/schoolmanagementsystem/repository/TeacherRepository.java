package np.schoolmanagementsystem.repository;

import np.schoolmanagementsystem.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findById(Long id);
    Optional<Teacher> findByuserName(String userName);
}
