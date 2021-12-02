package com.switchfully.digibooky.api;

import com.switchfully.digibooky.security.Features;
import com.switchfully.digibooky.security.Role;
import com.switchfully.digibooky.services.SecurityService;
import com.switchfully.digibooky.services.UserService;
import com.switchfully.digibooky.services.dtos.CreateUserDTO;
import com.switchfully.digibooky.services.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users", produces = "application/json")
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;

    @Autowired
    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers(@RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Features.GET_ALL_MEMBERS);
        return userService.getAllUsers();
    }

    @PostMapping(path = "/register-user", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createNewUser(@RequestBody CreateUserDTO createUserDTO,
                                @RequestHeader(required = false) String authorization){
        if(createUserDTO.getRole().equals(Role.LIBRARIAN)){
            securityService.validateAuthorization(authorization, Features.REGISTER_NEW_LIBRARIAN);
        }

        return userService.save(createUserDTO);
    }
}
