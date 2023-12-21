package com.vku.bocuoi.yu.mapper;

import com.vku.bocuoi.yu.model.dto.ActivityDto;
import com.vku.bocuoi.yu.model.entity.Activity;
import com.vku.bocuoi.yu.repository.OrganizationRepository;
import jakarta.persistence.EntityNotFoundException;

public class ActivityMapper {
    OrganizationRepository organizationRepository;
    private static ActivityMapper INSTANCE;
    public static ActivityMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ActivityMapper();
        }
        return INSTANCE;
    }
    public ActivityDto toDto(Activity activity) {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activity.getId());
//        activityDto.setUnitId(activity.getOrganization().getId());
        activityDto.setFromTime(activity.getFromTime());
        activityDto.setToTime(activity.getToTime());
        activityDto.setLocation(activity.getLocation());
        activityDto.setTitle(activity.getTitle());
        activityDto.setDescription(activity.getDescription());
        activityDto.setVolunteerMax(activity.getVolunteerMax());
        return activityDto;
    }
    public Activity toEntity(ActivityDto activityDto) {
        Activity activity = new Activity();
        activity.setId(activityDto.getId());
//        activity.setOrganization(organizationRepository.findById(activityDto.getUnitId())
//                .orElseThrow(()-> new EntityNotFoundException(String.format(
//                        "Unit with id [%d] was not found!",
//                        activityDto.getUnitId())
//                ))
//        );
        activity.setFromTime(activityDto.getFromTime());
        activity.setToTime(activityDto.getToTime());
        activity.setLocation(activityDto.getLocation());
        activity.setTitle(activityDto.getTitle());
        activity.setDescription(activityDto.getDescription());
        activity.setVolunteerMax(activityDto.getVolunteerMax());
        return activity;
    }
}
