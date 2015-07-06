/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingg.balldraw_core.balldraw.domain;

import com.ingg.balldraw_core.balldraw.logic.algorithm.BallDrawAlgorithmInterface.DrawAlgorithm;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BallDraw {

    private final Date creationDate;
    private final Integer pool;
    private final List<Integer> draw;
    private final DrawAlgorithm algorithm;

    public BallDraw(Integer pool, List<Integer> draw, DrawAlgorithm algorithm) {
        this.creationDate = Calendar.getInstance().getTime();
        this.pool = pool;
        this.draw = draw;
        this.algorithm = algorithm;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Integer getPool() {
        return pool;
    }

    public List<Integer> getDraw() {
        return draw;
    }

    public DrawAlgorithm getAlgorithm() {
        return algorithm;
    }

    @Override
    public String toString() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

        return "BallDraw ["
                + (creationDate != null ? "creationDate=" + sdf.format(creationDate.getTime()) + ", "
                        : "") + (pool != null ? "pool=" + pool + ", " : "")
                + (draw != null ? "size=" + draw.size() : "") + "]"
                + (draw != null ? "draw=" + draw : "") + "]"
                + (algorithm != null ? "algorithm=" + algorithm : "") + "]";
    }

}
