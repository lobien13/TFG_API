package intra.intranet2copia.Service;

import intra.intranet2copia.Entidades.DatosCalidadAire;
import intra.intranet2copia.Entidades.Incidencia;
import intra.intranet2copia.Entidades.SistemaArduino;
import intra.intranet2copia.Entidades.StandardAirQualityValores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirQualityChecker {

    @Autowired
    private IncidenciasService incidenciasService;

    public List<Incidencia> checkAirQuality(DatosCalidadAire datosCalidadAire) {
        List<Incidencia> incidencias = new ArrayList<>();
        SistemaArduino sistema = datosCalidadAire.getSistemaArduino();

        // Verificar si los datos son nulos o no son válidos
        if (sistema == null || !datosCalidadAire.isValid()) {
            try {
                Incidencia incidencia = incidenciasService.crearIncidencia(sistema, datosCalidadAire, "Datos mal insertados");
                incidencias.add(incidencia);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Verificar si al menos un valor excede los límites y generar una única incidencia
            if (!isWithinAirQualityLimits(datosCalidadAire)) {
                try {
                    Incidencia incidencia = incidenciasService.crearIncidencia(sistema, datosCalidadAire, "Al menos un valor excede los límites establecidos");
                    incidencias.add(incidencia);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return incidencias;
    }

    // Método para verificar si al menos un valor excede los límites
    private boolean isWithinAirQualityLimits(DatosCalidadAire datosCalidadAire) {
        return StandardAirQualityValores.STANDARD_CO2.isWithinRange(datosCalidadAire.getCo2()) &&
                StandardAirQualityValores.STANDARD_PM25.isWithinRange(datosCalidadAire.getPm25()) &&
                StandardAirQualityValores.STANDARD_PM10.isWithinRange(datosCalidadAire.getPm10()) &&
                StandardAirQualityValores.STANDARD_O3.isWithinRange(datosCalidadAire.getO3()) &&
                StandardAirQualityValores.STANDARD_NO2.isWithinRange(datosCalidadAire.getNo2()) &&
                StandardAirQualityValores.STANDARD_SO2.isWithinRange(datosCalidadAire.getSo2());
    }
}

