package com.vku.bocuoi.yu.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String studentId;
    private String name;
    private Date birthday;
    private String cId;
    private String phone;
    private String password;
    private Long organizationId;
}
