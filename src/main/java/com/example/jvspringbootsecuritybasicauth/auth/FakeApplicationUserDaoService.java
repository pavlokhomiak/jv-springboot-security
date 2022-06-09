package com.example.jvspringbootsecuritybasicauth.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.jvspringbootsecuritybasicauth.security.ApplicationUserRole.ADMIN;
import static com.example.jvspringbootsecuritybasicauth.security.ApplicationUserRole.ADMIN_TRAINEE;
import static com.example.jvspringbootsecuritybasicauth.security.ApplicationUserRole.STUDENT;

@Repository("fake")
@RequiredArgsConstructor
public class FakeApplicationUserDaoService implements ApplicationUserDao {
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream()
            .filter(applicationUser -> username.equals(applicationUser.getUsername()))
            .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = List.of(
            new ApplicationUser(STUDENT.getGrantedAuthorities(),
                "bob",
                passwordEncoder.encode("1"),
                true,
                true,
                true,
                true
            ),
            new ApplicationUser(ADMIN.getGrantedAuthorities(),
                "alisa",
                passwordEncoder.encode("1"),
                true,
                true,
                true,
                true
            ),
            new ApplicationUser(ADMIN_TRAINEE.getGrantedAuthorities(),
                "Pavlo",
                passwordEncoder.encode("1"),
                true,
                true,
                true,
                true
            )
        );
        return applicationUsers;
    }
}
