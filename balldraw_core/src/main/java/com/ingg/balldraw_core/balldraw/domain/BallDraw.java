/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingg.balldraw_core.balldraw.domain;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class BallDraw {

	private final Calendar creationDate;
	private final Integer pool;
	private final List<Integer> draw;
		
	public BallDraw(Integer pool, List<Integer> draw) {
		this.creationDate = Calendar.getInstance();
		this.pool = pool;
		this.draw = draw;
	}
	public Calendar getCreationDate() {
		return creationDate;
	}
	public Integer getPool() {
		return pool;
	}
	public List<Integer> getDraw() {
		return draw;
	}
	
	@Override
	public String toString() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		
		return "BallDraw ["
				+ (creationDate != null ? "creationDate=" + sdf.format(creationDate.getTime()) + ", "
						: "") + (pool != null ? "pool=" + pool + ", " : "")
				+ (draw != null ? "size=" + draw.size() : "") + "]"
				+ (draw != null ? "draw=" + draw : "") + "]";
	}
	
	
}
