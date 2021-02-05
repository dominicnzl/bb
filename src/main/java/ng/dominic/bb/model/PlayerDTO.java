package ng.dominic.bb.model;

public class PlayerDTO {
    private String name;
    private int movementAllowance;
    private int strength;
    private Integer agility;
    private Integer armourValue;
    private Integer passing;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMovementAllowance() {
        return movementAllowance;
    }

    public void setMovementAllowance(int movementAllowance) {
        this.movementAllowance = movementAllowance;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Integer getAgility() {
        return agility;
    }

    public void setAgility(Integer agility) {
        this.agility = agility;
    }

    public Integer getArmourValue() {
        return armourValue;
    }

    public void setArmourValue(Integer armourValue) {
        this.armourValue = armourValue;
    }

    public Integer getPassing() {
        return passing;
    }

    public void setPassing(Integer passing) {
        this.passing = passing;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
