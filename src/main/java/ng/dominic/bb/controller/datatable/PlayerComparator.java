package ng.dominic.bb.controller.datatable;

import ng.dominic.bb.model.Player;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class PlayerComparator {

    private PlayerComparator() {
    }

    static Map<Key, Comparator<Player>> map = new HashMap<>();

    static {
        map.put(new Key("name", Direction.ASC), Comparator.comparing(Player::getName));
        map.put(new Key("name", Direction.DESC), Comparator.comparing(Player::getName, Comparator.reverseOrder()));
        // TODO: 24/12/2022 uitbreiden met andere kolommen 
    }

    public static Comparator<Player> getComparator(String name, Direction direction) {
        return map.get(new Key(name, direction));
    }

    static class Key {
        String name;
        Direction direction;

        public Key(String name, Direction direction) {
            this.name = name;
            this.direction = direction;
        }

        public String getName() {
            return name;
        }

        public Direction getDirection() {
            return direction;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (!Objects.equals(name, key.name)) return false;
            return direction == key.direction;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (direction != null ? direction.hashCode() : 0);
            return result;
        }
    }
}
