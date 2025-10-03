package Control_inventario.control_inventario.controlador;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
public class ProductoControlador {
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR','OPERADOR')")
    public List<Map<String, Object>> listar() {
        return List.of(
                Map.of("id", "p1", "nombre", "Laptop", "stock", 12),
                Map.of("id", "p2", "nombre", "Mouse", "stock", 55)
        );
    }


    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR')")
    public Map<String, Object> crear(@RequestBody Map<String, Object> body) {
        body.put("id", "nuevo-id");
        return body;
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR')")
    public Map<String, Object> actualizar(@PathVariable String id, @RequestBody Map<String, Object> body) {
        body.put("id", id);
        body.put("actualizado", true);
        return body;
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Object> eliminar(@PathVariable String id) {
        return Map.of("id", id, "eliminado", true);
    }
}
