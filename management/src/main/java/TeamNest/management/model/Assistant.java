package TeamNest.management.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Assistant extends Worker{
    private String job;
    private String speciality;

    public Assistant(String dni, String name, String phoneNumber, String job, String speciality) {
        super(dni, name, phoneNumber);
        this.job = job;
        this.speciality = speciality;
    }
}
