package Control_inventario.control_inventario.repositorio;

import Control_inventario.control_inventario.entidad.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoRepositorio extends MongoRepository<Producto, String> {
}
