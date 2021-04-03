package ua.knu.gra.data.post;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class PostAddData {
    @NotBlank
    private String summary;
    @NotBlank
    private String content;
}
