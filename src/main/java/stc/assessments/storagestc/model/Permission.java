package stc.assessments.storagestc.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PERMISSION")
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class Permission {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NonNull
    private String userEmail;

    @NonNull
    @Enumerated(EnumType.STRING)
    private PermissionLevel permissionLevel;

    @ManyToOne
    @JoinColumn(name = "permission_group_id", nullable = false)
    @NonNull
    private PermissionGroup permissionGroup;
}
