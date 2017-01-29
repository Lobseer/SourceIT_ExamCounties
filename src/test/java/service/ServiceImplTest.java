package service;

import api.Country;
import api.State;
import impl.CountryImpl;
import impl.StateImpl;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class description
 *
 * @author lobseer on 29.01.2017.
 */

public class ServiceImplTest {
    List<Country> world = Arrays.asList(
            new CountryImpl("Ukraine", Arrays.asList(
                    new StateImpl("Kharkov", 88888L, new BigDecimal(2000)),
                    new StateImpl("Lvov", 10L, new BigDecimal(34563)),
                    new StateImpl("Kiev", 2313454L, new BigDecimal(242355))
            )),
            new CountryImpl("USA", Arrays.asList(
                    new StateImpl("California", 199999990L, new BigDecimal(1235)),
                    new StateImpl("Texas", 1332212L, new BigDecimal(6555))
            )),
            new CountryImpl("Russia", Arrays.asList(
                    new StateImpl("Moscow", 1241241L, new BigDecimal(3333423)),
                    new StateImpl("Peter", 511111L, new BigDecimal(201100))
            )),
            new CountryImpl("Monaco", Arrays.asList(
                    new StateImpl("Monaco", 37731L, new BigDecimal(2.02))
            ))
    );

    @Test
    public void getCountryTest() {
        ServiceImpl test = new ServiceImpl(world);
        Country expected = world.get(0);
        Country actual = test.getCountry("Ukraine");
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void getCountryNullNameTest() {
        ServiceImpl test = new ServiceImpl(world);
        test.getCountry(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void getCountryPopulationUnCorrectNameTest() {
        ServiceImpl test = new ServiceImpl(world);
        Country expected = null;
        Country actual = test.getCountry("Turkey");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCountryPopulationNormalTest() {
        ServiceImpl test = new ServiceImpl(world);
        Long expected = 1241241L + 511111L;
        Long actual = test.getCountryPopulation("Russia");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getOvercrowdedCountryTest() {
        ServiceImpl test = new ServiceImpl(world);
        List<Country> expected = Arrays.asList(world.get(1), world.get(3));
        List<Country> actual = test.getOvercrowdedCountry();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCountrySquareTest() {
        ServiceImpl test = new ServiceImpl(world);
        BigDecimal expected = new BigDecimal(3534523);
        BigDecimal actual = test.getCountrySquare("Russia");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTotalDensityOfPeopleTest() {
        ServiceImpl test = new ServiceImpl(world);
        //Country temp = world.get(0);
        //BigDecimal expected = new BigDecimal(temp.getPopulation() / temp.getSquare().doubleValue());
        BigDecimal expected = new BigDecimal(8.3262894470776357208928789106476);
        BigDecimal actual = test.getTotalDensityOfPeople("Ukraine");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAverageDensityOfPeopleByStatesTest() {
        ServiceImpl test = new ServiceImpl(world);
        //Country temp = world.get(0);
        //BigDecimal expected = new BigDecimal(temp.getPopulation() / temp.getSquare().doubleValue());
        BigDecimal expected = new BigDecimal(4.6636004621628933135155273864923);
        BigDecimal actual = test.getAverageDensityOfPeopleByStates("Ukraine");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getOvercrowdedState() {
        ServiceImpl test = new ServiceImpl(world);
        List<State> expected = Arrays.asList(world.get(1).getStates().get(0),world.get(1).getStates().get(1));
        List<State> actual = test.getOvercrowdedStates("Ukraine");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getBiggestStateTest() {
        ServiceImpl test = new ServiceImpl(world);
        State expected = world.get(0).getStates().get(2);
        State actual = test.getBiggestState("Ukraine");
        Assert.assertEquals(expected, actual);
    }
}