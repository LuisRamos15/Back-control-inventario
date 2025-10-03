package Control_inventario.control_inventario.servicio;

import Control_inventario.control_inventario.entidad.Producto;
import Control_inventario.control_inventario.repositorio.ProductoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicio {

    private final ProductoRepositorio repo;

    public ProductoServicio(ProductoRepositorio repo) {
        this.repo = repo;
    }

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto crear(Producto p) {
        return repo.save(p);
    }

    public Optional<Producto> buscarPorId(String id) {
        return repo.findById(id);
    }

    public Optional<Producto> actualizar(String id, Producto cambios) {
        return repo.findById(id).map(existing -> {
            if (cambios.getNombre() != null) existing.setNombre(cambios.getNombre());
            if (cambios.getStock() != null) existing.setStock(cambios.getStock());
            return repo.save(existing);
        });
    }

    public boolean eliminar(String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
