package ro.teamnet.zth.api.database;

/**
 * Created by Raluca.Russindilar on 02.07.2016.
 */
public interface DBProperties {
    //TODO de inlocuit cu IP-ul din Docker
    String IP = "~/test";
    String PORT = "8095";
    //TODO de inlocuit cu utilizatorul vostru
    String USER = "admin";
    //TODO de inlocuit cu parola voastra
    String PASS = "admin";
    String SID = "xe";
    String DRIVER_CLASS = "org.h2.Driver";

}
