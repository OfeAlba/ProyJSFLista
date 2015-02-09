package es.open4job.aytozgz.opendata.model.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConsultaOrcl{
	
 private Statement stmt;
 public ResultSet resultado;
 private PrintWriter pw = null;
 private StringWriter sw= null;
 
 //Esta sirve para conectarse a la base de Datos
 public Connection Conexion() throws ClassNotFoundException, NamingException, SQLException
 {
	 /*  AHORA USAREMOS EL POOL DE CONEXIONES */
	 Context initContext = new InitialContext();
	 Context envContext  = (Context)initContext.lookup("java:/comp/env");
	 DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
	 Connection con = ds.getConnection();
	 return con;
	  
  } 

  public ResultSet Consulta(Connection con, String consulta)
  {
	  ResultSet resultado= null;
	  try
	  {
		  stmt = (Statement) con.createStatement();
		  resultado = (ResultSet) stmt.executeQuery(consulta);
	  }
	  catch (SQLException e )
	  {
	    sw = new StringWriter();
	    pw = new PrintWriter(sw);
	    e.printStackTrace(pw);
	  }
	  return resultado;
  }
 
  public void CerrarConexion(Connection con)
  {
	   try
	   {
	     stmt.close();
	     con.close();
	   }
	   catch (SQLException e )
	   {
	    sw = new StringWriter();
	    pw = new PrintWriter(sw);
	    e.printStackTrace(pw);
	    //return "NO FUNCIONA" + sw.toString() + "rn";
	    }
  }
	
 
}