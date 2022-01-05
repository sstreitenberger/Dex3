/*     */ package dex;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.net.InetAddress;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.util.Vector;
/*     */ import dex.Conexion;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Conexion
/*     */ {
/*  22 */   public Connection getConnection() { return getConnectionProd(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Connection getConnectionProd() {
/*     */     try {
/*  29 */       String ip = InetAddress.getLocalHost().getHostAddress();
/*     */       
/*  31 */       String url = "jdbc:sybase:Tds:" + ip + ":2638?servicename=micros";
/*  32 */      
/*  33 */       String user = "dba";
/*  34 */       String password = "micros";
/*  35 */       Class.forName("com.sybase.jdbc3.jdbc.SybDriver");
/*  36 */       System.out.println("OK");
/*  37 */       return DriverManager.getConnection(url, user, password);
/*  38 */     } catch (Exception e) {
/*  39 */       e.printStackTrace();
/*  40 */       System.out.println("ERROR");
/*  41 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Connection getConnectionPayrollx() {
/*     */     try {
/*  47 */       String url = "jdbc:sqlserver://172.31.1.23:1433";
/*  48 */       String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
/*  49 */       String userName = "web";
/*  50 */       String password = "w3b4ls34";
/*  51 */       Class.forName(driver);
/*  52 */       return DriverManager.getConnection(url, userName, password);
/*  53 */     } catch (Exception e) {
/*  54 */       e.printStackTrace();
/*  55 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String rs2table(ResultSet rs) throws Exception {
/*  60 */     String table = "<table>";
/*  61 */     ResultSetMetaData metaData = rs.getMetaData();
/*     */     
/*  63 */     Vector<String> columnNames = new Vector<>();
/*  64 */     int columnCount = metaData.getColumnCount();
/*     */     
/*  66 */     table = String.valueOf(table) + "<thead><tr>";
/*  67 */     for (int column = 1; column <= columnCount; column++) {
/*  68 */       columnNames.add(metaData.getColumnName(column));
/*  69 */       table = String.valueOf(table) + "<th>" + metaData.getColumnName(column) + "</th>";
/*     */     } 
/*  71 */     table = String.valueOf(table) + "</tr></thead>";
/*     */     
/*  73 */     Vector<Vector<Object>> data = new Vector<>();
/*  74 */     table = String.valueOf(table) + "<tbody>";
/*  75 */     while (rs.next()) {
/*  76 */       Vector<Object> vector = new Vector();
/*  77 */       table = String.valueOf(table) + "<tr>";
/*  78 */       for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
/*  79 */         vector.add(rs.getObject(columnIndex));
/*  80 */         table = String.valueOf(table) + "<td>" + rs.getObject(columnIndex) + "</td>";
/*     */       } 
/*  82 */       table = String.valueOf(table) + "</tr>";
/*  83 */       data.add(vector);
/*     */     } 
/*  85 */     table = String.valueOf(table) + "</tbody></table>";
/*     */     
/*  87 */     return table;
/*     */   }
/*     */   
/*     */   public static String rs2json(ResultSet rs) throws Exception {
/*  91 */     JsonArray json = new JsonArray();
/*  92 */     ResultSetMetaData rsmd = rs.getMetaData();
/*  93 */     while (rs.next()) {
/*  94 */       int numColumns = rsmd.getColumnCount();
/*  95 */       JsonObject obj = new JsonObject();
/*  96 */       for (int i = 1; i <= numColumns; i++) {
/*  97 */         String column_name = rsmd.getColumnName(i);
/*  98 */         switch (rsmd.getColumnType(i)) {
/*     */           case -7:
/* 100 */             obj.addProperty(column_name, Boolean.valueOf(((Boolean)rs.getObject(column_name)).booleanValue()));
/*     */             break;
/*     */           case 2:
/* 103 */             obj.addProperty(column_name, Double.valueOf(((Double)rs.getObject(column_name)).doubleValue()));
/*     */             break;
/*     */           case 4:
/* 106 */             obj.addProperty(column_name, Integer.valueOf(((Integer)rs.getObject(column_name)).intValue()));
/*     */             break;
/*     */           default:
/* 109 */             obj.addProperty(column_name, (String)rs.getObject(column_name));
/*     */             break;
/*     */         } 
/*     */       } 
/* 113 */       json.add((JsonElement)obj);
/*     */     } 
/* 115 */     return json.toString();
/*     */   }
/*     */ }

