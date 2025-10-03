package Control_inventario.control_inventario.controlador;

import Control_inventario.control_inventario.entidad.Producto;
import Control_inventario.control_inventario.servicio.ProductoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoControlador {

    private final ProductoServicio servicio;

    public ProductoControlador(ProductoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR','OPERADOR')")
    public List<Producto> listar() {
        return servicio.listar();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR')")
    public ResponseEntity<Producto> crear(@RequestBody Producto body) {
        Producto creado = servicio.crear(body);
        return ResponseEntity.created(URI.create("/api/productos/" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR')")
    public ResponseEntity<Producto> actualizar(@PathVariable String id, @RequestBody Producto body) {
        return servicio.actualizar(id, body)
                .map(p -> ResponseEntity.ok(p))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> eliminar(@PathVariable String id) {
        boolean ok = servicio.eliminar(id);
        if (ok) return ResponseEntity.ok().body(java.util.Map.of("id", id, "eliminado", true));
        return ResponseEntity.notFound().build();
    }
}
