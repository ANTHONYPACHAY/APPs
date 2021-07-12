/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apis;

import com.google.gson.JsonObject;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import util.DataStatic;
import util.FileAccess;
import util.Methods;
import util.WeEncoder;

/**
 * REST Web Service
 *
 * @author tonyp
 */
@Path("MethodsRMI")
public class ApisDraw {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;

    /**
     * Creates a new instance of ApisDraw
     */
    public ApisDraw() {
    }

    /**
     * Retrieves representation of an instance of Apis.ApisDraw
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getImg() {
        String message;
        System.out.println("getImg....");
        try {
            Registry mireg = LocateRegistry.getRegistry(DataStatic.host, DataStatic.port);
            MetodosRmiInterface instancia = (MetodosRmiInterface) mireg.lookup(DataStatic.resources);

            String mepath = DataStatic.getLocation(request.getServletContext().getRealPath(""));
            String resp = instancia.getImg(mepath);
            if (!resp.equals("[]")) {
                message = Methods.getJsonMessage("2", "Data loaded correctly.", resp);
            } else {
                message = Methods.getJsonMessage("4", "Missing data.", "[]");
            }
        } catch (Exception ex) {
            System.out.println("error get:" + ex.getMessage());
            message = Methods.getJsonMessage("4", "An error has occurred on the RMI server.", "[]");
            ex.printStackTrace();
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response saveDraw(String data) {
        String message;
        System.out.println("saveDraw...");
        JsonObject jso = Methods.stringToJSON(data);
        if (jso.size() > 0) {

            String base = Methods.JsonToString(jso, "base64", "");
            try {
                Registry mireg = LocateRegistry.getRegistry(DataStatic.host, DataStatic.port);
                MetodosRmiInterface instancia = (MetodosRmiInterface) mireg.lookup(DataStatic.resources);

                String mepath = DataStatic.getLocation(request.getServletContext().getRealPath(""));
                if (instancia.saveImg(base, mepath)) {
                    message = Methods.getJsonMessage("2", "Data saved correctly.", "[]");
                } else {
                    message = Methods.getJsonMessage("4", "No records found.", "[]");
                }
            } catch (Exception ex) {
                System.out.println("error post:" + ex.getMessage());
                message = Methods.getJsonMessage("4", "An error has occurred on the RMI server.", "[]");
                ex.printStackTrace();
            }
        } else {
            message = Methods.getJsonMessage("4", "Missing data.", "[]");
        }
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
}
