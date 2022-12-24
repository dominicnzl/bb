package ng.dominic.bb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "player")
@SequenceGenerator(name = "seqGen", allocationSize = 1)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private Integer movementAllowance;

    @Column
    private Integer strength;

    @Column
    private Integer agility;

    @Column
    private Integer armourValue;

    @Column
    private Integer passing;

    @Column
    private Integer price;

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

    public Integer getMovementAllowance() {
        return movementAllowance;
    }

    public void setMovementAllowance(Integer movementAllowance) {
        this.movementAllowance = movementAllowance;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id.equals(player.id);
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
