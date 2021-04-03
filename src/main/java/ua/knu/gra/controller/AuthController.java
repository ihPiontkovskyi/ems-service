package ua.knu.gra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.knu.gra.data.auth.CookieData;
import ua.knu.gra.data.auth.LoginData;
import ua.knu.gra.data.auth.RegisterData;
import ua.knu.gra.model.UserRole;

import javax.servlet.http.HttpSession;

import static java.util.Base64.getDecoder;
import static java.util.Objects.isNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class AuthController extends AbstractController {

    @PostMapping(value = "/login", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CookieData> login(@RequestBody LoginData loginData, HttpSession session) {
        loginData.setPassword(new String(getDecoder().decode(loginData.getPassword())));
        UserRole role = authService.login(loginData);
        if (isNull(role)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        CookieData cookieData = setDataToSession(session, loginData, role);
        return ResponseEntity.ok(cookieData);
    }

    @PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody RegisterData registerData) {
        registerData.setPassword(new String(getDecoder().decode(registerData.getPassword())));
        boolean result = authService.register(registerData);
        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
