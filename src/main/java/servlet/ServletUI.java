package servlet;

import api.Country;
import api.Service;
import impl.CountryImpl;
import impl.StateImpl;
import service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Class description
 *
 * @author lobseer on 30.01.2017.
 */

public class ServletUI extends HttpServlet {
    private Service mainService;

    @Override
    public void init() throws ServletException {
        super.init();
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
        mainService = new ServiceImpl(world);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        String result = null;
        switch (query) {
            case "getBiggestState":
                result = mainService.getBiggestState(request.getParameter("country")).toString();
                break;
            case "getCountryPopulation":
                result = mainService.getCountryPopulation(request.getParameter("country")).toString();
                break;
            case "getCountrySquare":
                result = mainService.getCountrySquare(request.getParameter("country")).toString();
                break;
            case "getTotalDensityOfPeople":
                result = mainService.getTotalDensityOfPeople(request.getParameter("country")).toString();
                break;
            case "getAverageDensityOfPeopleByStates":
                result = mainService.getAverageDensityOfPeopleByStates(request.getParameter("country")).toString();
                break;
            case "getOvercrowdedStates":
                result = mainService.getOvercrowdedStates(request.getParameter("country")).toString();
                break;
            default:
                getServletContext().getRequestDispatcher("/MainForm.jsp");
                break;
        }
        request.setAttribute("result", result);
        getServletContext().getRequestDispatcher("/Result.jsp").forward(request, response);

        //req.getSession().setAttribute("name", "rrrrrr");
        //resp.sendRedirect("Result.jsp");
    }

}
