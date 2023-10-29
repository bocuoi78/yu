package com.vku.bocuoi.yu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto implements Serializable {
    private Long id;
    private Long activityId;
    private String title;
    private String content;
    private Long commentId;
    private Set<CommentDto> commentDtoSet;
    private Set<ActivityDto> activityDtoSet;
}
