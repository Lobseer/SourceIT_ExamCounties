package impl;

import api.Country;
import api.State;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class description
 *
 * @author lobseer on 29.01.2017.
 */

public class CountryImpl implements Country {
    private String name;
    private Long population;
    private BigDecimal square;
    private List<State> states;

    public CountryImpl() {}

    public CountryImpl(String name, List<State> states) {
        this.name = name;
        this.states = states;
        this.population = states.stream().mapToLong(State::getPopulation).sum();
        this.square = states.stream().map(State::getSquare).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    public boolean addNewState(State state) {
        if(state!=null) {
            if(states.stream().noneMatch((p)->p.getName().equals(state.getName()))) {
                states.add(state);
                population+=state.getPopulation();
                square.add(state.getSquare());
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Long getPopulation() {
        return population;
    }

    @Override
    public BigDecimal getSquare() {
        return square;
    }

    @Override
    public List<State> getStates() {
        return states;
    }

    @Override
    public String toString() {
        return String.format("CountryImpl{ name=%1s; population=%2s; square=%3s; states=%4s;}",
                name, population, square, states);
    }
}
