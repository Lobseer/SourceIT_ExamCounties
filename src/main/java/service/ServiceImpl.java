package service;

import api.Country;
import api.Service;
import api.State;
import impl.CountryImpl;
import org.apache.log4j.Logger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class description
 *
 * @author lobseer on 29.01.2017.
 */

@XmlRootElement(name = "World")
public class ServiceImpl implements Service {
    private static final Logger log = Logger.getLogger(ServiceImpl.class);
    private static final double CROWDED = 50d;

    @XmlElements(
            @XmlElement(name = "country", type = CountryImpl.class)
    )
    public List<Country> world;

    public ServiceImpl() {
    }

    public ServiceImpl(List<Country> world) {
        this.world = world;
    }

    public static <T> String listToString(List<T> toStr, Function<T, String> get) {
        StringBuilder result = new StringBuilder();
        if (toStr != null && toStr.size() > 0) {
            for (int i = 0; i < toStr.size() - 1; i++) {
                result.append(get.apply(toStr.get(i)));
                result.append(", ");
            }
            result.append(get.apply(toStr.get(toStr.size() - 1)));
        }
        return result.toString();
    }

    Country getCountry(String name) {
        return world.stream().filter((p) -> p.getName().equals(name)).findAny().orElse(null);
    }

    @Override
    public Long getCountryPopulation(String name) {
        log.debug(String.format("Param: Name=%s;", name));
        Country temp = getCountry(name);
        Long result = temp == null ? null : temp.getPopulation();
        log.debug(String.format("Result: Population=%d;", result));
        return result;
    }

    @Override
    public BigDecimal getCountrySquare(String name) {
        log.debug(String.format("Param: Name=%s;", name));
        BigDecimal result = getCountry(name).getSquare();
        log.debug(String.format("Result: Square=%.4f;", result));
        return result;
    }

    @Override
    public List<Country> getOvercrowdedCountry() {
        List<Country> result = world.stream().filter((p) -> (p.getPopulation() / p.getSquare().doubleValue()) > CROWDED).collect(Collectors.toList());
        log.debug(String.format("Result: %s;", listToString(result, Country::getName)));
        return result;
    }

    @Override
    public BigDecimal getTotalDensityOfPeople(String name) {
        log.debug(String.format("Param: Name=%s;", name));
        Country temp = getCountry(name);
        BigDecimal result = new BigDecimal(temp.getPopulation() / temp.getSquare().doubleValue());
        log.debug(String.format("Result: TotalDensityOfPeople=%.4f;", result));
        return result;
    }

    @Override
    public BigDecimal getAverageDensityOfPeopleByStates(String name) {
        log.debug(String.format("Param: Name=%s", name));
        Country temp = getCountry(name);
        BigDecimal result = new BigDecimal((temp.getPopulation() / temp.getSquare().doubleValue()) / temp.getStates().size());
        log.debug(String.format("Result: AverageDensityOfPeopleByStates=%.4f;", result));
        return result;
    }

    @Override
    public List<State> getOvercrowdedStates(String name) {
        log.debug(String.format("Param: Name=%s", name));
        Country temp = getCountry(name);
        List<State> result = temp.getStates().stream().filter((p) -> (p.getPopulation() / p.getSquare().doubleValue()) > CROWDED).collect(Collectors.toList());
        log.debug(String.format("Result: %s;", listToString(result, State::getName)));
        return result;
    }

    @Override
    public State getBiggestState(String name) {
        log.debug(String.format("Param: Name=%s;", name));
        Country temp = getCountry(name);
        State result = temp.getStates().stream().max((p1, p2) -> p1.getSquare().max(p2.getSquare()) == p1 ? 1 : -1).orElse(null);
        log.debug(String.format("Result: State=%s;", result));
        return result;
    }
}
