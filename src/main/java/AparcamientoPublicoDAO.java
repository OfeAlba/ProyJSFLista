import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.annotation.*;

import es.open4job.aytozgz.opendata.model.dao.AparcamientosPublicosDAO;
import es.open4job.aytozgz.opendata.model.vo.AparcamientosPublicosVO;
import es.open4job.aytozgz.opendata.model.vo.Punto;


@ManagedBean
@SessionScoped
public class AparcamientoPublicoDAO implements Serializable{
 
	private static final long serialVersionUID = 1L;
	public static Logger logger = Logger.getLogger(AparcamientosPublicosDAO.class.getName());

	//resource injection
	@Resource(name="jdbc/myoracle")
	private DataSource ds;
 
	//if resource injection is not support, you still can get it manually.
	public AparcamientoPublicoDAO()
	{
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
 
	}
	
	public List<AparcamientosPublicosVO> getListadoAparcamientos() throws ClassNotFoundException, NamingException, SQLException 
	{
		List<AparcamientosPublicosVO> aparcamientos = new ArrayList<AparcamientosPublicosVO>();
		ResultSet rs=null;
		PreparedStatement st=null;
		
		/*ESTA PARTE SE HACE ASI PORQUE LOS JSF HACEN TODA LA CONEXION*/
		if(ds==null)
			throw new SQLException("No puedo obtener la conexión");
 
		//get database connection
		Connection con = ds.getConnection();
			
		try{
			//Abrimos la conexion 
			String query= "SELECT * FROM EQ4_APARCA";

			st= con.prepareStatement(query);
			rs= st.executeQuery();
			
			Punto punto = null;
			
			while (rs.next())
			{
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
			}//while
		}//try
		
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
	}//método
}
