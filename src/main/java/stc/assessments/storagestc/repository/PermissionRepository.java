package stc.assessments.storagestc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stc.assessments.storagestc.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission getPermissionByUserEmail(String userEmail);
}
