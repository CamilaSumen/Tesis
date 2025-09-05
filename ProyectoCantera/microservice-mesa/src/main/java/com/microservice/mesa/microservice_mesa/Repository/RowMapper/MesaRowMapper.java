package com.microservice.mesa.microservice_mesa.Repository.RowMapper;

import com.microservice.mesa.microservice_mesa.Repository.Translator.MesaTranslator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MesaRowMapper implements RowMapper<MesaTranslator> {

    @Override
    public MesaTranslator mapRow(ResultSet rs, int rowNum) throws SQLException {
        MesaTranslator mesa = new MesaTranslator();

        mesa.setTableId(rs.getInt("nMesaId"));
        mesa.setTableCode(rs.getString("cCodMesa"));
        mesa.setTableBusy(rs.getBoolean("bocupado"));
        mesa.setStateTable(rs.getBoolean("bEstado"));

        return mesa;
    }
}
