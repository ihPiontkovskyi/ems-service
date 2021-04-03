package ua.knu.gra.data.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@JsonFormat
@Getter
@Setter
public class CookieData {
    private String sessionId;
    private String role;
}
