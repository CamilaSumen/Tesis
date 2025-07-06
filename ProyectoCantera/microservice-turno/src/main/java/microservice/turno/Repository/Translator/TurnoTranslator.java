package microservice.turno.Repository.Translator;


import microservice.turno.Model.Turno;

import java.time.LocalTime;

public class TurnoTranslator {

    private Integer shiftId;
    private String nameshift;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean stateshift;

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public void setNameshift(String nameshift) {
        this.nameshift = nameshift;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setStateshift(Boolean stateshift) {
        this.stateshift = stateshift;
    }


    public Turno toTurnoDTO() {
        Turno turno = new Turno();
        turno.setShiftId(this.shiftId);
        turno.setNameshift(this.nameshift);
        turno.setStartTime(this.startTime);
        turno.setEndTime(this.endTime);
        turno.setStateshift(this.stateshift);
        return turno;
    }
}