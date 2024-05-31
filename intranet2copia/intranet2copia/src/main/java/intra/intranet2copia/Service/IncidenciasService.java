package intra.intranet2copia.Service;

import intra.intranet2copia.Entidades.Incidencia;
import intra.intranet2copia.Entidades.SistemaArduino;
import intra.intranet2copia.Repository.IncidenciasRepository;
import intra.intranet2copia.Repository.SistemasArduinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import intra.intranet2copia.Entidades.DatosCalidadAire;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IncidenciasService {

    @Autowired
    private IncidenciasRepository incidenciasRepository;

    @Autowired
    private SistemasArduinoRepository sistemasArduinoRepository;

    @Autowired
    private SistemasArduinoService sistemasArduinoService;


    public List<Incidencia> obtenerTodas() {
        return incidenciasRepository.findAll();
    }

    public Optional<Incidencia> obtenerPorId(Long id) {
        return incidenciasRepository.findById(id);
    }



    public void eliminarIncidencia(Long id) {
        incidenciasRepository.deleteById(id);
    }

    public boolean existeIncidencia(Long id) {
        return incidenciasRepository.existsById(id);
    }




    public Incidencia crearIncidencia(SistemaArduino sistema, DatosCalidadAire datosCalidadAire, String mensaje) {
        Incidencia incidencia = new Incidencia();
        incidencia.setSistemaArduino(sistema);
        incidencia.setDatosCalidadAire(datosCalidadAire);
        incidencia.setMensaje(mensaje);
        return incidenciasRepository.save(incidencia);
    }




    public Incidencia crearIncidenciaParaSistema(SistemaArduino sistema, Incidencia incidencia) {
        if (sistema != null) {
            incidencia.setSistemaArduino(sistema);
            return incidenciasRepository.save(incidencia);
        } else {
            // Manejar el caso cuando el sistema no se encuentra
            return null;
        }
    }

    public void eliminarIncidenciasDeSistema(Long sistemaId) {
        SistemaArduino sistema = sistemasArduinoService.obtenerSistema(sistemaId);
        if (sistema != null) {
            List<Incidencia> incidencias = new ArrayList<>(sistema.getIncidencias());
            for (Incidencia incidencia : incidencias) {
                sistema.getIncidencias().remove(incidencia); // Eliminar la incidencia de la lista del sistema
                incidenciasRepository.delete(incidencia); // Eliminar la incidencia de la base de datos
            }
        } else {
            throw new RuntimeException("Sistema no encontrado");
        }
    }

}
