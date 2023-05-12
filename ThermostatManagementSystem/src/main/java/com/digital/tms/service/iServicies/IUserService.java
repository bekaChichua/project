package com.digital.tms.service.iServicies;

import com.digital.tms.model.dto.RegisterDTO;
import com.digital.tms.model.dto.UserDTO;
import com.digital.tms.model.dto.UserResponseDTO;
import com.digital.tms.model.entity.UserEntity;

import java.util.List;

public interface IUserService {
    List<UserResponseDTO> getUsers();

    UserResponseDTO getUserBy(String email);

    void addUser(RegisterDTO userDto);

    boolean updateUserBy(RegisterDTO user);

    void deleteUserBy(String email);

}
