package com.aobfilho.cursomc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aobfilho.cursomc.dto.EmailDTO;
import com.aobfilho.cursomc.security.JWTUtil;
import com.aobfilho.cursomc.security.UserSpringSecurity;
import com.aobfilho.cursomc.service.AuthService;
import com.aobfilho.cursomc.service.UserService;

@RestController
@RequestMapping(value="/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService authService;
	
	@PostMapping(value="/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response){
		UserSpringSecurity user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer "+token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value="/forgot")
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDTO){
		authService.sendNewPassword(objDTO.getEmail());
		return ResponseEntity.noContent().build();
	}

}
