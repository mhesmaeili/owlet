package com.owlet.api.controller.base;

import com.owlet.api.dto.base.AttachmentReferenceCreateRequest;
import com.owlet.api.dto.base.AttachmentReferenceDto;
import com.owlet.api.dto.base.AttachmentUrlDto;
import com.owlet.api.service.base.AttachmentReferenceService;
import com.owlet.api.service.base.helper.EntityIdDto;
import com.owlet.api.storage.StorageObject;
import com.owlet.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Tag(name = "AttachmentReferenceController")
@RestController
@RequestMapping("/api/base/attachment")
public class AttachmentReferenceController extends CrudController<
        UUID,
        AttachmentReferenceDto,
        AttachmentReferenceCreateRequest,
        AttachmentReferenceCreateRequest> {

    private final AttachmentReferenceService attachmentReferenceService;

    public AttachmentReferenceController(AttachmentReferenceService service, AttachmentReferenceService attachmentReferenceService) {
        super(service);
        this.attachmentReferenceService = attachmentReferenceService;
    }


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<AttachmentReferenceDto> upload(
            @RequestPart MultipartFile file,
            @RequestParam String entityClass,
            @RequestParam UUID entityId,
            @RequestParam(required = false) UUID categoryId) {

        AttachmentReferenceCreateRequest request = AttachmentReferenceCreateRequest.builder()
                .entityClass(entityClass)
                .entityId(entityId)
                .category(new EntityIdDto(categoryId))
                .build();

        return ApiResponse.success(attachmentReferenceService.upload(
                file,
                request));

    }

    @PostMapping(value = "/uploadGroup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<List<AttachmentReferenceDto>> uploadGroup(
            @RequestPart MultipartFile file,
            @RequestParam String entityClass,
            @RequestParam List<UUID> entityList,
            @RequestParam(required = false) UUID categoryId) {

        List<AttachmentReferenceDto> list = new ArrayList<>();

        for (UUID entityId : entityList) {
            AttachmentReferenceCreateRequest request = AttachmentReferenceCreateRequest.builder()
                    .entityClass(entityClass)
                    .entityId(entityId)
                    .category(new EntityIdDto(categoryId))
                    .build();

            AttachmentReferenceDto upload = attachmentReferenceService.upload(file, request);
            list.add(upload);
        }

        return ApiResponse.success(list);

    }

    @GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> download(
            @PathVariable UUID id) {

        StorageObject object =
                attachmentReferenceService.download(id);

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
    public ApiResponse<List<AttachmentReferenceDto>> list(

            @RequestParam String entityClass,

            @RequestParam UUID entityId) {

        return ApiResponse.success
                (attachmentReferenceService.list(
                        entityClass,
                        entityId));

    }

    @GetMapping("/{id}/url")
    public ApiResponse<AttachmentUrlDto> getUrl(
            @PathVariable UUID id) {

        AttachmentReferenceDto attachmentReferenceDto =
                attachmentReferenceService.get(id);

        return ApiResponse.success
                (attachmentReferenceService.generatePresignedUrl(attachmentReferenceDto));
    }

}