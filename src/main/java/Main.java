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
                        new StateImpl("Moscow", 1241241L, new BigDecimal(33423)),
                        new StateImpl("Peter", 511111L, new BigDecimal(20100))
                )),
                new CountryImpl("Monaco", Arrays.asList(
                        new StateImpl("Monaco", 37731L, new BigDecimal(2.02))
                ))
        );
        Service mainService = new ServiceImpl(world);

    }
}

