package org.hydra.hyperion_backend.pojo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hydra.hyperion_backend.annotation.ValidRegexp;

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
     * 收获人姓名
     */
    @NotNull
    private String consignee;

    /**
     * 收货人联系电话
     */
    @NotNull
    @ValidRegexp(type = "tel")
    private String contact;
    /**
     * 用户 ID
     */
    private Integer userId;

    @NotNull(groups = {AddressRequest.Update.class})
    private long id;

    public interface Update extends Default {
    }
}
