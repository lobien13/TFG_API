package intra.intranet2copia.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import intra.intranet2copia.Entidades.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findById(Long usuarioId);

    Usuario save (Usuario usuario);

}
