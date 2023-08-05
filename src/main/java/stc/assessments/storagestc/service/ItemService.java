package stc.assessments.storagestc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stc.assessments.storagestc.domain.Folder;
import stc.assessments.storagestc.domain.Space;
import stc.assessments.storagestc.model.Item;
import stc.assessments.storagestc.model.ItemType;
import stc.assessments.storagestc.repository.ItemRepository;
import stc.assessments.storagestc.repository.PermissionGroupRepository;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final PermissionGroupRepository permissionGroupRepository;

    public Space createSpace(Space space) {
        Item item = new Item();

        item.setItemName(space.getSpaceName());
        item.setItemType(ItemType.Space);
        item.setPermissionGroup(permissionGroupRepository.getPermissionGroupByGroupName("admin"));

        Item savedItem = itemRepository.save(item);

        space.setSpaceId(savedItem.getId());

        return space;
    }

    public Folder createFolder(Folder folder) {
        Item item = new Item();

        item.setItemName(folder.getFolderName());
        item.setItemType(ItemType.Folder);
        item.setPermissionGroup(permissionGroupRepository.getPermissionGroupByGroupName("dev"));
        item.setParent(itemRepository.getItemByParentItemName(folder.getParentName()));

        Item savedItem = itemRepository.save(item);

        folder.setFolderId(savedItem.getId());

        return folder;
    }

    public Item getItemByParentItemName(String parentName) {
        return this.itemRepository.getItemByParentItemName(parentName);
    }
}
