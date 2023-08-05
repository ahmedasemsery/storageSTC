package stc.assessments.storagestc.validator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import stc.assessments.storagestc.model.File;
import stc.assessments.storagestc.model.Item;
import stc.assessments.storagestc.model.Permission;
import stc.assessments.storagestc.model.PermissionLevel;
import stc.assessments.storagestc.repository.FileRepository;
import stc.assessments.storagestc.repository.ItemRepository;
import stc.assessments.storagestc.repository.PermissionRepository;

import java.util.Optional;
import java.util.Set;

@Component
@AllArgsConstructor
@Slf4j
public class Validator {

    private final PermissionRepository permissionRepository;
    private final ItemRepository itemRepository;
    private final FileRepository fileRepository;

    public void validateUserAgainstSpace(String mail, String spaceName) throws Exception {
        Permission permission = getPermission(mail);
        Item item = itemRepository.getItemByItemName(spaceName);
        Set<Permission> permissions = item.getPermissionGroup().getPermissions();
        if (permissions.contains(permission)) {
            if (permission.getPermissionLevel().equals(PermissionLevel.VIEW)) {
                throw new Exception(mail + " don't have edit access on space " + spaceName);
            }
        } else {
            throw new Exception(mail + " don't have any access to space " + spaceName);
        }
    }

    public void validateUserAgainstFolder(String mail, String folderName) throws Exception {
        Permission permission = getPermission(mail);
        Item item = itemRepository.getItemByItemName(folderName);
        Set<Permission> permissions = item.getPermissionGroup().getPermissions();
        if (permissions.contains(permission)) {
            if (permission.getPermissionLevel().equals(PermissionLevel.VIEW)) {
                throw new Exception(mail + " don't have edit access on folder " + folderName);
            }
        } else {
            throw new Exception(mail + " don't have any access to folder " + folderName);
        }
    }

    public void validateUserAgainstFile(String mail, Long fileId) throws Exception {
        Permission permission = getPermission(mail);
        Optional<File> fileOp = fileRepository.findById(fileId);
        Item item = fileOp.orElseThrow().getItem();
        Set<Permission> permissions = item.getPermissionGroup().getPermissions();
        if (permissions.contains(permission) && permission.getPermissionGroup().getGroupName().equals("user")) {
            if (!permission.getPermissionLevel().equals(PermissionLevel.VIEW)) {
                throw new Exception(mail + " don't have view access on file id " + fileId);
            }
        } else {
            throw new Exception(mail + " don't have any access on file id " + fileId);
        }
    }

    private Permission getPermission(String mail) {
        return permissionRepository.getPermissionByUserEmail(mail);
    }
}
