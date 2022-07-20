package com.example.jubging.service;

import com.example.jubging.model.dto.UserDTO;
import com.example.jubging.model.entity.User;
import com.example.jubging.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    // Create
    public void register(final UserDTO userDTO) {
        userRepository.save(userDTO.toEntity());
    }

    // TODO
    // get
    // delete
    // modify

}
