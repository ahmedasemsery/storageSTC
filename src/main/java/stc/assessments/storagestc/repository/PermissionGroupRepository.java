package stc.assessments.storagestc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stc.assessments.storagestc.model.PermissionGroup;

@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {
    PermissionGroup getPermissionGroupByGroupName(String groupName);
}
