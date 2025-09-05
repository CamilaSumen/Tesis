package com.microservice.mesa.microservice_mesa.Repository;

import com.microservice.mesa.microservice_mesa.Model.Mesa;
import com.microservice.mesa.microservice_mesa.Repository.RowMapper.MesaRowMapper;
import com.microservice.mesa.microservice_mesa.Repository.StoredProcedure.StoredProcedureC;
import com.microservice.mesa.microservice_mesa.Repository.Translator.MesaTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MesaRepositoryImpl implements MesaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*IMPLEMENTACION PARA LOS TURNOS*/
    @Override
    public List<Mesa> listarMesas() {
        String sql = StoredProcedureC.SEL_MESA;
        List<MesaTranslator> lista = jdbcTemplate.query(sql, new MesaRowMapper());
        return lista.stream()
                .map(MesaTranslator::toMesaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertarMesa(Mesa mesa) {
        jdbcTemplate.update(StoredProcedureC.INS_MESANUEVO,
                mesa.getTableCode());
    }

    @Override
    public void eliminarMesaLogico(int id) {
        jdbcTemplate.update(StoredProcedureC.UPD_ELIMARMESALOGICO, id);
    }

    @Override
    public void modificarMesa(Mesa mesa) {
        jdbcTemplate.update(StoredProcedureC.UPD_MODIFICARMESA,
                mesa.getTableId(),
                mesa.getTableCode());
    }
}
