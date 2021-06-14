package ramzet89.dictionary.enums.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permission {
    USER_LEARN("user:learn"),
    USER_ADD_WORDS("user:add_words"),
    ADMIN_USERS("admin:users");

    private final String permission;
}
