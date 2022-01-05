/*    */ package dex;
/*    */ 
/*    */ import dex.Precios;
/*    */ import com.google.gson.Gson;
/*    */ import java.sql.Connection;
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.util.ArrayList;
/*    */ import dex.Conexion;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GestorPrecios
/*    */ {
/*    */   public String obtenerPrecios() {
/* 20 */     Connection con = (new Conexion()).getConnection();
/* 21 */     String query = "select obj_num ,cast(preset_amt_1 as int) as precio from micros.mi_def a,micros.mi_price_def b\r\n where a.mi_seq=b.mi_seq and B.mi_price_seq in (select max(mi_price_seq) from micros.mi_price_def where B.mi_seq=mi_seq and effective_from<now()) and preset_amt_1 is not null";
/*    */ 
/*    */     
/* 24 */     ArrayList<Precios> listPrecios = new ArrayList<>();
/*    */     
/*    */     try {
/* 27 */       PreparedStatement pst = con.prepareStatement(query);
/*    */ 
/*    */       
/* 30 */       ResultSet rs = pst.executeQuery();
/*    */       
/* 32 */       while (rs.next()) {
/* 33 */         Precios p = new Precios();
/*    */         
/* 35 */         p.setSKU(rs.getInt("obj_num"));
/* 36 */         p.setPrecio(rs.getInt("precio"));
/*    */         
/* 38 */         listPrecios.add(p);
/*    */       } 
/*    */ 
/*    */       
/* 42 */       Gson json = new Gson();
/* 43 */       String var = json.toJson(listPrecios);
/*    */       
/* 45 */       return var;
/*    */     
/*    */     }
/* 48 */     catch (SQLException e) {
/* 49 */       e.printStackTrace();
/* 50 */       return "ERROR";
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\sstreitenberger\Desktop\multiconvecional\dex.war!\WEB-INF\classes\Gestores\GestorPrecios.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */