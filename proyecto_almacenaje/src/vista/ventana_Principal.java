package vista;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.shaper.StandardButtonShaper;

import controlador.controlador;

import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ventana_Principal {

	public static JFrame frame;
	public static ventana_NuevoProducto dialog;
	private String categoria_seleccionada = "categoria";
	public static JList<String> listProductos;
	static controlador ct;
	private JTextField txtListaDeLa;
	private JTextField tfAnyadir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventana_Principal window = new ventana_Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Create the application.
	 */
	public ventana_Principal() {
		ct = new controlador();
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ventana_Principal.class.getResource("/img/logo_App_220.png")));
		frame.getContentPane().setBackground(new Color(72, 209, 204));
		frame.setResizable(false);
		frame.setBounds(100, 100, 884, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JPanel panelLista = new JPanel();
		panelLista.setVisible(false);
		panelLista.setBackground(new Color(72, 209, 204));
		panelLista.setBounds(0, 21, 878, 495);
		frame.getContentPane().add(panelLista);
		panelLista.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		scrollPane_1.setBounds(10, 45, 436, 439);
		panelLista.add(scrollPane_1);
		
		JList<String> listCompra = new JList<String>();
		listCompra.setFont(new Font("Segoe UI Black", Font.ITALIC, 20));
		listCompra.setBackground(new Color(230, 230, 250));
		listCompra.setModel(controlador.cargarLista(controlador.listaCompra));
		scrollPane_1.setViewportView(listCompra);
		
		txtListaDeLa = new JTextField();
		txtListaDeLa.setFocusable(false);
		txtListaDeLa.setEditable(false);
		txtListaDeLa.setBackground(new Color(230, 230, 250));
		txtListaDeLa.setBorder(null);
		txtListaDeLa.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 25));
		txtListaDeLa.setText("  Lista de la Compra");
		txtListaDeLa.setBounds(10, 11, 436, 36);
		panelLista.add(txtListaDeLa);
		txtListaDeLa.setColumns(10);
		
		JButton btnAnyadir = new JButton("A\u00F1adir");
		btnAnyadir.setBackground(new Color(0, 128, 128));
		btnAnyadir.setBorder(new LineBorder(new Color(0, 128, 128)));
		btnAnyadir.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 14));
		btnAnyadir.setBounds(500, 80, 190, 38);
		panelLista.add(btnAnyadir);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.setBackground(new Color(0, 128, 128));
		btnEliminar.setBorder(new LineBorder(new Color(0, 128, 128)));
		btnEliminar.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 14));
		btnEliminar.setBounds(500, 129, 190, 38);
		panelLista.add(btnEliminar);
		
		JButton btnPDF = new JButton("PDF");
		btnPDF.setBorder(new LineBorder(new Color(0, 128, 128)));
		btnPDF.setBackground(new Color(0, 128, 128));
		btnPDF.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 14));
		btnPDF.setBounds(500, 178, 190, 38);
		panelLista.add(btnPDF);
		
		JButton btnBorrarLista = new JButton("Borrar Lista");
		btnBorrarLista.setBackground(new Color(0, 128, 128));
		btnBorrarLista.setBorder(new LineBorder(new Color(0, 128, 128)));
		btnBorrarLista.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 14));
		btnBorrarLista.setBounds(500, 227, 190, 38);
		panelLista.add(btnBorrarLista);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(new Color(0, 128, 128));
		btnAceptar.setBorder(new LineBorder(new Color(0, 128, 128)));
		btnAceptar.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 14));
		btnAceptar.setBounds(667, 429, 190, 38);
		panelLista.add(btnAceptar);
		
		tfAnyadir = new JTextField();
		tfAnyadir.setBounds(500, 34, 256, 20);
		panelLista.add(tfAnyadir);
		tfAnyadir.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(466, 105, 387, 341);
		frame.getContentPane().add(scrollPane);
		
		listProductos = new JList<String>();
		listProductos.setFont(new Font("Segoe UI Black", Font.ITALIC, 20));
		listProductos.setBackground(new Color(72, 209, 204));
		listProductos.setBorder(null);
		scrollPane.setViewportView(listProductos);
		
		JComboBox<String> comboCategorias = new JComboBox<String>();
		comboCategorias.setFocusable(false);
		comboCategorias.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 30));
		comboCategorias.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		comboCategorias.setBackground(new Color(95, 158, 160));
		comboCategorias.setBounds(466, 52, 387, 42);
		comboCategorias.setModel(ct.cargarCategorias());
		comboCategorias.setSelectedItem("Seleccione Categoria------");
		frame.getContentPane().add(comboCategorias);
		
		JLabel lblNombre = new JLabel("OPHA");
		lblNombre.setForeground(new Color(0, 128, 128));
		lblNombre.setFont(new Font("Source Serif Pro Black", Font.BOLD | Font.ITALIC, 34));
		lblNombre.setBounds(32, 46, 109, 42);
		frame.getContentPane().add(lblNombre);
		
		JButton btnNuevoProducto = new JButton("NUEVO PRODUCTO");
		btnNuevoProducto.setEnabled(false);
		btnNuevoProducto.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 14));
		btnNuevoProducto.setBorder(new LineBorder(new Color(95, 158, 160), 1, true));
		btnNuevoProducto.putClientProperty( SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
		btnNuevoProducto.setBackground(new Color(95, 158, 160));
		btnNuevoProducto.setBounds(466, 460, 190, 38);
		frame.getContentPane().add(btnNuevoProducto);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 994, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Opciones");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuItemOpciones = new JMenuItem("Opciones");
		mnNewMenu.add(menuItemOpciones);
		
		JMenuItem menuItemSalir = new JMenuItem("Salir");
		mnNewMenu.add(menuItemSalir);
		
		JButton btnLista = new JButton("LISTA DE LA COMPRA");
		btnLista.setFocusable(false);
		btnLista.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 14));
		btnLista.setBackground(new Color(95, 158, 160));
		btnLista.setBorder(new LineBorder(new Color(95, 158, 160), 1, true));
		btnLista.setBounds(266, 460, 190, 38);
		frame.getContentPane().add(btnLista);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(ventana_Principal.class.getResource("/img/logo_App_220.png")));
		lblLogo.setBounds(111, 174, 220, 207);
		frame.getContentPane().add(lblLogo);
		
		JLabel lblNombre2 = new JLabel("Organizador de productos del hogar y aliment\u00EDcios");
		lblNombre2.setForeground(new Color(0, 128, 128));
		lblNombre2.setFont(new Font("Source Serif Pro Black", Font.BOLD | Font.ITALIC, 13));
		lblNombre2.setBounds(29, 93, 387, 30);
		frame.getContentPane().add(lblNombre2);
		
		JLabel lblAviso = new JLabel("");
		lblAviso.setVisible(ct.aviso);
		lblAviso.setIcon(new ImageIcon(ventana_Principal.class.getResource("/img/warning.png")));
		lblAviso.setBounds(32, 432, 50, 50);
		frame.getContentPane().add(lblAviso);
		
		JButton btnEliminarProducto = new JButton("ELIMINAR PRODUCTO");
		btnEliminarProducto.setEnabled(false);
		btnEliminarProducto.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 14));
		btnEliminarProducto.setBackground(new Color(95, 158, 160));
		btnEliminarProducto.setBorder(new LineBorder(new Color(95, 158, 160)));
		btnEliminarProducto.setBounds(666, 461, 190, 38);
		frame.getContentPane().add(btnEliminarProducto);
		
		//Listeners----------------------------------------------------------------------------------------------------------------
		
		comboCategorias.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String cate = comboCategorias.getSelectedItem().toString();
				
				if(!cate.equals("Seleccione Categoria------")) {
					listProductos.setModel(ct.cargarListaProductos(cate,ct.cargarProductos()));
					btnNuevoProducto.setEnabled(true);
					btnEliminarProducto.setEnabled(false);
				} else {
					listProductos.setModel(new DefaultListModel<String>());
					btnNuevoProducto.setEnabled(false);
					btnEliminarProducto.setEnabled(false);
				}
			}
		});
		
		btnNuevoProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					categoria_seleccionada = comboCategorias.getSelectedItem().toString();
					dialog = new ventana_NuevoProducto(categoria_seleccionada,frame,true);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.setLocationRelativeTo(frame);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		
		menuItemOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					ventana_Opciones dialog = new ventana_Opciones(frame,true);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.setLocationRelativeTo(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		lblAviso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					ventana_aviso dialog = new ventana_aviso(frame,true);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.setLocationRelativeTo(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String[] lista = controlador.listaCompra;
				ArrayList<String> listota = new ArrayList<String>();
				for(int i=0;i<lista.length;i++) {
					if(lista[i]!=null) {
						listota.add(lista[i]);
					}
				}
				listota.add("- "+tfAnyadir.getText());
				String[] fin =new String[listota.size()];
				for(int j=0;j<listota.size();j++) {
					fin[j] = listota.get(j);
				}
				controlador.listaCompra = fin;
				listCompra.setModel(controlador.cargarLista(controlador.listaCompra));
				try {
					controlador.actualizarFichero(controlador.lista, controlador.listaCompra);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tfAnyadir.setText("");
			}
		});
		
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelLista.setVisible(true);
				comboCategorias.setEnabled(false);
				listProductos.setEnabled(false);
				btnLista.setEnabled(false);
				btnNuevoProducto.setEnabled(false);
				lblAviso.setEnabled(false);
				btnEliminar.setEnabled(false);
			}
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelLista.setVisible(false);
				comboCategorias.setEnabled(true);
				listProductos.setEnabled(true);
				btnLista.setEnabled(true);
				String cate = comboCategorias.getSelectedItem().toString();
				
				if(!cate.equals("Seleccione Categoria------")) {
					btnNuevoProducto.setEnabled(true);
				} else {
					btnNuevoProducto.setEnabled(false);
				}
				lblAviso.setEnabled(true);
			}
		});
		
		listCompra.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				btnEliminar.setEnabled(true);
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select = JOptionPane.showConfirmDialog(frame, "Seguro que quieres borrarlo de la lista?", "Borrar", JOptionPane.YES_NO_OPTION);
				if(select==JOptionPane.YES_NO_OPTION) {
					String[] lista =  controlador.listaCompra;
					String producto = listCompra.getSelectedValue();
					controlador.listaCompra = new String[100];
					int numProd = lista.length;
					int posList = 0;
					
					for(int i=0;i<lista.length;i++) {
						if(lista[i].equals(producto)) {
							posList = i;
							i=lista.length;
						}
					}
					for(int i=posList;i<numProd;i++) {
						if(i==numProd-1) {
							lista[i]=null;
						} else {
							lista[i] = lista[i+1];
						}
					}
					
					
					
					String[] lista2 = new String[lista.length-1];
					for(int i=0;i<lista2.length;i++) {
						lista2[i] = lista[i];
					}
					
					controlador.listaCompra=lista2;
					listCompra.setModel(controlador.cargarLista(controlador.listaCompra));
					try {
						controlador.actualizarFichero(controlador.lista, controlador.listaCompra);
					} catch (Throwable e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnBorrarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select = JOptionPane.showConfirmDialog(frame, "Esta segur@ de que desea borrar\ntoda la lista de la compra?", "Borrar", JOptionPane.YES_NO_OPTION);
				if(select==JOptionPane.YES_OPTION) {
					controlador.listaCompra = new String[100];
					try {
						controlador.actualizarFichero(controlador.lista, controlador.listaCompra);
					} catch (Throwable e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					listCompra.setModel(controlador.cargarLista(controlador.listaCompra));
				}
			}
		});
		
		btnPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String lista = "";
				for(int i=0;i<ct.listaCompra.length;i++) {
					lista = lista + ct.listaCompra[i] + "\n";
				}
				System.out.println(lista);
				ct.reporte(lista);
			}
		});
		
		listProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnEliminarProducto.setEnabled(true);
			}
		});
		
		btnEliminarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] producto = listProductos.getSelectedValue().split("\\(");
				int select = JOptionPane.showConfirmDialog(frame, "Desea eliminar este producto?", "Eliminar", JOptionPane.YES_NO_OPTION);
				if(select==JOptionPane.YES_OPTION) {
					ct.eliminarProducto(producto[0]);
					String cate = comboCategorias.getSelectedItem().toString();
					listProductos.setModel(ct.cargarListaProductos(cate,ct.cargarProductos()));
				}
			}
		});
		
		menuItemSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}
}
