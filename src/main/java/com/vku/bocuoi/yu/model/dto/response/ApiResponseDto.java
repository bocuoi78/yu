package com.vku.bocuoi.yu.model.dto.response;


import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
public class ApiResponseDto {
	private String code;

	private String message;

	private Object data;

	private String status;

}