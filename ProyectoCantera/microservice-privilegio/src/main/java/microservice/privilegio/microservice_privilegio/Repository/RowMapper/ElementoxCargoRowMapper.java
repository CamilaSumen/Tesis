package microservice.privilegio.microservice_privilegio.Repository.RowMapper;


import microservice.privilegio.microservice_privilegio.Repository.Translator.ElementoxCargoTranslator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ElementoxCargoRowMapper implements RowMapper<ElementoxCargoTranslator> {

    @Override
    public ElementoxCargoTranslator mapRow(ResultSet rs, int rowNum) throws SQLException {
        ElementoxCargoTranslator elementoxCargo = new ElementoxCargoTranslator();

        elementoxCargo.setElementChargeId(rs.getInt("nElementoxCargoId"));
        elementoxCargo.setElementId(rs.getInt("nElementoId"));
        elementoxCargo.setChargeId(rs.getInt("nCargoId"));
        elementoxCargo.setElementCode(rs.getString("cCodElmento"));
        elementoxCargo.setModule(rs.getString("cModulo"));
        elementoxCargo.setElementName(rs.getString("cNombreElemento"));
        elementoxCargo.setElementCommand(rs.getString("cComandoElmento"));

        return elementoxCargo;
    }
}