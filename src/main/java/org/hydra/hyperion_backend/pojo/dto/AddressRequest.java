package org.hydra.hyperion_backend.pojo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    /**
     * 城市代码
     */
    @NotNull
    private Integer cityId;
    /**
     * 详细地址
     */
    @NotNull
    private String detail;
    /**
     * 区县代码
     */
    @NotNull
    private Integer distId;
    /**
     * 省份代码
     */
    @NotNull
    private Integer provId;
    /**
     * 用户 ID
     */
    private Integer userId;

    @NotNull(groups = {AddressRequest.Update.class})
    private long id;

    public interface Update {
    }
}
