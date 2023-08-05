package stc.assessments.storagestc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;
import stc.assessments.storagestc.domain.FileResponse;
import stc.assessments.storagestc.model.File;
import stc.assessments.storagestc.model.Item;

import java.io.IOException;

@Mapper(componentModel = "spring")
public abstract class FileMapper {

    @Mapping(target = "name", source = "multipartFile.originalFilename")
    @Mapping(target = "item", source = "item")
    public abstract File mapFileFromMultipartFileAndItem(MultipartFile multipartFile, Item item) throws IOException;

    public abstract FileResponse mapToFileResponse(File file);
}
