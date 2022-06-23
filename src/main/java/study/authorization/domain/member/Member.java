package study.authorization.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

    private Long id;

    private String name;
    private String password;

    private Long wallet;
}
