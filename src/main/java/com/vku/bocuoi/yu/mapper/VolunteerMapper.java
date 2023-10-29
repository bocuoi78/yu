package com.vku.bocuoi.yu.mapper;

import com.vku.bocuoi.yu.model.dto.VolunteerDto;
import com.vku.bocuoi.yu.model.entity.Volunteer;
import com.vku.bocuoi.yu.repository.ActivityRepository;
import com.vku.bocuoi.yu.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;

public class VolunteerMapper {
    ActivityRepository activityRepository;
    StudentRepository studentRepository;
    private static VolunteerMapper INSTANCE;
    public static VolunteerMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new VolunteerMapper();
        }
        return INSTANCE;
    }
    public VolunteerDto toDto(Volunteer volunteer) {
        VolunteerDto volunteerDto = new VolunteerDto();
        volunteerDto.setActivityId(volunteer.getActivity().getId());
        volunteerDto.setStudentId(volunteer.getStudent().getId());
        volunteerDto.setDescription(volunteer.getDescription());
        return volunteerDto;
    }
    public Volunteer toEntity(VolunteerDto volunteerDto) {
        Volunteer volunteer = new Volunteer();
        volunteer.setActivity(activityRepository.findById(volunteerDto.getActivityId())
                .orElseThrow(()-> new EntityNotFoundException(String.format(
                        "Activity with id [%d] was not found!",
                        volunteerDto.getActivityId())
                ))
        );
        volunteer.setStudent(studentRepository.findById(volunteerDto.getStudentId())
                .orElseThrow(()-> new EntityNotFoundException(String.format(
                        "Student with id [%s] was not found!",
                        volunteerDto.getStudentId())
                ))
        );
        volunteer.setDescription(volunteerDto.getDescription());
        return volunteer;
    }
}
