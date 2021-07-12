/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Usuario
 */
public class DataStatic {

    public static String nameApplication = "PizarraRMI";
    public static String privateKey = "AAAA";

    public static String host = "localhost";
    public static String resources = "apisdraw";
    public static int port = 1234;

    private static String fileLocation = "";

    private static String StringTarget = "";//
    private static String StringReplacement = "";

    public static String proyectName = "storageTddm4IoTbs/";

    public static String folderResources = "results/";

    public static String getLocation(String context) {
        if (!fileLocation.equals("")) {
            context = fileLocation;
        }
        return context.replace(StringTarget, StringReplacement);
    }
}
