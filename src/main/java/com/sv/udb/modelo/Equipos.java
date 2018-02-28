/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mauricio González
 */
public class Equipos {
    private int cod;
    private String nomb, desc;

    /**
    * Constructor, no olvidar crear el objeto de las listas
    * en ambos constructores
    *
    * @param nomb El nombre del equipo
    */
    public Equipos() {
    }

    public Equipos(int cod,String nomb, String desc) {
        this.cod = cod;
        this.nomb = nomb;
        this.desc = desc;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNomb() {
        return nomb;
    }

    public void setNomb(String nomb) {
        this.nomb = nomb;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    @Override
    public String toString() {
        return nomb;
    }    
}
