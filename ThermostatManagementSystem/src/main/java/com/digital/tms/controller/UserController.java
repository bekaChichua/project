package com.digital.tms.controller;

import com.digital.tms.model.dto.ArrayResponse;
import com.digital.tms.model.dto.RegisterDTO;
import com.digital.tms.model.dto.UserDTO;
import com.digital.tms.model.dto.UserResponseDTO;
import com.digital.tms.model.entity.UserEntity;
import com.digital.tms.service.iServicies.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final IUserService service;

    @GetMapping
    public ArrayResponse<UserResponseDTO> getUsers() {
        var users = service.getUsers();
        return new ArrayResponse<>(users);
    }

    @GetMapping("/{email}")
    public UserResponseDTO getUserBy(@PathVariable String email) {
        return service.getUserBy(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@Valid @RequestBody RegisterDTO user) {
        service.addUser(user);
    }

    @PutMapping
    public ResponseEntity<Void> updateUserBy(@Valid @RequestBody RegisterDTO user) {
        var isUpdated = service.updateUserBy(user);
        return isUpdated ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{email}")
    public void deleteUser(@PathVariable String email) {
        service.deleteUserBy(email);
    }
}
