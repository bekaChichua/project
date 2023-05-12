package com.digital.tms.service.iServicies;

import com.digital.tms.model.dto.LoginDTO;
import com.digital.tms.model.dto.RegisterDTO;

public interface IAuthService {
    void register(RegisterDTO userDto);
    String login(LoginDTO loginDTO);
}
