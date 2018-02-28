/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Equipos;
import com.sv.udb.modelo.Jugadores;
import com.sv.udb.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dante
 */
public class JugadorCtrl {

    private final Connection conn;
    public JugadorCtrl() {
        this.conn = new Conexion().getConn();
    }
    
    public boolean guar(Equipos codiEqui, String nombJuga, int edadJuga, int altuJuga, int pesoJuga)
    {
        boolean resp = false;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO jugadores VALUES(NULL,?,?,?,?,?)");
            cmd.setInt(1, codiEqui.getCod());
            cmd.setString(2, nombJuga);
            cmd.setInt(3, edadJuga);
            cmd.setInt(4, altuJuga);
            cmd.setInt(5, pesoJuga);
            cmd.executeUpdate();
            resp=true;
        }
        catch (SQLException ex)
        {
            System.err.println("Error al guardar Equipos: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException ex)
            {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
    
    public boolean actu(int codJ, Equipos codiEqui, String nombJuga, int edadJuga, int altuJuga, int pesoJuga)
    {
        boolean resp = false;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("UPDATE jugadores SET codi_equi=?, nomb_juga=?, edad_juga=? "+
                    ", altu_juga=?, peso_juga=? WHERE codi_juga=?");
            cmd.setInt(1, codiEqui.getCod());
            cmd.setString(2, nombJuga);
            cmd.setInt(3, edadJuga);
            cmd.setInt(4, altuJuga);
            cmd.setInt(5, pesoJuga);
            cmd.setInt(6, codJ);
            cmd.executeUpdate();
            resp = true;
        }
        catch(Exception e)
        {
            System.err.println("Error al actualizar Jugador: " + e.getMessage());
        }
        finally
        {
            try {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return resp;
    }
    
    public boolean elim(int codJ)
    {
        boolean resp = false;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("DELETE FROM jugadores WHERE codi_juga=?");
            cmd.setInt(1, codJ);
            cmd.executeUpdate();
            resp = true;
        }
        catch(Exception e)
        {
            System.err.println("Error al eliminar el Jugador: " + e.getMessage());
        }
        finally
        {
            try {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return resp;
    }
    
    public List<Jugadores> consTodo()
    {
       List<Jugadores> resp = new ArrayList<>();
        try
        {
            PreparedStatement cmd = conn.prepareStatement("SELECT j.codi_juga, e.*, j.nomb_juga, j.edad_juga, j.altu_juga, " +
                                            " j.peso_juga FROM jugadores j INNER JOIN equipos e ON j.codi_equi = e.codi_equi");
            ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp.add(new Jugadores(rs.getInt(1),new Equipos(rs.getInt(2),rs.getString(3),rs.getString(4)),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8)) );
            }
        }
        catch(SQLException ex)
        {
            System.err.println("Error al consultar Jugadores: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException ex)
            {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
    
}
