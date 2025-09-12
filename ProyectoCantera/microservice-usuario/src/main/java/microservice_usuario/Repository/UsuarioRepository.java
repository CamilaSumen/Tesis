package microservice_usuario.Repository;

import microservice_usuario.Model.Usuario;

import java.util.List;

public interface UsuarioRepository {

    List<Usuario> listarUsuarios();
    void insertarUsuario(Usuario usuario);
    void eliminarUsuarioLogico(int id);
    void modificarUsuario(Usuario usuario);
    Usuario verificarUsuario(String username, String password);

}
