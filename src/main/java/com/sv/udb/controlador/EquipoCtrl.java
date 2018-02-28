/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Equipos;
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
public class EquipoCtrl {
    private final Connection conn;
    public EquipoCtrl()
    {
        this.conn = new Conexion().getConn();
    }
    
    public boolean guar(String nomb, String desc)
    {
        boolean resp = false;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO equipos VALUES(NULL,?,?)");
            cmd.setString(1, nomb);
            cmd.setString(2, desc);
            cmd.executeUpdate();
            resp = true;
        }
        catch(Exception e)
        {
            System.err.println("Error al guardar Equipos: " + e.getMessage());
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
    
    public boolean actu(String nomb, String desc, int cod)
    {
        boolean resp = false;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("UPDATE equipos SET nomb_equi=? , desc_equi=? WHERE codi_equi=?");
            cmd.setString(1, nomb);
            cmd.setString(2, desc);
            cmd.setInt(3, cod);
            cmd.executeUpdate();
            resp = true;
        }
        catch(Exception e)
        {
            System.err.println("Error al actualizar Equipos: " + e.getMessage());
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
    
    public boolean elim(int cod)
    {
        boolean resp = false;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("DELETE FROM equipos WHERE codi_equi=?");
            cmd.setInt(1, cod);
            cmd.executeUpdate();
            resp = true;
        }
        catch(Exception e)
        {
            System.err.println("Error al eliminar el Equipo: " + e.getMessage());
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
    
    public boolean Verif(int codEqui)
    {
        boolean resp = true;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM jugadores WHERE codi_equi=?");
            cmd.setInt(1, codEqui);
            ResultSet rs = cmd.executeQuery();
            System.out.println(rs.first());
            //si el query no devuelve algun registro se envia un resp = false
            if(rs.first() == true)
            {
                resp = false;
            }
            
        }
        catch(SQLException ex)
        {
            System.err.println("Error al verificar: " + ex.getMessage());
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
    
    public List<Equipos> consTodo()
    {
       List<Equipos> resp = new ArrayList<>();
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM equipos");
            ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp.add(new Equipos(rs.getInt(1),rs.getString(2), rs.getString(3)));
            }
        }
        catch(SQLException ex)
        {
            System.err.println("Error al consultar Equipos: " + ex.getMessage());
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
