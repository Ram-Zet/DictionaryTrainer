package ramzet89.dictionary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ramzet89.dictionary.db.entity.UserEntity;
import ramzet89.dictionary.service.UserService;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class AdminController {
    private final UserService userService;


    @GetMapping("/")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }
}
