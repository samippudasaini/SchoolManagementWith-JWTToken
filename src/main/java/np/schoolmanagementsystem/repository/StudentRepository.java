package np.schoolmanagementsystem.repository;

import np.schoolmanagementsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface StudentRepository extends JpaRepository<Student,Long>
{
    Optional<Student> findByEmail(String email);
    Optional<Student> findByUserName(String userName);
    Optional<Student> findBypassword(String password);
}
