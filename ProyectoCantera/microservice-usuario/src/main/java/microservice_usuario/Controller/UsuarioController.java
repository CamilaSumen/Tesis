package microservice_usuario.Controller;

import microservice_usuario.Model.Usuario;
import microservice_usuario.Service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    public List<Usuario> listar() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody Usuario usuario) {
        usuarioService.insertarUsuario(usuario);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody Usuario usuario) {
        usuarioService.modificarUsuario(usuario);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        usuarioService.eliminarUsuarioLogico(id);
    }
}