package impl;

import api.State;

import java.math.BigDecimal;

/**
 * Class description
 *
 * @author lobseer on 29.01.2017.
 */

public class StateImpl implements State {
    private String name;
    private Long population;
    private BigDecimal square;

    public StateImpl() {}

    public StateImpl(String name, Long population, BigDecimal square) {
        this.name = name;
        this.population = population;
        this.square = square;
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
    public String toString() {
        return String.format("StateImpl{ name=%1s; population=%2s; square=%3s;}",
                name, population, square);
    }
}
