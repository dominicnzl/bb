package ng.dominic.bb.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "seqGen", allocationSize = 1)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private int movementAllowance;

    @Column
    private int strength;

    @Column
    private Integer agility;

    @Column
    private Integer armourValue;

    @Column
    private Integer passing;

    @Column
    private int price;

    public Player() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setArmourValue(Integer armorValue) {
        this.armourValue = armorValue;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return movementAllowance == player.movementAllowance &&
                strength == player.strength &&
                agility.equals(player.agility) &&
                armourValue.equals(player.armourValue) &&
                passing.equals(player.passing) &&
                price == player.price &&
                id.equals(player.id) &&
                name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, movementAllowance, strength, agility, armourValue, passing, price);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", movementAllowance=" + movementAllowance +
                ", strength=" + strength +
                ", agility=" + agility +
                ", armorValue=" + armourValue +
                ", passing=" + passing +
                ", price=" + price +
                '}';
    }
}
