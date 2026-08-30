package com.owlet.api.dto.profile.parent;


import com.owlet.api.dto.base.AttachmentUrlDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGalleryGroupDto {
    private String courseName;
    private List<AttachmentUrlDto> images;
}