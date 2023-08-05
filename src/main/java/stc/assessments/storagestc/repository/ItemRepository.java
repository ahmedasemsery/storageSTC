package stc.assessments.storagestc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stc.assessments.storagestc.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item getItemByItemName(String itemName);

    Item getItemByParentItemName(String parentItemName);
}
