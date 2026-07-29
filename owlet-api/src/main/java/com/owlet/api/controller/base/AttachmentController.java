package com.owlet.api.controller.base;

import com.owlet.api.dto.base.AttachmentCreateRequest;
import com.owlet.api.dto.base.AttachmentDto;
import com.owlet.api.service.base.AttachmentService;
import com.owlet.api.service.base.helper.EntityIdDto;
import com.owlet.api.storage.StorageObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Tag(name = "AttachmentController")
@RestController
@RequestMapping("/api/base/attachment")
public class AttachmentController extends CrudController<
        UUID,
        AttachmentDto,
        AttachmentCreateRequest,
        AttachmentCreateRequest> {

    private final AttachmentService attachmentService;

    public AttachmentController(AttachmentService service, AttachmentService attachmentService) {
        super(service);
        this.attachmentService = attachmentService;
    }


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AttachmentDto upload(
            @RequestPart MultipartFile file,
            @RequestParam String entityClass,
            @RequestParam UUID entityId,
            @RequestParam(required = false) UUID categoryId) {

        AttachmentCreateRequest request = AttachmentCreateRequest.builder()
                .entityClass(entityClass)
                .entityId(entityId)
                .category(new EntityIdDto(categoryId))
                .build();

        return attachmentService.upload(
                file,
                request);

    }

    @GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> download(
            @PathVariable UUID id) {

        StorageObject object =
                attachmentService.download(id);

        return ResponseEntity.ok()

                .contentType(
                        MediaType.parseMediaType(
                                object.contentType()))

                .contentLength(
                        object.contentLength())

                .eTag(object.etag())

                .lastModified(
                        object.lastModified().toEpochMilli())

                .header(
                        HttpHeaders.CONTENT_DISPOSITION,

                        ContentDisposition
                                .attachment()
                                .filename(
                                        object.filename(),
                                        StandardCharsets.UTF_8)
                                .build()
                                .toString())

                .body(
                        new InputStreamResource(
                                object.inputStream()));

    }

    @GetMapping("/listByEntity")
    public List<AttachmentDto> list(

            @RequestParam String entityClass,

            @RequestParam UUID entityId) {

        return attachmentService.list(
                entityClass,
                entityId);

    }

}