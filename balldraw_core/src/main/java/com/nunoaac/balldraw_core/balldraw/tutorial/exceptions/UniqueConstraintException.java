/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nunoaac.balldraw_core.balldraw.tutorial.exceptions;

/**
 *
 * @author support
 */
public class UniqueConstraintException extends Exception {

    /**
     * Creates a new instance of <code>UniqueConstraintException</code> without
     * detail message.
     */
    public UniqueConstraintException(String msg, Exception ex) {
        super(msg, ex);
    }

    /**
     * Constructs an instance of <code>UniqueConstraintException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UniqueConstraintException(String msg) {
        super(msg);
    }
}
