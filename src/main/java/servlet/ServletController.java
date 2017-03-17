package servlet;

import api.Service;
import service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;


/**
 * Class description
 *
 * @author lobseer on 30.01.2017.
 */


public class ServletController extends HttpServlet {
    private Service mainService;

    private void loadCountries() throws Exception {
        //SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        //Schema schema = sf.newSchema(new File("src/main/resources/world.xsd"));
        File xml = new File(getServletContext().getRealPath("WEB-INF/classes/world.xml"));
        JAXBContext jxc = JAXBContext.newInstance(ServiceImpl.class);

        Unmarshaller unmarshaller = jxc.createUnmarshaller();

        mainService = ((ServiceImpl) unmarshaller.unmarshal(xml));
        /*unmarshaller.setSchema(schema);
        unmarshaller.setEventHandler(
                event -> {
                    System.out.println("\nEVENT");
                    System.out.println("SEVERITY:  " + event.getSeverity());
                    System.out.println("MESSAGE:  " + event.getMessage());
                    System.out.println("LINKED EXCEPTION:  " + event.getLinkedException());
                    System.out.println("LOCATOR");
                    System.out.println("    LINE NUMBER:  " + event.getLocator().getLineNumber());
                    System.out.println("    COLUMN NUMBER:  " + event.getLocator().getColumnNumber());
                    System.out.println("    OFFSET:  " + event.getLocator().getOffset());
                    System.out.println("    OBJECT:  " + event.getLocator().getObject());
                    System.out.println("    NODE:  " + event.getLocator().getNode());
                    System.out.println("    URL:  " + event.getLocator().getURL());
                    return true;
                }
        );*/
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            loadCountries();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
