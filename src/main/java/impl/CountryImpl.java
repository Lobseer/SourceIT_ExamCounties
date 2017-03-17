package impl;

import api.Country;
import api.State;
import org.apache.log4j.Logger;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class description
 *
 * @author lobseer on 29.01.2017.
 */

@XmlRootElement(name = "country")
public class CountryImpl implements Country {
    private static final Logger log = Logger.getLogger(CountryImpl.class);
    @XmlAttribute
    private String name;
    @XmlAttribute
    private Long population;
    @XmlAttribute
    private BigDecimal square;

    @XmlElementWrapper(name = "states")
    @XmlElements(
            @XmlElement(name = "state", type = StateImpl.class)
    )
    private List<State> states;

    public CountryImpl() {
    }

    public CountryImpl(String name, List<State> states) {
        this.name = name;
        this.states = states;
        this.population = states.stream().mapToLong(State::getPopulation).sum();
        this.square = states.stream().map(State::getSquare).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < states.size() - 1; i++) {
            st.append(states.get(i).getName());
            st.append(", ");
        }
        st.append(states.get(states.size() - 1).getName());
        log.info(String.format("Create new country: Name=%s, Population=%d, Square=%.4f, States[%s];", name, population, square, st));
    }

    public boolean addNewState(StateImpl state) {
        if (state != null) {
            if (states.stream().noneMatch((p) -> p.getName().equals(state.getName()))) {
                states.add(state);
                population += state.getPopulation();
                square.add(state.getSquare());
                log.info(String.format("Add new state(Name=%s, Population=%d, Square=%.4f) into country %s;", state.getName(), state.getPopulation(), state.getSquare(), name));
                return true;
            }
        }
        log.warn(String.format("Failed adding new state into %s", name));
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
        return new ArrayList<>(states);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryImpl country = (CountryImpl) o;
        return Objects.equals(name, country.name) &&
                Objects.equals(population, country.population) &&
                Objects.equals(square, country.square) &&
                Objects.equals(states, country.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, population, square, states);
    }

    @Override
    public String toString() {
        return String.format("CountryImpl{ name=%s; population=%d; square=%.4f; states=%s;}",
                name, population, square, states);
    }
}
