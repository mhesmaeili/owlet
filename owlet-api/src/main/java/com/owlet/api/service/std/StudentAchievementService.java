package com.owlet.api.service.std;



import com.owlet.api.dto.std.StudentAchievementCreateRequest;
import com.owlet.api.dto.std.StudentAchievementDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface StudentAchievementService extends CrudService<
        UUID,
        StudentAchievementDto,
        StudentAchievementCreateRequest,
        StudentAchievementCreateRequest> {
}