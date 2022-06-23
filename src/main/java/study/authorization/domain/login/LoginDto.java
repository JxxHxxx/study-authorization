package study.authorization.domain.login;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class LoginDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;
}
