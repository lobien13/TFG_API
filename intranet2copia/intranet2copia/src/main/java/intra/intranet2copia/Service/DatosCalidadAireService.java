package intra.intranet2copia.Service;

import intra.intranet2copia.Entidades.DatosCalidadAire;
import intra.intranet2copia.Entidades.Incidencia;
import intra.intranet2copia.Entidades.SistemaArduino;
import intra.intranet2copia.Repository.DatosCalidadAireRepository;
import intra.intranet2copia.Repository.IncidenciasRepository;
import intra.intranet2copia.Repository.SistemasArduinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatosCalidadAireService {

    @Autowired
    private DatosCalidadAireRepository datosCalidadAireRepository;

    @Autowired
    private IncidenciasRepository incidenciasRepository;

    @Autowired
    private SistemasArduinoRepository sistemasArduinoRepository;

    @Autowired
    private AirQualityChecker airQualityChecker;

    public List<DatosCalidadAire> obtenerTodos(){
        return datosCalidadAireRepository.findAll();
    }

    public DatosCalidadAire crearDatos(DatosCalidadAire datos, Long sistemaId){
        SistemaArduino sistemaArduino = sistemasArduinoRepository.findById(sistemaId).orElse(null);
        if (sistemaArduino != null) {
            datos.setSistemaArduino(sistemaArduino);
        } else {
            // Manejar el caso cuando el sistema no se encuentra
            return null;
        }
        DatosCalidadAire savedDatos = datosCalidadAireRepository.save(datos);
        List<Incidencia> incidencias = airQualityChecker.checkAirQuality(savedDatos);
        if (!incidencias.isEmpty()) {
            for (Incidencia incidencia : incidencias) {
                incidenciasRepository.save(incidencia);
            }
        }
        return savedDatos;
    }

    public DatosCalidadAire save(DatosCalidadAire datos) {
        return datosCalidadAireRepository.save(datos);
    }

    public void eliminarDatos(Long id){
        datosCalidadAireRepository.deleteById(id);
    }
}
