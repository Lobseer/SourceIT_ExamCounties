package api;

import java.math.BigDecimal;
import java.util.List;
/**
 * Class description
 * @author Lobseer on 21.10.2016.
 */
public interface Country {
        String getName();
        Long getPopulation();
        BigDecimal getSquare();
        List<State> getStates();
}

