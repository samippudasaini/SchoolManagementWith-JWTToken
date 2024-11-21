package np.schoolmanagementsystem.repository;

import np.schoolmanagementsystem.entity.Student;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface StudentRepository extends JpaRepository<Student,Long>
{
    Student findByEmail(String email);
//    Optional<Student> findByUserName(String userName);
    Optional<Student> findBypassword(String password);

//    User findByUserName(String username);
    Student findByUserName(String userName);



}
