/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import util.DataStatic;

/**
 *
 * @author tonyp
 */
public class ServidorRmi {

    public static void main(String[] args) {
        try {
            
            Registry reg = LocateRegistry.createRegistry(DataStatic.port);
            reg.rebind(DataStatic.resources, new MethodsRmi());
            System.out.println("servidor RMI en línea");
            
        } catch (Exception e) {
            System.out.println("Error RMI server:" + e.getMessage());
        }
    }
}
