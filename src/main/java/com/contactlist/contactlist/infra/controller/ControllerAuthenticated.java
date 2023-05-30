package com.contactlist.contactlist.infra.controller;

import com.contactlist.contactlist.infra.TokenService;
import com.contactlist.contactlist.infra.User;
import com.contactlist.contactlist.infra.UserRepository;
import com.contactlist.contactlist.infra.dtos.TokenJWTDto;
import com.contactlist.contactlist.infra.dtos.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class ControllerAuthenticated {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody UserDto userDto){
        var userToken = new UsernamePasswordAuthenticationToken(userDto.login(),userDto.password());
        var authentication = authenticationManager.authenticate(userToken);
        var token = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJWTDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody  UserDto userDto){
        var user = new User();
        BeanUtils.copyProperties(userDto,user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }


}
