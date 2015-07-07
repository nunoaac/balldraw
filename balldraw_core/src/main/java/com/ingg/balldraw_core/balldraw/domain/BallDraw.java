package com.ingg.balldraw_core.balldraw.domain;

import com.ingg.balldraw_core.balldraw.logic.algorithm.BallDrawAlgorithmInterface.DrawAlgorithm;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Represents a balldraw, with generation timestamp, Pool size, Draw itself and
 * algorithm used to generate the draw.
 *
 * @author Nuno Costa (nunoaac@msn.com)
 */
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

    /**
     * Return ball draw size - Number of balls on the draw
     *
     * @return Number of ball draws generated
     */
    public int getSize() {
        return this.draw.size();
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
