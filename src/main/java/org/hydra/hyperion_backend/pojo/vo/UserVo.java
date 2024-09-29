package org.hydra.hyperion_backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * author： Shuowei Hou
 * date： 2024/9/29
 * description：
 */
@Data
@Builder
public class UserVo {
    Integer id;
    String name;
    String tel;
    String email;
}
