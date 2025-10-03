package Control_inventario.control_inventario.entidad;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("productos")
public class Producto {

    @Id
    private String id;

    @NotBlank
    private String nombre;

    private Integer stock;

    public Producto() {}

    public Producto(String nombre, Integer stock) {
        this.nombre = nombre;
        this.stock = stock;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
