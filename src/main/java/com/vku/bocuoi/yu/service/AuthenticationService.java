package com.vku.bocuoi.yu.service;

import com.vku.bocuoi.yu.auth.AuthenticationRequest;
import com.vku.bocuoi.yu.auth.AuthenticationResponse;
import com.vku.bocuoi.yu.auth.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
//    ApiResponseDto resetPassword();
}
