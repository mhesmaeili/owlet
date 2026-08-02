package com.owlet.api.controller.std;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.std.StudentAchievementCreateRequest;
import com.owlet.api.dto.std.StudentAchievementDto;
import com.owlet.api.service.std.StudentAchievementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "StudentAchievementController")
@RestController
@RequestMapping("/api/std/studentAchievement")
public class StudentAchievementController extends CrudController<
        UUID,
        StudentAchievementDto,
        StudentAchievementCreateRequest,
        StudentAchievementCreateRequest> {

    public StudentAchievementController(StudentAchievementService service) {
        super(service);
    }

}