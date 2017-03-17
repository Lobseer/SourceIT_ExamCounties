package service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Class description.
 *
 * @author lobseer on 17.03.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        File xml = new File("src/main/resources/world.xml");
        JAXBContext jxc = JAXBContext.newInstance(ServiceImpl.class);
        Unmarshaller unmarshaller = jxc.createUnmarshaller();
        ServiceImpl mainService = ((ServiceImpl) unmarshaller.unmarshal(xml));
        mainService.world.forEach(System.out::println);
    }
}
