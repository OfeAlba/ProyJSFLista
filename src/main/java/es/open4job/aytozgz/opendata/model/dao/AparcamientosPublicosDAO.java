package es.open4job.aytozgz.opendata.model.dao;

import es.open4job.aytozgz.opendata.model.vo.AparcamientosPublicosVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

import javax.naming.NamingException;

import es.open4job.aytozgz.opendata.model.vo.Punto;

public class AparcamientosPublicosDAO {

	ConsultaOrcl consulta = new ConsultaOrcl();

	public static Logger logger = Logger.getLogger(AparcamientosPublicosDAO.class.getName());

	// Listado de aparcamientos

	public List<AparcamientosPublicosVO> getListadoAparcamientos() throws ClassNotFoundException, NamingException, SQLException {
	List<AparcamientosPublicosVO> aparcamientos = new ArrayList<AparcamientosPublicosVO>();
		
		//String query = "SELECT * FROM EQ4_APARCA";
		Statement st = null;
		ResultSet rs = null;
		
		
		Connection con = consulta.Conexion();
		
		try{
		//Abrimos la conexion 
		String query= "SELECT * FROM EQ4_APARCA";

		PreparedStatement stmt= con.prepareStatement(query);
		rs= stmt.executeQuery();
		
		Punto punto = null;
		
		while (rs.next()){
			int id = rs.getInt(1);
			Date fecha = rs.getDate(2);
			String icon = rs.getString(3);
			String title = rs.getString(4);
			String horario = rs.getString(5);
			String accesoPeaton = rs.getString(6);
			String accesos = rs.getString(7);
			String accesoVehiculo = rs.getString(8);

			int x = rs.getInt(9);
			int y = rs.getInt(10);
				
			punto = new Punto(x, y);
			
				AparcamientosPublicosVO aparcamiento = new AparcamientosPublicosVO(punto, horario, title,
						accesos, fecha, accesoPeaton,accesoVehiculo, id);
				
				aparcamientos.add(aparcamiento);
		}
		
			
		}
			
		catch(Exception e){
			
		logger.log(Level.SEVERE, e.getMessage());
		
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					}
			}
			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
					
				}
			}
		}
		
	return aparcamientos;
	}

	public String getListadoAparcamientos(int id) throws ClassNotFoundException, NamingException, SQLException {
		String aparcamientos = null;
			
		//String query = "SELECT * FROM EQ4_APARCA";
		Statement st = null;
		ResultSet rs = null;
			
			
		Connection con = consulta.Conexion();
			
		try{
			//Abrimos la conexion 
			Punto punto = null;
			String query= "SELECT * FROM EQ4_APARCA WHERE ID=?";

			PreparedStatement stmt= con.prepareStatement(query);
			stmt.setInt(1, id );
			rs= stmt.executeQuery();
			
			while (rs.next()){
				Date fecha = rs.getDate(2);
				String icon = rs.getString(3);
				String title = rs.getString(4);
				String horario = rs.getString(5);
				String accesoPeaton = rs.getString(6);
				String accesos = rs.getString(7);
				String accesoVehiculo = rs.getString(8);

				int x = rs.getInt(9);
				int y = rs.getInt(10);
					
				punto = new Punto(x, y);
				
					AparcamientosPublicosVO aparcamiento = new AparcamientosPublicosVO(punto, horario, title,
							accesos, fecha, accesoPeaton,accesoVehiculo, id);
					
					return aparcamiento.toString();
			}
			
				
			}
				
			catch(Exception e){
				
			logger.log(Level.SEVERE, e.getMessage());
			
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (Exception e) {
						}
				}
				if (st != null) {
					try {
						st.close();
					} catch (Exception e) {
						
					}
				}
			}
			
		return aparcamientos;
		}
	private void Callable(Connection con)
	{
		try
		{
			//Ahora se llama un procedimiento almacenado en la base de Datos
			CallableStatement clstmt= con.prepareCall("{?=call NUMERO}");
			//Primero se asignan los tipos de valores que van en el retorno
			clstmt.registerOutParameter(1, java.sql.Types.INTEGER);
			//Luego se ejecuta la query
			clstmt.executeQuery();
			//Por último se recuperan los valores que regresa la funcion
			int entero= clstmt.getInt(1);
			//Se imprime en pantalla
			System.out.println(entero);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, e.getMessage());
			
		}
				
	}
	
	private void PrimeraQuery(Connection con)
	{
		try
		{
			String query2= "SELECT * FROM EQ4_APARCA WHERE ID=?";
			//Primera query	
			PreparedStatement stmt= con.prepareStatement(query2);
			stmt.setInt(1, 1);
			ResultSet rs= stmt.executeQuery();
			while (rs.next())
				System.out.println(rs.getString(1));
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, e.getMessage());
			
		}
		
	}
	
	private void SegundaQuery(Connection con)
	{
		try
		{
			String query= "INSERT INTO EQ4_APARCA (ID, LASTUPDATED, ICON, TITLE,HORARIO, ACCESOPEATON,ACCESOS, ACCESOVEHICULO, COORDX, COORDY) VALUES (?, ?, ?, ?,?, ?, ?, ?, ?, ?)";
			
			PreparedStatement stmt= con.prepareStatement(query);
			stmt.setInt(1, 10);
			java.sql.Date f= new java.sql.Date(2015, 02,16);
			stmt.setDate(2, f);
			stmt.setString(3,"'El iconico'");
			stmt.setString(4, "'las marias'");
			stmt.setString(5, "'24 horas'");
			stmt.setString(6, "'no'");
			stmt.setString(7, "'no'");
			stmt.setString(8, "'si'");
			stmt.setDouble(9, 10.25);
			stmt.setDouble(10, 45.2);
			
			
			int row= stmt.executeUpdate();
			
			if(row!= 0)
			{
				System.out.println("Se ha hecho la insercion");
			}
			
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, e.getMessage());
			
		}
	}
	private void AutoCommit(Connection con)
	{
		try
		{
			String query= "INSERT INTO EQ4_APARCA (ID) VALUES (?)";
			//Primera query	
			PreparedStatement stmt= con.prepareStatement(query);
			int num= (int) Math.round(Math.random()*100+5);
			stmt.setInt(1, num);
			int row= stmt.executeUpdate();
			
			if(row!= 0)
			{
				num= (int) Math.round(Math.random()*100+5);
				if(num%2 == 0)
				{
					int num2= (int) Math.round(Math.random()*100+5);
					stmt.setInt(1, num2);
					stmt.executeUpdate();
					con.commit();
					System.out.println("Se hizo el commit "+ num + " y " + num2);
				}
				else
				{
					con.rollback();
					System.out.println("Se hizo el rollback");
				}
			}
				
			
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, e.getMessage());
			
		}
		
	}

}
