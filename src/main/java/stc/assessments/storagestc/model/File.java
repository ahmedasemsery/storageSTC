package stc.assessments.storagestc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FILE")
@Setter
@Getter
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long size;

    private String contentType;

    @Lob
    private byte[] bytes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
}
