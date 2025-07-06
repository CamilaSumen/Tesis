package microservice_usuario.Service;

import microservice_usuario.Model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> listarUsuarios();
    void insertarUsuario(Usuario usuario);
    void eliminarUsuarioLogico(int id);
    void modificarUsuario(Usuario usuario);

}
