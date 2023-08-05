package stc.assessments.storagestc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ITEM")
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @ManyToOne
    private Item parent;

    @OneToMany
    @JoinColumn(name = "parent_id")
    private List<Item> children = new ArrayList<>();

    @OneToOne(mappedBy = "item")
    private File file;

    @ManyToOne
    private PermissionGroup permissionGroup;
}
