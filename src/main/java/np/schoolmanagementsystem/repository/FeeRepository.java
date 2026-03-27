package np.schoolmanagementsystem.repository;

import np.schoolmanagementsystem.entity.Fee;
import np.schoolmanagementsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
@Service
public interface FeeRepository extends JpaRepository<Fee, Long> {

    Fee findById(long feeId);
    List<Fee> findByStudent_StudentId(Long studentId);
    List<Fee> findByStudent_Email(String email);

}
