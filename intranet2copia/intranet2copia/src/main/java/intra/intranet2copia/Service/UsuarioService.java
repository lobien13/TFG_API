package intra.intranet2copia.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import intra.intranet2copia.Repository.UsuarioRepository;
import intra.intranet2copia.Entidades.Usuario;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario modificarUsuario(Long id, Usuario usuarioModificado) {
        Optional<Usuario> usuarioExistenteOptional = usuarioRepository.findById(id);

        return usuarioExistenteOptional.map(usuarioExistente -> {
            usuarioExistente.setNombre(usuarioModificado.getNombre());
            usuarioExistente.setEmail(usuarioModificado.getEmail());
            return usuarioRepository.save(usuarioExistente);
        }).orElse(null);
    }

    public boolean existeUsuario(Long id) {
        return usuarioRepository.existsById(id);
    }
}
