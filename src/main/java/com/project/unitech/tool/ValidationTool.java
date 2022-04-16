package com.project.unitech.tool;

import com.project.unitech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ValidationTool {

    private final UserRepository userRepository;

    public boolean isPinUnique(String pin) {
        return userRepository.findUserByPin(pin.toUpperCase()).equals(Optional.empty());
    }
}
