package intra.intranet2copia.Controlador;


import intra.intranet2copia.Entidades.DatosCalidadAire;
import intra.intranet2copia.Entidades.Incidencia;
import intra.intranet2copia.Entidades.SistemaArduino;
import intra.intranet2copia.Service.IncidenciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
public class IncidenciasControlador {

    @Autowired
    private IncidenciasService incidenciasService;

    @GetMapping("/incidencias")
    public List<Incidencia> obtenerTodos() {
        return incidenciasService.obtenerTodas();
    }

    @PostMapping("/incidencias/añadir")
    public Incidencia crearIncidencia(@RequestBody Incidencia incidencia) {
        SistemaArduino sistema = incidencia.getSistemaArduino();
        DatosCalidadAire datosCalidadAire = incidencia.getDatosCalidadAire();
        String mensaje = incidencia.getMensaje();
        return incidenciasService.crearIncidencia(sistema, datosCalidadAire, mensaje);
    }



    @DeleteMapping("/eliminar/{id}")
    public void eliminarIncidencia(@PathVariable Long id) {
        incidenciasService.eliminarIncidencia(id);
    }

    @DeleteMapping("/sistemas/{sistemaId}/incidencias")
    public void eliminarIncidenciasDeSistema(@PathVariable Long sistemaId) {
        incidenciasService.eliminarIncidenciasDeSistema(sistemaId);
    }

}

