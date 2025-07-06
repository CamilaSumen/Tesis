package microservice.privilegio.microservice_privilegio.Repository;

import microservice.privilegio.microservice_privilegio.Model.Privilegio;
import microservice.privilegio.microservice_privilegio.Repository.RowMapper.PrivilegioRowMapper;
import microservice.privilegio.microservice_privilegio.Repository.StoredProcedure.StoredProcedureC;
import microservice.privilegio.microservice_privilegio.Repository.Translator.PrivilegioTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PrivilegioRepositoryImpl implements PrivilegioRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /*IMPLEMENTACION PARA LOS PRIVILEGIOS*/
    @Override
    public List<Privilegio> listarPrivilegios() {
        String sql = StoredProcedureC.SEL_PRIVILEGIO;
        List<PrivilegioTranslator> lista = jdbcTemplate.query(sql, new PrivilegioRowMapper());
        return lista.stream()
                .map(PrivilegioTranslator::toPrivilegioDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertarPrivilegio(Privilegio privilegio) {
        jdbcTemplate.update(StoredProcedureC.INS_PRIVILEGIONUEVO,
                privilegio.getPrivilegeName(),
                privilegio.getDescription(),
                privilegio.getObservation());
    }

    @Override
    public void eliminarPrivilegioLogico(int id) {
        jdbcTemplate.update(StoredProcedureC.UPD_ELIMARPRIVILEGIOLOGICO, id);
    }

    @Override
    public void modificarPrivilegio(Privilegio privilegio) {
        jdbcTemplate.update(StoredProcedureC.UPD_MODIFICARPRIVILEGIO,
                privilegio.getPrivilegeId(),
                privilegio.getPrivilegeName(),
                privilegio.getDescription(),
                privilegio.getObservation());
    }

}
