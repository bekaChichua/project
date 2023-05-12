package com.digital.tms.service;

import com.digital.tms.model.dto.RegisterDTO;
import com.digital.tms.model.dto.UserDTO;
import com.digital.tms.model.dto.UserResponseDTO;
import com.digital.tms.model.entity.UserEntity;
import com.digital.tms.model.exceptions.EmailAlreadyExistsException;
import com.digital.tms.model.exceptions.EntityNotFoundException;
import com.digital.tms.repo.UserEntityRepo;
import com.digital.tms.service.iServicies.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService {

    private final UserEntityRepo repo;
    private final ObjectMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<UserResponseDTO> getUsers() {
        return repo.findAll()
                .stream()
                .map(userEntity -> mapper.convertValue(userEntity, UserResponseDTO.class))
                .toList();
    }


    @Override
    @Transactional
    public UserResponseDTO getUserBy(String email) {
        return repo.findByEmail(email)
                .map(userEntity -> mapper.convertValue(userEntity, UserResponseDTO.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void addUser(RegisterDTO user) {
        try {
            var userEntity = mapper.convertValue(user, UserEntity.class);
            userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
            userEntity.setAccountNonExpired(true);
            repo.save(userEntity);
            log.debug("user with email: {} was saved to db", user.getEmail());
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw new EmailAlreadyExistsException(user.getEmail());
        }
    }

    @Override
    @Transactional
    public boolean updateUserBy(RegisterDTO user) {
        UserEntity userEntity=null;
        try {
            userEntity = repo.findByEmail(user.getEmail()).orElseThrow(EntityNotFoundException::new);
            BeanUtils.copyProperties(user, userEntity);
            repo.save(userEntity);
            log.debug("user with email: {} was updated", user.getEmail());
            return true;
        } catch (EntityNotFoundException e) {
            log.warn("user doesn't exists to update");
            assert userEntity != null;
            this.addUser(user);
            return false;
        }
    }

    @Override
    @Transactional
    public void deleteUserBy(String email) {
        repo.deleteUserEntityByEmail(email);
        log.debug("user with email: {} was deletes", email);
    }
}
