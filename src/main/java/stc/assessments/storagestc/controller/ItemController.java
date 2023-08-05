package stc.assessments.storagestc.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stc.assessments.storagestc.domain.Folder;
import stc.assessments.storagestc.domain.Space;
import stc.assessments.storagestc.service.ItemService;
import stc.assessments.storagestc.validator.Validator;

@RestController
@RequestMapping("/api/create")
@AllArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final Validator validator;

    @PostMapping("/space")
    public ResponseEntity<String> createSpace(@RequestBody Space space) {
        try {
            Space createdSpace = itemService.createSpace(space);

            return ResponseEntity.ok()
                    .body(String.format("Space %s created with id %s",
                            createdSpace.getSpaceName(), createdSpace.getSpaceId()));
        } catch (Exception e) {
            log.error(e.fillInStackTrace().getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not create Space: %s!", space.getSpaceName()));
        }
    }

    @PostMapping("/folder")
    public ResponseEntity<String> createFolder(@RequestHeader(value = "mail") String mail, @RequestBody Folder folder) {
        try {
            validator.validateUserAgainstSpace(mail, folder.getParentName());

            Folder createdFolder = itemService.createFolder(folder);

            return ResponseEntity.ok()
                    .body(String.format("Folder %s created with id %s",
                            createdFolder.getFolderName(), createdFolder.getFolderId()));
        } catch (Exception e) {
            log.error(e.fillInStackTrace().getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not create Folder: %s!", folder.getFolderName()));
        }
    }
}
