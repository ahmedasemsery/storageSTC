package stc.assessments.storagestc.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stc.assessments.storagestc.domain.FileResponse;
import stc.assessments.storagestc.mapper.FileMapper;
import stc.assessments.storagestc.model.File;
import stc.assessments.storagestc.service.FileService;
import stc.assessments.storagestc.validator.Validator;

import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/api/file")
@AllArgsConstructor
@Slf4j
public class FileController {
    private final FileService fileService;
    private final FileMapper fileMapper;
    private final Validator validator;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("parentName") String parentName,
            @RequestHeader("mail") String mail) {
        try {
            validator.validateUserAgainstFolder(mail, parentName);

            File savedFile = fileService.saveFile(file, parentName);

            return ResponseEntity.ok()
                    .body(String.format("File %s uploaded successfully with id %s"
                            , file.getOriginalFilename(), savedFile.getId()));
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s! \n Exception: %s"
                            , file.getOriginalFilename(), e.getMessage()));
        }
    }

    @GetMapping("/meta/{id}")
    public ResponseEntity<FileResponse> getFileMetaData(@PathVariable Long id, @RequestHeader("mail") String mail) {
        try {
            validator.validateUserAgainstFile(mail, id);

            Optional<File> fileById = fileService.getFileById(id);

            return ResponseEntity.ok(fileMapper.mapToFileResponse(fileById.orElseThrow()));
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id, @RequestHeader("mail") String mail) {
        try {
            validator.validateUserAgainstFile(mail, id);

            Optional<File> fileOptional = fileService.getFileById(id);

            if (fileOptional.isEmpty()) {
                return ResponseEntity.notFound()
                        .build();
            }

            File file = fileOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .contentType(MediaType.valueOf(file.getContentType()))
                    .body(file.getBytes());
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not download file: %s! \n Exception: %s"
                            , id.toString(), e.getMessage()).getBytes());
        }
    }
}
