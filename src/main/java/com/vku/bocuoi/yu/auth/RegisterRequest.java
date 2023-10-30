package com.vku.bocuoi.yu.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String studentId;
    private String name;
    private String cId;
    private String phone;
    private String password;
}
