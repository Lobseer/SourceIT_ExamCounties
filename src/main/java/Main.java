import api.Country;
import api.Service;
import impl.CountryImpl;
import impl.StateImpl;
import service.ServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Class description
 *
 * @author Lobseer on 21.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        List<Country> world = Arrays.asList(
                new CountryImpl("UA", Arrays.asList(
                        new StateImpl("Kh", 88888L, new BigDecimal(2000)),
                        new StateImpl("Lv", 10L, new BigDecimal(3456))
                )),
                new CountryImpl("USA", Arrays.asList(
                        new StateImpl("Cal", 100000L, new BigDecimal(1235)),
                        new StateImpl("Tex", 1332212L, new BigDecimal(6555))
                )),
                new CountryImpl("Rus", Arrays.asList(
                        new StateImpl("Mos", 1241241L, new BigDecimal(33333)),
                        new StateImpl("Pet", 511111L, new BigDecimal(201100))
                ))
        );
        Service mainService = new ServiceImpl(world);

    }
}

