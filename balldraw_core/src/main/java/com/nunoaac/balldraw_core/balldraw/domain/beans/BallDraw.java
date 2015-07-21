package com.nunoaac.balldraw_core.balldraw.domain.beans;

import com.nunoaac.balldraw_core.balldraw.logic.algorithm.BallDrawAlgorithmInterface.DrawAlgorithm;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Represents a balldraw, with generation timestamp, UUID, Pool size, Draw
 * itself and algorithm used to generate the draw. This class is serializable
 * and will use BallDrawJpaDao to persist to the DB. The annotations are above
 * the getter methods because we want to use the getMethods to set objects to be
 * persisted to the DB and vice-versa This is due to the fact that we want to
 * generate the Draw itself while getting the values from the DB. DRAW and
 * DRAWSTRING means the same thing but in different representations When reading
 * from the DB, DRAW is generated through the DRAWSTRING (which is what is
 * persisted in the DB). When writing in the DB, DRAWSTRING is generated by the
 * DRAW list itself.
 *
 * @author Nuno Costa (nunoaac@msn.com)
 */
@Entity
@Access(value = AccessType.PROPERTY)          //this means that the JPA serialzier will use both getter and setter methods - we use this because we want to set the draw variable when reading the drawString var
public class BallDraw implements Serializable {

    private Date creationDate;
    private Integer pool;
    private List<Integer> draw;
    private String drawString;
    private DrawAlgorithm algorithm;
    private Integer size = 0;
    private String uid;
    private Client client;

    public BallDraw() {
    }

    public BallDraw(Integer pool, List<Integer> draw, DrawAlgorithm algorithm) {
        this.creationDate = Calendar.getInstance().getTime();
        this.pool = pool;
        this.draw = draw;
        this.size = draw.size();
        this.algorithm = algorithm;
        this.drawString = getDrawString();
        uid = UUID.randomUUID().toString();
    }

    @Column(name = "GENERATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreationDate() {
        return creationDate;
    }

    @Column(name = "POOL")
    public Integer getPool() {
        return pool;
    }

    @Transient
    public List<Integer> getDraw() {
        return draw;
    }

    @ManyToOne()
    @JoinColumn(nullable=false)
    public Client getClient() {
        return client;
    }

    @Column(name = "DRAW_STRING")
    public String getDrawString() {

        if ((this.drawString != null) && (this.drawString.length() > 0)) {
            return this.drawString;
        }

        this.drawString = "[";
        if (this.draw != null) {
            for (int ball : draw) {
                this.drawString = this.drawString.concat(ball + ",");
            }
            this.drawString = this.drawString.substring(0, this.drawString.length() - 1);  //remove last ','
        }

        this.drawString = this.drawString.concat("]");
        return drawString;
    }

    @Column(name = "ALGORITHM")
    @Enumerated(EnumType.STRING)        //to tell DB how he should persist this field (using the draw algorithm name, not his index on the enum)
    public DrawAlgorithm getAlgorithm() {
        return algorithm;
    }

    /**
     * Return ball draw size - Number of balls on the draw
     *
     * @return Number of ball draws generated
     */
    @Column(name = "DRAW_SIZE")
    public Integer getSize() {
        return this.size;
    }

    @Id
    public String getUid() {
        return this.uid;
    }

    public void setPool(Integer pool) {
        this.pool = pool;
    }

    public void setDraw(List<Integer> draw) {
        this.draw = draw;
    }

    public void setAlgorithm(DrawAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setDrawString(String drawString) {
        this.drawString = drawString;

        String auxString = drawString;
        if (draw == null) {
            draw = new ArrayList<Integer>();
            auxString = auxString.substring(1);
            auxString = auxString.substring(0, auxString.length() - 1);

            StringTokenizer tokenizer = new StringTokenizer(auxString, ",");
            while (tokenizer.hasMoreTokens()) {
                String stringBall = tokenizer.nextToken();
                draw.add(Integer.parseInt(stringBall));
            }
            this.size = draw.size();
        }
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

        return "BallDraw ["
                + (creationDate != null ? "creationDate=" + sdf.format(creationDate.getTime()) + ", "
                        : "") + (pool != null ? "pool=" + pool + ", " : "")
                + (uid != null ? "uid=" + uid : "") + ", "
                + (size != null ? "size=" + size : "") + ", "
                + (draw != null ? "draw=" + draw : "") + ", "
                + (drawString != null ? "drawString=" + this.getDrawString() : "") + ", "
                + (algorithm != null ? "algorithm=" + algorithm : "") + "]";
    }

}
