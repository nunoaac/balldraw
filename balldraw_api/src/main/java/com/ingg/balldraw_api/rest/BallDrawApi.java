/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingg.balldraw_api.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.ingg.balldraw_core.balldraw.domain.BallDraw;
import com.ingg.balldraw_core.balldraw.logic.algorithm.BallDrawAlgorithmInterface;
import com.ingg.balldraw_core.balldraw.logic.generator.ManualBallDraw;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author support
 */
@Path("/draw")
public class BallDrawApi {

    @GET
    @Produces("application/json")
    @Path("/fisheryates")
    public String generateFisherYatesDraw(
            @DefaultValue("50") @QueryParam("pool") int pool,
            @DefaultValue("20") @QueryParam("selection") int selection) {

        return generateJson(pool, selection, BallDrawAlgorithmInterface.DrawAlgorithm.FISHERYATES).toString();
    }

    @GET
    @Produces("application/json")
    @Path("/simplerandom")
    public String generateSimpleRandomDraw(
            @DefaultValue("50") @QueryParam("pool") int pool,
            @DefaultValue("20") @QueryParam("selection") int selection) {

        return generateJson(pool, selection, BallDrawAlgorithmInterface.DrawAlgorithm.SIMPLERANDOM).toString();

    }

    /**
     * Generate a JSON that reflects the generated Ball Draw object. It makes a
     * regular serialization of the object vars and adds an extra field, which
     * is ball draw size. Ball draw size is generated from the list size and is
     * injected on the final JSON.
     *
     * @param pool Pool max value of the ball draw
     * @param selection Number of values of the ball draw
     * @param drawAlgorithm Algorithm of the ball draw
     * @return - JSON of the generated ball draw, as string.
     */
    private JsonElement generateJson(int pool, int selection, BallDrawAlgorithmInterface.DrawAlgorithm drawAlgorithm) {

        BallDraw newDraw = ManualBallDraw.getBallDraw(pool, selection, drawAlgorithm);

        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm:ss zzz").create();
        JsonElement jsonElement = gson.toJsonTree(newDraw);
        jsonElement.getAsJsonObject().addProperty("size", newDraw.getDraw().size());

        return jsonElement;
    }

}
