package np.schoolmanagementsystem.repository;

import np.schoolmanagementsystem.Enum.StudentStatus;
import np.schoolmanagementsystem.entity.Student;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Repository
@Service
public interface StudentRepository extends JpaRepository<Student,Long>
{
    Student findByEmail(String email);
    Student findByUserName(String userName);
    List<Student> findByStatus(StudentStatus status);

}


