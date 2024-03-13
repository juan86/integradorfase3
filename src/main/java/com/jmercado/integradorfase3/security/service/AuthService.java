package com.jmercado.integradorfase3.security.service;

import com.jmercado.integradorfase3.security.dto.AuthResponse;
import com.jmercado.integradorfase3.security.dto.AuthenticationRequest;
import com.jmercado.integradorfase3.security.dto.RegisterRequest;

public interface AuthService {
	AuthResponse register(RegisterRequest request);
	
	AuthResponse authenticate(AuthenticationRequest request);

}