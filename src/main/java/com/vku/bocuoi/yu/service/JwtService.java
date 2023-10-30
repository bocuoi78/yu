package com.vku.bocuoi.yu.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    public String extractStudentId(String token);
    public String generateToken(UserDetails userDetails);
    public boolean isTokenValid(String token, UserDetails userDetails);
}
