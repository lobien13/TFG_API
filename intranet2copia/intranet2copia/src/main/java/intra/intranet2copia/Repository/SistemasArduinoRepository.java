package intra.intranet2copia.Repository;

import intra.intranet2copia.Entidades.SistemaArduino;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SistemasArduinoRepository extends JpaRepository<SistemaArduino, Long>{
    Optional<SistemaArduino> findById(Long sistemaId);
    SistemaArduino save (SistemaArduino sistema);
}
