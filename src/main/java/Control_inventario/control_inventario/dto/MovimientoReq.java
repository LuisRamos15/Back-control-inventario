package Control_inventario.control_inventario.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovimientoReq(
        @NotBlank String productoId,
        @NotNull @Min(1) Integer cantidad,
        @NotBlank String tipo
) {
}
