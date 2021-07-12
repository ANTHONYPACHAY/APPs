/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apis;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author tonyp
 */
public interface MetodosRmiInterface extends Remote {

    public boolean saveImg(String base64, String absPath) throws RemoteException;

    public String getImg(String absPath) throws RemoteException;
}
