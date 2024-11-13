package np.schoolmanagementsystem.repository;

import np.schoolmanagementsystem.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    Optional<Staff> findByEmail(String email);
    Optional<Staff> findByUserName(String username);
}
