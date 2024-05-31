package intra.intranet2copia.Service;

import intra.intranet2copia.Entidades.SistemaArduino;
import intra.intranet2copia.Entidades.Usuario;
import intra.intranet2copia.Repository.SistemasArduinoRepository;
import intra.intranet2copia.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SistemasArduinoService {

    @Autowired
    private SistemasArduinoRepository sistemasArduinoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<SistemaArduino> obtenerTodosSistemas() {
        return sistemasArduinoRepository.findAll();
    }

    public Optional<SistemaArduino> obtenerSistemaPorId(Long id) {
        return sistemasArduinoRepository.findById(id);
    }

    public SistemaArduino crearSistema(SistemaArduino sistema) {
        return sistemasArduinoRepository.save(sistema);
    }

    public void eliminarSistema(Long id) {
        sistemasArduinoRepository.deleteById(id);
    }

    public boolean existeSistema(Long id) {
        return sistemasArduinoRepository.existsById(id);
    }

    public void asociarUsuarioASistema(Long idSistema, Long idUsuario) {
        Optional<SistemaArduino> sistemaOptional = sistemasArduinoRepository.findById(idSistema);
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);

        if (sistemaOptional.isPresent() && usuarioOptional.isPresent()) {
            SistemaArduino sistema = sistemaOptional.get();
            Usuario usuario = usuarioOptional.get();

            sistema.setUsuario(usuario);
            sistemasArduinoRepository.save(sistema);
        } else {
            // Manejo de excepción si no se encuentra el sistema o el usuario
            throw new IllegalArgumentException("No se encontró el sistema o el usuario correspondiente.");
        }
    }

    public void desasociarUsuarioDeSistema(Long idSistema) {
        Optional<SistemaArduino> sistemaOptional = sistemasArduinoRepository.findById(idSistema);

        if (sistemaOptional.isPresent()) {
            SistemaArduino sistema = sistemaOptional.get();
            sistema.setUsuario(null);
            sistemasArduinoRepository.save(sistema);
        } else {
            // Manejo de excepción si no se encuentra el sistema
            throw new IllegalArgumentException("No se encontró el sistema correspondiente.");
        }
    }


    public SistemaArduino obtenerSistema(Long id) {
        return sistemasArduinoRepository.findById(id).orElse(null);
    }

    public SistemaArduino guardarSistema(SistemaArduino sistema) {
        return sistemasArduinoRepository.save(sistema);
    }
}
