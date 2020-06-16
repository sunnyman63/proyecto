package controlador;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.WindowConstants;

import modelo.conexion;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class controlador {
	
	public static String[] options = new String[2];
	public static File settings;
	public static File lista;
	public static String OS = System.getProperty("os.name").toLowerCase();
    public static String dir = System.getProperty("user.dir");
    public static Calendar c = Calendar.getInstance();
    public static ArrayList<String[]> caducados;
    public static ArrayList<String[]> proxCaducaciones;
    public static String[] listaCompra = new String[100];
    public static boolean aviso;
    
    static conexion cn = new conexion();
    
    public controlador() {
    	
    	if(isWindows()) {
			settings = new File(dir+"\\extra\\settings.txt");
			lista = new File(dir+"\\extra\\lista.txt");
		} else {
			settings = new File(dir+"/extra/settings.txt");
			lista = new File(dir+"/extra/lista.txt");
		}
    	try {
			options=leerFichero(settings);
			listaCompra=leerFichero(lista);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	caducados=cargarProductosCaducados();
    	if(options[0].equals("true")) {
			proxCaducaciones=listarProximasCaducaciones(cargarProductos(), Integer.parseInt(options[1]));
			if(!proxCaducaciones.isEmpty()) {
				aviso = true;
			}
		}
    }
    
    
    public static boolean isWindows() { return (OS.indexOf("win") >= 0); }
    
    public static ComboBoxModel<String> cargarCategorias() {
    	
    	if(isWindows()) {
			cn.conectar(dir+"\\extra\\productos.db");
		} else {
			cn.conectar(dir+"/extra/productos.db");
		}
    		
    	DefaultComboBoxModel<String> md = new DefaultComboBoxModel<String>() {
    		
    		ArrayList<String> values = cn.consultarCategorias("categoria");
			
    		public int getSize() {
    			return values.size();
    		}
    		public String getElementAt(int index) {
    			return values.get(index);
    		}
    	};
    	
    	cn.desconectar();
    	
    	return md;
    }
    
    public static ArrayList<String[]> cargarProductos() {
    	if(isWindows()) {
			cn.conectar(dir+"\\extra\\productos.db");
		} else {
			cn.conectar(dir+"/extra/productos.db");
		}
    	
    	ArrayList<String[]> productos = cn.consultarProductos("producto");
    	
    	cn.desconectar();
    	
    	return productos;
    }
    
    public ArrayList<String[]> cargarProductosCaducados() {
    	if(isWindows()) {
			cn.conectar(dir+"\\extra\\productos.db");
		} else {
			cn.conectar(dir+"/extra/productos.db");
		}
    	
    	ArrayList<String[]> productos = cn.consultarProductosCaducados(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH), c.get(Calendar.YEAR));
    	
    	cn.desconectar();
    	
    	return productos;
    }
    
    public static ListModel<String> cargarListaProductos(String categoria, ArrayList<String[]> array) {
    	
    		
    	ArrayList<String[]> productos = array;
    	ArrayList<String> valores = new ArrayList<String>();
    	for (int i=0;i<productos.size();i++) {
    		String[] valor = productos.get(i);
    		if(categoria.equals("")) {
    			boolean estaProx = false;
    			String[] prox = new String[6];
    			for(int k=0;k<proxCaducaciones.size();k++) {
    				prox = proxCaducaciones.get(k);
    				if(valor[0].equals(prox[0])) {
    					estaProx = true;
    				}
    			}
    			if(estaProx==true) {
    				valores.add("-"+valor[0]+"\t(Caduca en " + prox[5] + " días)");
    			} else {
    				valores.add("-"+valor[0]+"\t(" + valor[1] + ")(" + valor[2] + ")");
    			}
    		} else if(valor[1].equals(categoria)) {
    			boolean esta = false;
    			for(int j=0;j<caducados.size();j++) {
    				String[] caducado = caducados.get(j);
    				if(valor[0].equals(caducado[0])) {
    					esta = true;
    				}
    			}
    			if(esta==true) {
    				valores.add(valor[0]+ "(" + valor[2]+") (CADUCADO)");
    			} else {
    				valores.add(valor[0]+ "(" + valor[2]+")");
    			}
    			
    		}
    	}
    	
    	DefaultComboBoxModel<String> md = new DefaultComboBoxModel<String>() {
    		
    		ArrayList<String> values = valores;
			
    		public int getSize() {
    			return values.size();
    		}
    		public String getElementAt(int index) {
    			return values.get(index);
    		}
    	};
    	
    	cn.desconectar();
    	
    	return md;
    }
    
    public static ListModel<String> cargarLista(String[] array) {
    	
    	ArrayList<String> valores = new ArrayList<String>();
    	for(int i=0;i<array.length;i++) {
    		valores.add(array[i]);
    	}
    	
    	DefaultComboBoxModel<String> md = new DefaultComboBoxModel<String>() {
    		ArrayList<String> values = valores;
			
    		public int getSize() {
    			return values.size();
    		}
    		public String getElementAt(int index) {
    			return values.get(index);
    		}
    	};
		return md;
    }
    
    
    
    public static int numerarMeses(String mes) {
    	int mesInt = 0;
    	switch(mes) {
			case "Enero":mesInt = 1;break; case "Febrero":mesInt = 2;break; case "Marzo":mesInt = 3;break; case "Abril":mesInt = 4;break; case "Mayo":mesInt = 5;break; case "Junio":mesInt = 6;break;
			case "Julio":mesInt = 7;break; case "Agosto":mesInt = 8;break; case "Septiembre":mesInt = 9;break; case "Octubre":mesInt = 10;break; case "Nobiembre":mesInt = 11;break; case "Diciembre":mesInt = 12;break;
    	}
    	return mesInt;
    }
    
    public static void preguntaFinalAñadir(Component comp, String datos, String valores) {
    	int select = JOptionPane.showConfirmDialog(comp, datos, "Seguro?", JOptionPane.YES_NO_OPTION);
    	if (select == JOptionPane.YES_OPTION) {
    		if(isWindows()) {
    			cn.conectar(dir+"\\extra\\productos.db");
    		} else {
    			cn.conectar(dir+"/extra/productos.db");
    		}
    		cn.consultasInDelUp(cn.insertarProducto("producto", valores));
    		cn.desconectar();
    	}
    }
    
    
    public String[] leerFichero(File file) throws Throwable {
    	String[] apartados = new String[100];
    	FileInputStream fis = null;
		try {
			
			fis = new FileInputStream(file);
			
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			apartados = (String[]) ois.readObject();
			
			ois.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Fallo al leer del fichero");
			e.printStackTrace();
		}
		
		return apartados;
    }
    
    public static void actualizarFichero(File file, String[] strings) throws Throwable {
    	
    	FileOutputStream fos = null;
    	
    	try {
			
			if(isWindows()) {
				fos = new FileOutputStream(file);
			} else {
				fos = new FileOutputStream(file);
			}
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(strings);
			
			oos.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Fallo al leer del fichero");
			e.printStackTrace();
		}
    	
    }
    
    public static ArrayList<String[]> listarProximasCaducaciones(ArrayList<String[]> products, int diasAntes) {
    	
    	ArrayList<String[]> proximos = new ArrayList<String[]>();
    	
    	for(int i=0;i<products.size();i++) {
    		String[] prod = products.get(i);
    		if(prod[3].equals("true")) {
    			String[] cad = prod[4].split("/");
        		int num =Integer.parseInt(cad[0])-c.get(Calendar.DAY_OF_MONTH);
        		if((num<=diasAntes) && (cad[1].equals(String.valueOf(c.get(Calendar.MONTH)+1))) && (cad[2].equals(String.valueOf(c.get(Calendar.YEAR))))) {	
        			if(num>-1) {
        				String[] produ = new String[6];
            			produ[0] = prod[0];
            			produ[1] = prod[1];
            			produ[2] = prod[2];
            			produ[3] = prod[3];
            			produ[4] = prod[4];
            			produ[5] = String.valueOf(num);
            			proximos.add(produ);
        			}
        		}
    		}
    		
    	}
    	
    	return proximos;
    } 
     
    public void reporte (String list) {
		
		try {        
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("lista",list);
            
            if (isWindows()) {
            	JasperPrint mostrarInforme = JasperFillManager.fillReport(dir+"\\extra\\Blank_A4.jasper",parametros,new JREmptyDataSource());    
            	//JasperExportManager.exportReportToPdfFile(mostrarInforme,"C:\\Users\\Usuario\\Desktop\\listadelacompra.pdf");
                JasperViewer view = new JasperViewer(mostrarInforme, false);
                view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                view.setVisible(true);
            } else {
            	JasperPrint mostrarInforme = JasperFillManager.fillReport(dir+"/extra/Blank_A4.jasper",parametros,new JREmptyDataSource()); 
            	//JasperExportManager.exportReportToPdfFile(mostrarInforme,"C:/Users/Usuario/Desktop/listadelacompra.pdf");
                JasperViewer view = new JasperViewer(mostrarInforme, false);
                view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                view.setVisible(true);
            }
        } catch (JRException ex) {
            Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
    
    public static void eliminarProducto(String nom) {
    	if(isWindows()) {
			cn.conectar(dir+"\\extra\\productos.db");
		} else {
			cn.conectar(dir+"/extra/productos.db");
		}
    	cn.consultasInDelUp(cn.eliminarProducto("producto", nom));
    	cn.desconectar();
    }
    
}
