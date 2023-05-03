package com.intreswitch.articleblogsystemintv.controllers;

import com.intreswitch.articleblogsystemintv.security.jwt.JwtTokenUtils;
import com.intreswitch.articleblogsystemintv.security.jwt.JwtUserPrincipal;
import com.intreswitch.articleblogsystemintv.security.jwt.UnauthorizedException;
import com.intreswitch.articleblogsystemintv.security.model.UserDTO;
import com.intreswitch.articleblogsystemintv.security.model.UserLoginModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController()
@RequestMapping(value = "/AuthenticationController")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;


    @PostMapping("/login")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserLoginModel loginModel,
                                              HttpServletResponse response,
                                              HttpServletRequest request) {

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginModel.getUsername(),
                            loginModel.getPassword()));
            final JwtUserPrincipal userDetails = (JwtUserPrincipal) authentication.getPrincipal();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtils.generateToken(userDetails);
            response.setHeader("Token", token);
            return new ResponseEntity<UserDTO>(new UserDTO(userDetails.getAuthor(), token), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnauthorizedException(e.getMessage());
        }

    }
}
