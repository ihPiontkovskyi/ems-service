package ua.knu.gra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.knu.gra.data.CookieData;
import ua.knu.gra.data.LoginData;
import ua.knu.gra.model.UserRole;

import javax.servlet.http.HttpSession;

import static java.util.Objects.isNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class AuthController extends AbstractController {

    @PostMapping(value = "/login", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CookieData> login(@RequestBody LoginData loginData, HttpSession session) {
        UserRole role = authService.login(loginData);
        if (isNull(role)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        CookieData cookieData = setDataToSession(session, loginData, role);
        return ResponseEntity.ok(cookieData);
    }

    @GetMapping(value = "/check")
    public ResponseEntity<?> checkAuth(@CookieValue(name = SESSION_ID_COOKIE_NAME) String cookieId,
                                       @CookieValue(name = ROLE_COOKIE_NAME) String cookieRole,
                                       HttpSession session) {
        if (checkSessionValues(session, cookieId, cookieRole)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
