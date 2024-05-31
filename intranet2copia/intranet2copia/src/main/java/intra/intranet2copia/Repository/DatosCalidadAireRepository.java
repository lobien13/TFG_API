package intra.intranet2copia.Repository;

import intra.intranet2copia.Entidades.DatosCalidadAire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DatosCalidadAireRepository extends JpaRepository<DatosCalidadAire, Long>{
    Optional<DatosCalidadAire> findById(Long datosId);
    DatosCalidadAire save (DatosCalidadAire datos);

    @Query("SELECT d FROM DatosCalidadAire d WHERE d.co2 > :co2Level")
    List<DatosCalidadAire> findByCo2GreaterThan(Double co2Level);

    @Query("SELECT d FROM DatosCalidadAire d WHERE d.pm25 > :pm25Level")
    List<DatosCalidadAire> findByPm25GreaterThan(Double pm25Level);

    @Query("SELECT d FROM DatosCalidadAire d WHERE d.pm10 > :pm10Level")
    List<DatosCalidadAire> findByPm10GreaterThan(Double pm10Level);

    @Query("SELECT d FROM DatosCalidadAire d WHERE d.o3 > :o3Level")
    List<DatosCalidadAire> findByO3GreaterThan(Double o3Level);

    @Query("SELECT d FROM DatosCalidadAire d WHERE d.no2 > :no2Level")
    List<DatosCalidadAire> findByNo2GreaterThan(Double no2Level);

    @Query("SELECT d FROM DatosCalidadAire d WHERE d.so2 > :so2Level")
    List<DatosCalidadAire> findBySo2GreaterThan(Double so2Level);
}