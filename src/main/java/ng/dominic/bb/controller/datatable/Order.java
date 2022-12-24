package ng.dominic.bb.controller.datatable;

import java.util.Objects;

public class Order {

    private Integer column;
    private Direction dir;

    public Order() {
    }

    public Order(Integer column, Direction dir) {
        this.column = column;
        this.dir = dir;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!Objects.equals(column, order.column)) return false;
        return dir == order.dir;
    }

    @Override
    public int hashCode() {
        int result = column != null ? column.hashCode() : 0;
        result = 31 * result + (dir != null ? dir.hashCode() : 0);
        return result;
    }
}
