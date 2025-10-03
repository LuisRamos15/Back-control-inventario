package Control_inventario.control_inventario.controlador;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoControlador {
    @PostMapping
    @PreAuthorize("hasAnyRole('SUPERVISOR','OPERADOR')")
    public Map<String, Object> crear(@RequestBody Map<String, Object> body) {
        body.put("registrado", true);
        return body;
    }
}
