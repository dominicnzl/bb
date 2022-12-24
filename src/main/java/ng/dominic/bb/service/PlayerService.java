package ng.dominic.bb.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ng.dominic.bb.controller.datatable.Column;
import ng.dominic.bb.controller.datatable.Order;
import ng.dominic.bb.controller.datatable.Page;
import ng.dominic.bb.controller.datatable.PageArray;
import ng.dominic.bb.controller.datatable.PageRequest;
import ng.dominic.bb.controller.datatable.Search;
import ng.dominic.bb.model.Player;
import ng.dominic.bb.repos.PlayerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
@Transactional
public class PlayerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerService.class);

    private static final Comparator<Player> EMPTY_COMPARATOR = (e1, e2) -> 0;

    private final PlayerRepo playerRepo;

    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public List<Player> findAll() {
        return playerRepo.findAll();
    }

    public Optional<Player> findById(Long id) {
        return playerRepo.findById(id);
    }

    public Player save(Player player) {
        return playerRepo.save(player);
    }

    public Player update(Player player) {
        return playerRepo.save(player);
    }

    public void delete(Player player) {
        playerRepo.delete(player);
    }

    public PageArray getPlayersArray(PageRequest pageRequest) {
        pageRequest.setColumns(Stream.of(
                        "name",
                        "movementAllowance",
                        "strength",
                        "agility",
                        "armourValue",
                        "passing",
                        "price")
                .map(Column::new)
                .toList());
        Page<Player> playerPage = getPlayers(pageRequest);
        PageArray pageArray = new PageArray();
        pageArray.setRecordsFiltered(playerPage.getRecordsFiltered());
        pageArray.setRecordsTotal(playerPage.getRecordsTotal());
        pageArray.setDraw(playerPage.getDraw());
        pageArray.setData(playerPage.getData()
                .stream()
                .map(this::toStringList)
                .toList());
        return pageArray;
    }

    private List<String> toStringList(Player player) {
        return Arrays.asList(
                player.getName(),
                String.valueOf(player.getMovementAllowance()),
                String.valueOf(player.getStrength()),
                String.valueOf(player.getAgility()),
                String.valueOf(player.getPassing()),
                String.valueOf(player.getArmourValue()),
                String.valueOf(player.getPrice()));
    }

    public Page<Player> getPlayers(PageRequest pageRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Player> players = objectMapper.readValue(
                    getClass().getClassLoader().getResourceAsStream("players.json"),
                    new TypeReference<List<Player>>() {
                    });
            return getPage(players, pageRequest);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return new Page<>();
    }

    private Page<Player> getPage(List<Player> players, PageRequest pageRequest) {
        List<Player> filtered = players.stream()
                .filter(filterPlayers(pageRequest))
                .sorted(sortPlayers(pageRequest))
                .skip(pageRequest.getStart())
                .limit(pageRequest.getLength())
                .toList();
        long count = players.stream().filter(filterPlayers(pageRequest)).count();
        Page<Player> page = new Page<>(filtered);
        page.setRecordsTotal((int) count);
        page.setDraw(pageRequest.getDraw());
        return page;
    }

    // TODO: 24/12/2022 wat een rare manier om dit te doen
    private Predicate<Player> filterPlayers(PageRequest pageRequest) {
        var isSearchValueAbsent = Optional.ofNullable(pageRequest.getSearch())
                .map(Search::getValue)
                .isEmpty();
        if (isSearchValueAbsent) {
            return player -> true;
        }
        var value = pageRequest.getSearch().getValue();

        // andere stats nog te implementeren
        return player -> player.getName().toLowerCase().contains(value)
                || String.valueOf(player.getPrice()).toLowerCase().contains(value);
    }

    /*    private Comparator<Employee> sortEmployees(PagingRequest pagingRequest) {
        try {
            Order order = pagingRequest.getOrder()
                                       .get(0);

            int columnIndex = order.getColumn();
            Column column = pagingRequest.getColumns()
                                         .get(columnIndex);

            Comparator<Employee> comparator = EmployeeComparators.getComparator(column.getData(), order.getDir());
            if (comparator == null) {
                return EMPTY_COMPARATOR;
            }

            return comparator;

        }
    }
     */
    private Comparator<Player> sortPlayers(PageRequest pageRequest) {
        if (pageRequest.getOrder() == null) {
            return EMPTY_COMPARATOR;
        }
        try {
            Order order = pageRequest.getOrder().get(0);
            int columnIndex = order.getColumn();
            Column column = pageRequest.getColumns().get(columnIndex);

//            Comparator<Player> comparator = P
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return EMPTY_COMPARATOR;
    }
}
