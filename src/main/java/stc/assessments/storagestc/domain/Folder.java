package stc.assessments.storagestc.domain;

import lombok.Data;

@Data
public class Folder {
    private Long folderId;
    private String folderName;
    private String parentName;
}
