package intra.intranet2copia.Controlador;

import intra.intranet2copia.Entidades.Usuario;
import intra.intranet2copia.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodosUsuarios();
    }

    @PostMapping("/usuarios/añadir")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @DeleteMapping("eliminar/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }

}