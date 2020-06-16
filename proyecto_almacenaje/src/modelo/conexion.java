package modelo;
	
import java.sql.*;
import java.util.ArrayList;

public class conexion {
		
	public static String string = "";
	public static boolean contraCorrecta = false;
		
	private static Connection cn = null;
		
	public Connection conectar(String ruta) {
			
		try {
			Class.forName("org.sqlite.JDBC").newInstance();
			cn = DriverManager.getConnection("jdbc:sqlite:" + ruta);
				  
			} catch (ClassNotFoundException cnfe) {
					cnfe.printStackTrace();
			} catch (SQLException sqle) {
					sqle.printStackTrace();
			} catch (InstantiationException ie) {
					ie.printStackTrace();
			} catch (IllegalAccessException iae) {
					iae.printStackTrace();
			}
			
		return cn;
	}
		
	public void desconectar() {
		try {
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//metodo general para insertar, eliminar o actualizar en la base de datos------------------------
	
	public void consultasInDelUp(String string) {
		String sentenciaSQL = string;
		PreparedStatement sentencia = null;
		int resultado = 0;
		try {
			sentencia = cn.prepareStatement(sentenciaSQL);
			resultado = sentencia.executeUpdate();
		} catch (SQLException sqle) {
			  sqle.printStackTrace();
		} finally {
			if (sentencia != null) {
				try {
					sentencia.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		}
	}
	
	//consultas (INSERT, DELETE or UPDATE)----------------------------------------------------------
	
	public String insertarProducto(String tabla, String valores) {
		return "INSERT INTO " + tabla + "(nombre,categoria,cantidad,caducidad,fecha_caducidad) VALUES (" + valores +");";
	}
	
	public String eliminarProducto(String tabla, String nombre) {
		return "DELETE FROM " + tabla + " WHERE nombre='" + nombre + "';";
	}
	
	//otras consultas (SELECT o CREATE)------------------------------------------------------------------------------
	
	public ArrayList<String> consultarCategorias(String tabla) {
		ArrayList<String> lista = new ArrayList<String>();
		
		String sentenciaSQL = "SELECT nombre FROM " + tabla + ";" ;
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		try {
			sentencia = cn.prepareStatement(sentenciaSQL);
			resultado = sentencia.executeQuery();
			
			lista.add("Seleccione Categoria------");
			
			while (resultado.next()) {				
				lista.add(resultado.getString("nombre"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public ArrayList<String[]> consultarProductos(String tabla) {
		
		ArrayList<String[]> lista = new ArrayList<String[]>();
		
		String sentenciaSQL = "SELECT * FROM " + tabla + ";";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		try {
			sentencia = cn.prepareStatement(sentenciaSQL);
			resultado = sentencia.executeQuery();
			
			while (resultado.next()) {	
				String[] productos = new String[] {
					resultado.getString("nombre"),
					resultado.getString("categoria"),
					resultado.getString("cantidad"),
					resultado.getString("caducidad"),
					resultado.getString("fecha_caducidad")
				};
				lista.add(productos);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
		
	}
	
	public ArrayList<String[]> consultarProductosCaducados(int day, int month, int year) {
		ArrayList<String[]> lista = new ArrayList<String[]>();
		
		String sentenciaSQL = "SELECT nombre, caducidad, fecha_caducidad, categoria FROM producto;";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		try {
			sentencia = cn.prepareStatement(sentenciaSQL);
			resultado = sentencia.executeQuery();
			
			while (resultado.next()) {	
				
				if(resultado.getString("caducidad").equals("true")) {
					String[] parts = resultado.getString("fecha_caducidad").split("/");
					if(Integer.parseInt(parts[2])<year) {
						String[] productos = new String[] {
								resultado.getString("nombre"),
								resultado.getString("fecha_caducidad"),
								resultado.getString("categoria")
						};
						lista.add(productos);
					} else {
						if(Integer.parseInt(parts[1])<month) {
							String[] productos = new String[] {
								resultado.getString("nombre"),
								resultado.getString("fecha_caducidad"),
								resultado.getString("categoria")
							};
							lista.add(productos);
						} else {
							if(Integer.parseInt(parts[0])<day) {
								String[] productos = new String[] {
										resultado.getString("nombre"),
										resultado.getString("fecha_caducidad"),
										resultado.getString("categoria")
								};
								lista.add(productos);
							}
						}
					}
				}	
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
}
