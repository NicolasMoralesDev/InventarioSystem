package com.nicolasMorales.InventariumSystem.mapper;

import com.nicolasMorales.InventariumSystem.dto.UserDTO;
import com.nicolasMorales.InventariumSystem.entity.UserSec;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author Nicolas Morales.
 * Metodos Mapper para mapear Usuarios.
 */
@Component
public class UserMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Metodo para Mappear de UserSec a UserDTO.
     * @param userSec Recibe un UserSec a mappear.
     * @return Devuelve un UserDTO.
     */
    public UserDTO userSecaUserDTO (UserSec userSec) {
        UserDTO usuario = modelMapper.map(userSec, UserDTO.class);
        return usuario;
    }
}
