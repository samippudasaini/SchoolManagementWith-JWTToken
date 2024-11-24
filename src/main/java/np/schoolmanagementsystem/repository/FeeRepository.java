package np.schoolmanagementsystem.repository;

import np.schoolmanagementsystem.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeRepository extends JpaRepository<Fee, Long> {

    Fee findById(long feeId);

}
