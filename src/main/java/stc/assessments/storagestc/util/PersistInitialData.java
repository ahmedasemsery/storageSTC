package stc.assessments.storagestc.util;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import stc.assessments.storagestc.model.Permission;
import stc.assessments.storagestc.model.PermissionGroup;
import stc.assessments.storagestc.model.PermissionLevel;
import stc.assessments.storagestc.repository.PermissionGroupRepository;
import stc.assessments.storagestc.repository.PermissionRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class PersistInitialData {

    private final PermissionRepository permissionRepository;
    private final PermissionGroupRepository permissionGroupRepository;

    @PostConstruct
    public void init() {
        this.permissionGroupRepository.saveAll(List.of(
                new PermissionGroup("admin"),
                new PermissionGroup("dev"),
                new PermissionGroup("user")
        ));

        this.permissionRepository.saveAll(List.of(
                new Permission("ahmed@gmail.com", PermissionLevel.EDIT, permissionGroupRepository.getPermissionGroupByGroupName("admin")),
                new Permission("shaimaa@gmail.com", PermissionLevel.VIEW, permissionGroupRepository.getPermissionGroupByGroupName("admin")),
                new Permission("ali@gmail.com", PermissionLevel.EDIT, permissionGroupRepository.getPermissionGroupByGroupName("dev")),
                new Permission("mohsen@gmail.com", PermissionLevel.VIEW, permissionGroupRepository.getPermissionGroupByGroupName("dev")),
                new Permission("asem@gmail.com", PermissionLevel.VIEW, permissionGroupRepository.getPermissionGroupByGroupName("user"))
        ));
    }
}
