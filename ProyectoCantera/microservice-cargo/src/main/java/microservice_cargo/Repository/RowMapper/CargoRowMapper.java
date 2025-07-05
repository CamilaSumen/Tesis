package microservice_cargo.Repository.RowMapper;

import microservice_cargo.Repository.Translator.CargoTranslator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CargoRowMapper implements RowMapper<CargoTranslator> {

    @Override
    public CargoTranslator mapRow(ResultSet rs, int rowNum) throws SQLException {
        CargoTranslator cargo = new CargoTranslator();
        cargo.setIdcharge(rs.getInt("nCargoId"));
        cargo.setNameCargue(rs.getString("cNombreCargo"));
        cargo.setDescriptionCargue(rs.getString("cDescripcion"));
        cargo.setSalary(rs.getDouble("nSueldo"));
        cargo.setStateCargue(rs.getBoolean("bEstado"));
        return cargo;
    }
}