package np.schoolmanagementsystem.repository;

import np.schoolmanagementsystem.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface StaffRepository extends JpaRepository<Staff, Long> {

    Optional<Staff> findByEmail(String email);
    Optional<Staff> findByUserName(String username);
    Optional<Staff> findById(Long id);
}
