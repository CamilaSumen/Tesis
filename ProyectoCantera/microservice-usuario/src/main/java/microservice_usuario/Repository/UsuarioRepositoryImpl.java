package microservice_usuario.Repository;

import microservice_usuario.Model.Usuario;
import microservice_usuario.Repository.RowMapper.UsuarioRowMapper;
import microservice_usuario.Repository.StoredProcedure.StoredProcedureC;
import microservice_usuario.Repository.Translator.UsuarioTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Usuario> listarUsuarios() {
        String sql = StoredProcedureC.SEL_USUARIO;
        List<UsuarioTranslator> lista = jdbcTemplate.query(sql, new UsuarioRowMapper());
        return lista.stream()
                .map(UsuarioTranslator::toUsuarioDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertarUsuario(Usuario usuario) {
        jdbcTemplate.update(StoredProcedureC.INS_USUARIONUEVO,
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getPrivilegeId(),
                usuario.getShiftId(),
                usuario.getEmployeeId());
    }

    @Override
    public void eliminarUsuarioLogico(int id) {
        jdbcTemplate.update(StoredProcedureC.UPD_ELIMARTUSUARIOLOGICO, id);
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        jdbcTemplate.update(StoredProcedureC.UPD_MODIFICARUSUARIO,
                usuario.getUserId(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getPrivilegeId(),
                usuario.getShiftId());
    }

    @Override
    public Usuario verificarUsuario(String username, String password) {
        String sql = StoredProcedureC.UPD_VERIFICARUSUARIO;
        List<UsuarioTranslator> lista = jdbcTemplate.query(sql, new UsuarioRowMapper(), username, password);

        if (lista.isEmpty()) {
            return null; // usuario no encontrado
        }

        return lista.get(0).toUsuarioDTO(); // devuelve el primer resultado
    }

}
