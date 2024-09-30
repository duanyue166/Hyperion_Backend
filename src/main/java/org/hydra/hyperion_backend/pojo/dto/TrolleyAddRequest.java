package org.hydra.hyperion_backend.pojo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrolleyAddRequest {
    @NotNull
    Integer id;

    @Min(1)
    Integer quantity;

    Integer userId;
}
