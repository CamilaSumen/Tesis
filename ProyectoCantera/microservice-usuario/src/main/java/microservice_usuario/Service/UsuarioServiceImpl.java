package microservice_usuario.Service;

import microservice_usuario.Model.Usuario;
import microservice_usuario.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarUsuarios();
    }

    @Override
    public void insertarUsuario(Usuario usuario) {
        usuarioRepository.insertarUsuario(usuario);
    }

    @Override
    public void eliminarUsuarioLogico(int id) {
        usuarioRepository.eliminarUsuarioLogico(id);
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        usuarioRepository.modificarUsuario(usuario);
    }


}
