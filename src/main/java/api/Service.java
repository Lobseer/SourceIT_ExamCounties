package api;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class description
 * @author Lobseer on 21.10.2016.
 */
public interface Service {
    Long getCountryPopulation(String name);
    List<Country> getOvercrowdedCountry();
    BigDecimal getCountrySquare(String name);
    BigDecimal getTotalDensityOfPeople(String name);
    BigDecimal getAverageDensityOfPeopleByStates(String name);
    List<State> getOvercrowdedStates(String name);
    State getBiggestState(String name);
}
