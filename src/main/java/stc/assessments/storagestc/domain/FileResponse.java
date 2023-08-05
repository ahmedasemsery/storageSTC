package stc.assessments.storagestc.domain;

import lombok.Data;

@Data
public class FileResponse {
    private Long id;
    private String name;
    private Long size;
    private String contentType;
}
