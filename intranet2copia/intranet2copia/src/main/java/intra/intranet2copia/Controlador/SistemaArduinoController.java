package intra.intranet2copia.Controlador;


import intra.intranet2copia.Entidades.SistemaArduino;

import intra.intranet2copia.Service.SistemasArduinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sistemasArduino")
public class SistemaArduinoController {

    @Autowired
    private SistemasArduinoService sistemaArduinoService;

    @GetMapping("/sistemasArduino")
    public List<SistemaArduino> obtenerTodos() {
        return sistemaArduinoService.obtenerTodosSistemas();
    }

    @PostMapping("sistema/añadir")
    public SistemaArduino crearSistema(@RequestBody SistemaArduino sistemaArduino) {
        return sistemaArduinoService.crearSistema(sistemaArduino);
    }

    @DeleteMapping("/{id}")
    public void eliminarSistemaArduino(@PathVariable Long id) {
        sistemaArduinoService.eliminarSistema(id);
    }

    @GetMapping("/api/v1/sistemasArduino/{id}")
    public SistemaArduino obtenerSistemaPorId(@PathVariable Long id) {
        return sistemaArduinoService.obtenerSistemaPorId(id)
                .orElseThrow(() -> new RuntimeException("Sistema no encontrado"));
    }

    @PostMapping("/asociar/{idArduino}/{idUsuario}")
    public void asociarArduinoUsuario(@PathVariable Long idArduino, @PathVariable Long idUsuario) {
        sistemaArduinoService.asociarUsuarioASistema(idArduino, idUsuario);
    }


}
