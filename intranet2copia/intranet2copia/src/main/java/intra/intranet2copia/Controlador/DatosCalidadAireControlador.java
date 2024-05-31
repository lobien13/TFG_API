package intra.intranet2copia.Controlador;

import intra.intranet2copia.Entidades.DatosCalidadAire;
import intra.intranet2copia.Entidades.Incidencia;
import intra.intranet2copia.Entidades.SistemaArduino;
import intra.intranet2copia.Service.AirQualityChecker;
import intra.intranet2copia.Service.DatosCalidadAireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/datosCalidadAire")
public class DatosCalidadAireControlador {

    @Autowired
    private DatosCalidadAireService datosCalidadAireService;

    @Autowired
    private intra.intranet2copia.Repository.SistemasArduinoRepository sistemasArduinoRepository;

    @Autowired
    private AirQualityChecker airQualityChecker;

    @Autowired
    private intra.intranet2copia.Repository.IncidenciasRepository incidenciasRepository;

    @GetMapping("/datosCalidadAire")
    public List<DatosCalidadAire> obtenerTodos() {
        return datosCalidadAireService.obtenerTodos();
    }

    @PostMapping("/datosCalidadAire/añadir")
    public DatosCalidadAire crearDatos(@RequestBody DatosCalidadAire datos) {
        SistemaArduino sistema = sistemasArduinoRepository.findById(datos.getSistemaArduino().getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "SistemaArduino no encontrado con id: " + datos.getSistemaArduino().getId()));
        datos.setSistemaArduino(sistema);

        // Guardar los datos de calidad del aire en la base de datos
        DatosCalidadAire datosGuardados = datosCalidadAireService.save(datos);

        // Pasar los datos a través de AirQualityChecker
        List<Incidencia> incidencias = airQualityChecker.checkAirQuality(datosGuardados);
        if (!incidencias.isEmpty()) {
            for (Incidencia incidencia : incidencias) {
                incidencia.setSistemaArduino(sistema); // Asociar la incidencia al sistema
                incidenciasRepository.save(incidencia);
            }
        }

        // Devolver los datos de calidad del aire guardados
        return datosGuardados;
    }

    @DeleteMapping("/{id}")
    public void eliminarDatos(@PathVariable Long id) {
        datosCalidadAireService.eliminarDatos(id);
    }
}
