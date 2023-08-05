package stc.assessments.storagestc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stc.assessments.storagestc.mapper.FileMapper;
import stc.assessments.storagestc.model.File;
import stc.assessments.storagestc.model.Item;
import stc.assessments.storagestc.model.ItemType;
import stc.assessments.storagestc.repository.FileRepository;
import stc.assessments.storagestc.repository.PermissionGroupRepository;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final ItemService itemService;
    private final PermissionGroupRepository permissionGroupRepository;
    private final FileMapper fileMapper;

    public File saveFile(MultipartFile multipartFile, String parentName) throws IOException {
        Item item = new Item();

        item.setItemName(multipartFile.getOriginalFilename());
        item.setItemType(ItemType.File);
        item.setPermissionGroup(permissionGroupRepository.getPermissionGroupByGroupName("user"));
        item.setParent(itemService.getItemByParentItemName(parentName));

        File file = fileMapper.mapFileFromMultipartFileAndItem(multipartFile, item);
        item.setFile(file);

        return fileRepository.save(file);
    }

    public Optional<File> getFileById(Long id) {
        return fileRepository.findById(id);
    }
}
