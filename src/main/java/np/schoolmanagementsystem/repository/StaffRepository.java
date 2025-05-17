package np.schoolmanagementsystem.repository;

import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface StaffRepository extends JpaRepository<Staff, Long> {

    Optional<Staff> findByEmail(String email);
   Staff findByUserName(String userName);
    Optional<Staff> findById(Long staffId);
    Staff findByRole(Role role);
    @Query(value = "SELECT * FROM Staff_table", nativeQuery = true)
    List<Staff> fetchAllStaff();

}
