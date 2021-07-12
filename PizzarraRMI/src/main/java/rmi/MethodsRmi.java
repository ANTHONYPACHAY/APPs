/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import Apis.MetodosRmiInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import util.DataStatic;
import util.FileAccess;
import util.WeEncoder;

/**
 *
 * @author tonyp
 */
public class MethodsRmi extends UnicastRemoteObject implements MetodosRmiInterface {

    public MethodsRmi() throws RemoteException {
        super();
    }

    @Override
    public boolean saveImg(String base64, String absPath) {
        System.out.println("estoy en RMI Metodo Post IMH...............");
        System.out.println("x: " + absPath);
        try {
            Thread th = new Thread(() -> {
                String ruta = absPath + DataStatic.folderResources;

                WeEncoder we = new WeEncoder();
                FileAccess fac = new FileAccess();
                ruta += we.getUrlGeneric() + ".png";
                boolean resp = fac.SaveImg(base64, ruta);
                System.out.println("Guargar: " + resp);
            });
            th.start();
            return true;
        } catch (Exception e) {
            System.out.println("RmiERROR: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String getImg(String absPath) {
        System.out.println("estoy en RMI Metodo getIMH...............");
        try {
            String ruta = absPath + DataStatic.folderResources;
            FileAccess fac = new FileAccess();
            String resp = fac.getFileNames(ruta, DataStatic.folderResources, "");
            return resp;
        } catch (Exception e) {
            System.out.println("RmiERROR: " + e.getMessage());
            return "[]";
        }
    }

}
