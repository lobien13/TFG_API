package intra.intranet2copia.Repository;
import intra.intranet2copia.Entidades.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IncidenciasRepository extends JpaRepository<Incidencia, Long> {
    Optional<Incidencia> findById(Long incidenciaId);
    Incidencia save (Incidencia incidencia);
}
