package microservice.privilegio.microservice_privilegio.Repository.RowMapper;


import microservice.privilegio.microservice_privilegio.Repository.Translator.ElementoTranslator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ElementoRowMapper implements RowMapper<ElementoTranslator> {

    @Override
    public ElementoTranslator mapRow(ResultSet rs, int rowNum) throws SQLException {
        ElementoTranslator elemento = new ElementoTranslator();

        elemento.setElementId(rs.getInt("nElementoId"));
        elemento.setElementCode(rs.getString("cCodElmento"));
        elemento.setModule(rs.getString("cModulo"));
        elemento.setElementName(rs.getString("cNombreElemento"));
        elemento.setElementCommand(rs.getString("cComandoElmento"));

        return elemento;
    }
}