package ro.teamnet.zth.api.database;

/**
 * Created by Raluca.Russindilar on 02.07.2016.
 */
public interface DBProperties {
    //TODO de inlocuit cu IP-ul din Docker
<<<<<<< HEAD
    String IP = "~/test";
    String PORT = "8095";
    //TODO de inlocuit cu utilizatorul vostru
    String USER = "admin";
    //TODO de inlocuit cu parola voastra
    String PASS = "admin";
    String SID = "xe";
    String DRIVER_CLASS = "org.h2.Driver";
=======
    String IP = "192.168.99.100";
    String PORT = "49161";
    //TODO de inlocuit cu utilizatorul vostru
    String USER = "cclaudiu";
    //TODO de inlocuit cu parola voastra
    String PASS = "cclaudiu";
    String SID = "xe";
    String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
>>>>>>> 855c1a6880e16f18104918fdd2e8cbca3602e0f4

}
