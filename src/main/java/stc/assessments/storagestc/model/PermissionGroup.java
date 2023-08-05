package stc.assessments.storagestc.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@Table(name = "PERMISSION_GROUP")
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class PermissionGroup {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NonNull
    private String groupName;

    @OneToMany(mappedBy = "permissionGroup")
    private Set<Permission> permissions;
}
