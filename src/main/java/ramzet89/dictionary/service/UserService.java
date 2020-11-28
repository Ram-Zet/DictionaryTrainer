package ramzet89.dictionary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ramzet89.dictionary.db.entity.UserEntity;
import ramzet89.dictionary.db.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
