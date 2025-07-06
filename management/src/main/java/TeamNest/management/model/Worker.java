package TeamNest.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Worker{
    private String dni;
    private String name;
    private String phoneNumber;
}
