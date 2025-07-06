package TeamNest.management.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Executive extends Worker{
    private String job;

    public Executive(String dni, String name, String phoneNumber, String job) {
        super(dni, name, phoneNumber);
        this.job = job;
    }
}
