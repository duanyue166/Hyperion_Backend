package org.hydra.hyperion_backend.pojo.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hydra.hyperion_backend.annotation.ValidRegexp;

/**
 * Created with IntelliJ IDEA.
 * author： Shuowei Hou
 * date： 2024/9/29
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChangeRequest {
    Integer id;
    
    @ValidRegexp(type = "username")
    String name;

    @ValidRegexp(type = "tel")
    String tel;

    @Email
    String email;
}
