package Control_inventario.control_inventario.dto;

public record MovimientoNotification(
        String productoId,
        Integer cantidad,
        String tipo,
        String usuario
) {
}
