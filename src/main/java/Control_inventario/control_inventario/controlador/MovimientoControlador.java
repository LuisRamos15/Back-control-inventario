package Control_inventario.control_inventario.controlador;

import Control_inventario.control_inventario.dto.MovimientoNotification;
import Control_inventario.control_inventario.dto.MovimientoReq;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoControlador {

    private final SimpMessagingTemplate messagingTemplate;

    public MovimientoControlador(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR','OPERADOR')")
    public Map<String, Object> crear(@RequestBody @jakarta.validation.Valid MovimientoReq req, Principal principal) {
        // Build and send notification to websocket subscribers
        MovimientoNotification notif = new MovimientoNotification(req.productoId(), req.cantidad(), req.tipo(), principal != null ? principal.getName() : null);
        messagingTemplate.convertAndSend("/topic/movimientos", notif);

        java.util.Map<String, Object> resp = new java.util.HashMap<>();
        resp.put("productoId", req.productoId());
        resp.put("cantidad", req.cantidad());
        resp.put("tipo", req.tipo());
        resp.put("registrado", true);
        return resp;
    }
}
