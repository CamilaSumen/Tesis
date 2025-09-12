package microservice_usuario.Controller;

import microservice_usuario.Model.Usuario;
import microservice_usuario.Service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuario")
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

    @PostMapping("/verificar")
    public ResponseEntity<Usuario> verificar(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        Usuario result = usuarioService.verificarUsuario(username, password);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(result);
    }



}