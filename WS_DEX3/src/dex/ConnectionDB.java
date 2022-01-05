package dex;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.*;
import dex.Orden;


public class ConnectionDB {
	public static Connection cn;
	static String thisIp = new String ();
	
	public static Connection getConnection() throws Exception, SQLException
	{
		try 
		{
			thisIp = InetAddress.getLocalHost().getHostAddress();
			System.out.println("IP:"+thisIp);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		try
		{		
			Class.forName("com.sybase.jdbc3.jdbc.SybDriver");
			cn = DriverManager.getConnection("jdbc:sybase:Tds:"+thisIp+":2638/micros","dba","micros"); //Esta es la entrada a la base de datos con la IP de la tineda en la que esta el programa
			//cn = DriverManager.getConnection("jdbc:sybase:Tds:172.20.32.100:2638/micros","dba","micros"); "jdbc:sybase:Tds:"+thisIp+":2638/micros","dba","micros"
		}
		catch(Exception e)
		{	
			cn = null;
		}
		return cn;	
	}

	public  ArrayList<Orden> GetOrderPrep() throws Exception, SQLException
	{	
		ArrayList<Orden> orden = new ArrayList<Orden>();
		cn = ConnectionDB.getConnection();
		if (cn != null) 
		{		
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT  chk_num, name FROM micros.order_dtl where date_send is null and date_closed >  DATEADD(mi,-30,GETDATE())  and zone<100 order by date_closed asc");
		
			while(rs.next())
			{
				Orden o = new Orden();
				o.setChk(rs.getInt("chk_num"));			
				o.setname(rs.getString("name"));

				orden.add(o);
			}
		}
		return orden;
	}
	
	
	public  ArrayList<Orden> GetOrderFinal() throws Exception {
		
		String setOrderType= "";//
		String OrderType = "";//
		String chk_seq = "";
		String Order_id = "";
		
		ArrayList<Orden> orden = new ArrayList<Orden>();
		cn = ConnectionDB.getConnection();
		if (cn != null) 
		{
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT chk_num, name FROM micros.order_dtl WHERE date_send IS NOT NULL AND date_delete IS NULL AND date_send > DATEADD(mi,-30,GETDATE()) ORDER BY date_send DESC ");//"SELECT TOP 30 chk_num, name FROM micros.order_dtl WHERE date_send IS NOT NULL ORDER BY date_send DESC"); //"SELECT chk_num, name FROM micros.order_dtl WHERE date_send IS NOT NULL  AND date_send > DATEADD(mi,-15,GETDATE()) ORDER BY date_send DESC"
			
			while(rs.next())
			{
				Orden o = new Orden();
				o.setChk(rs.getInt("chk_num"));				
				o.setname(rs.getString("name"));				
				orden.add(o);		
				
				setOrderType = rs.getString("chk_seq");	//
				Statement stm2 = cn.createStatement();//
				ResultSet rs2 = stm2.executeQuery("select order_type_seq, chk_seq from micros.chk_dtl where chk_seq = '"+setOrderType+"'");//
				while(rs2.next())//
				{
					OrderType = rs.getString("order_type_seq");
					chk_seq = rs. getString("chk_seq");
				}								 			 				
			}
		}	
				
		return orden;	
	}

	public static ArrayList<String> GetProductND() throws SQLException, Exception 
	{
		cn = ConnectionDB.getConnection();
		ArrayList<String> ND = new ArrayList<String>();
		if (cn != null) 
		{
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT D.obj_num as CodMicros, D.name_1 as Descripcion\r\n" + 
					"FROM \"micros\".\"mi_def\" as D\r\n" + 
					"INNER JOIN micros.mi_status S on d.mi_seq = S.mi_seq\r\n" + 
					"WHERE S.ob_mi32_out_of_mi = 'T'"); 
			
			while(rs.next())
			{
				ND.add(rs.getString("CodMicros"));	
			}		
		}
		return ND;
	}
}

//"SELECT  id, chk_num, date_open, date_closed, name, date_send,date_delete, [zone],chk_seq FROM micros.order_dtl where date_send is null and date_closed >  DATEADD(mi,-30,GETDATE())  and zone<100 order by date_closed asc"
