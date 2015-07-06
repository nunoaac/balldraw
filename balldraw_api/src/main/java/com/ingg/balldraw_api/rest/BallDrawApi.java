/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingg.balldraw_api.rest;

import com.google.gson.Gson;
import com.ingg.balldraw_core.balldraw.domain.BallDraw;
import com.ingg.balldraw_core.balldraw.logic.generator.ManualBallDraw;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author support
 */
@Path("/draw")
public class BallDrawApi {
    
    @GET
    @Produces("application/json")
    public String generateDraw() {
        
        BallDraw newDraw = ManualBallDraw.getBallDraw(50,20, ManualBallDraw.DrawAlgorithm.SIMPLERANDOM); 
        
        Gson gson = new Gson();
        return gson.toJson(newDraw);
    }
    
}
