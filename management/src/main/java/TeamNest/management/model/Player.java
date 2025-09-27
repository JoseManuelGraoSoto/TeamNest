package TeamNest.management.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Player extends Worker{
    private int age;
    private long marketValue;
    private boolean conditionToPlay;

    public Player(String dni, String name, String phoneNumber, int age, long marketValue, boolean conditionToPlay) {
        super(dni, name, phoneNumber);
        this.age = age;
        this.marketValue = marketValue;
        this.conditionToPlay = conditionToPlay;
    }

    public boolean getConditionToPlay(){
        return conditionToPlay;
    }
}
