/*    */ package dex;
/*    */ 
/*    */ import dex.GestorPrecios;
/*    */ import javax.ws.rs.GET;
/*    */ import javax.ws.rs.Path;
/*    */ import javax.ws.rs.Produces;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Path("/precios.json")
/*    */ public class APIRest
/*    */ {
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   public String hola() {
/* 20 */     GestorPrecios gp = new GestorPrecios();
/* 21 */     String JSON = "";
/* 22 */     JSON = gp.obtenerPrecios();
/* 23 */     return JSON;
/*    */   }
/*    */ }



 