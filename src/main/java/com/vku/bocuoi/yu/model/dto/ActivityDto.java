package com.vku.bocuoi.yu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto implements Serializable {
    private Long id;
    private Long unitId;
    private Date fromTime;
    private Date toTime;
    private String location;
    private String title;
    private String description;
    private Integer volunteerMax;
    private Set<VolunteerDto> volunteerDtoSet;
    private Set<CommentDto> commentDtoSet;
}
