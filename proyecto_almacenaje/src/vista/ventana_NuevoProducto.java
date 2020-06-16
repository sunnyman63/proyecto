package vista;

import java.awt.BorderLayout;
import controlador.controlador;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;

import java.util.*;
import javax.swing.SpinnerListModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ventana_NuevoProducto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JTextField tfCantidad;

	/**
	 * Launch the application.
	 */
	
	/*public static void main(String[] args) {
		try {
			ventana_NuevoProducto dialog = new ventana_NuevoProducto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ventana_NuevoProducto(String category, Frame parent, boolean modal) {
		
		super(parent,modal);
		setResizable(false);
		
		setBounds(100, 100, 436, 375);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(72, 209, 204));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(parent);
		
		JComboBox<String> comboCategorias = new JComboBox<String>();
		comboCategorias.setBackground(new Color(95, 158, 160));
		comboCategorias.setBorder(new LineBorder(new Color(95, 158, 160), 1, true));
		comboCategorias.setBounds(29, 26, 363, 36);
		comboCategorias.setModel(controlador.cargarCategorias());
		if(!category.equals("categoria")) {
			comboCategorias.setSelectedItem(category);
		}
		contentPanel.add(comboCategorias);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(29, 100, 363, 36);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfCantidad = new JTextField();
		tfCantidad.setBounds(29, 176, 177, 36);
		contentPanel.add(tfCantidad);
		tfCantidad.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 14));
		lblNombre.setBounds(29, 75, 107, 14);
		contentPanel.add(lblNombre);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 14));
		lblCantidad.setBounds(29, 151, 107, 14);
		contentPanel.add(lblCantidad);
		
		JCheckBox checkCaducidad = new JCheckBox("Caducidad");
		checkCaducidad.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 14));
		checkCaducidad.setBackground(new Color(72, 209, 204));
		checkCaducidad.setBounds(262, 183, 130, 23);
		contentPanel.add(checkCaducidad);
		
		JSpinner spinnerDia = new JSpinner();
		spinnerDia.setEnabled(false);
		spinnerDia.setFocusable(false);
		spinnerDia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spinnerDia.setBounds(29, 248, 39, 20);
		contentPanel.add(spinnerDia);
		
		JComboBox<String> comboMes = new JComboBox<String>();
		comboMes.setEnabled(false);
		comboMes.setModel(new DefaultComboBoxModel<String>(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		comboMes.setBounds(78, 248, 88, 20);
		contentPanel.add(comboMes);
		
		JSpinner spinnerAnyo = new JSpinner();
		spinnerAnyo.setEnabled(false);
		spinnerAnyo.setFocusable(false);
		spinnerAnyo.setModel(new SpinnerListModel(new String[] {String.valueOf(controlador.c.get(Calendar.YEAR)),String.valueOf(controlador.c.get(Calendar.YEAR)+1),String.valueOf(controlador.c.get(Calendar.YEAR)+2),String.valueOf(controlador.c.get(Calendar.YEAR)+3),String.valueOf(controlador.c.get(Calendar.YEAR)+4),String.valueOf(controlador.c.get(Calendar.YEAR)+5)}));
		spinnerAnyo.setBounds(176, 248, 60, 20);
		contentPanel.add(spinnerAnyo);
		
		JButton btnAnyadir = new JButton("A\u00F1adir");
		btnAnyadir.setBackground(new Color(0, 128, 128));
		btnAnyadir.setBorder(new LineBorder(new Color(0, 128, 128)));
		btnAnyadir.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 14));
		btnAnyadir.setBounds(220, 287, 190, 38);
		contentPanel.add(btnAnyadir);
		
		JLabel lblFechaCaducidad = new JLabel("Fecha de caducidad:");
		lblFechaCaducidad.setEnabled(false);
		lblFechaCaducidad.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 14));
		lblFechaCaducidad.setBounds(29, 223, 177, 14);
		contentPanel.add(lblFechaCaducidad);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(0, 128, 128));
		btnCancelar.setBorder(new LineBorder(new Color(0, 128, 128)));
		btnCancelar.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 14));
		btnCancelar.setBounds(10, 287, 190, 38);
		contentPanel.add(btnCancelar);
		
		JTextPane tpError = new JTextPane();
		tpError.setEditable(false);
		tpError.setForeground(new Color(255, 0, 0));
		tpError.setBackground(new Color(72, 209, 204));
		tpError.setBounds(246, 213, 146, 55);
		contentPanel.add(tpError);
		
		//Listeners------------------------------------------------------------------------------------------------
		
		checkCaducidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkCaducidad.isSelected()) {
					lblFechaCaducidad.setEnabled(true);
					spinnerDia.setEnabled(true);
					comboMes.setEnabled(true);
					spinnerAnyo.setEnabled(true);
				} else {
					lblFechaCaducidad.setEnabled(false);
					spinnerDia.setEnabled(false);
					comboMes.setEnabled(false);
					spinnerAnyo.setEnabled(false);
				}
			}
		});
		
		comboMes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String mes = comboMes.getSelectedItem().toString();
				int anyo = Integer.parseInt(spinnerAnyo.getValue().toString());
				int dia = Integer.parseInt(spinnerDia.getValue().toString());
				
				if(mes.equals("Enero") || mes.equals("Marzo") || mes.equals("Mayo") || mes.equals("Julio") || mes.equals("Agosto") || mes.equals("Octubre") || mes.equals("Diciembre")) {
					spinnerDia.setModel(new SpinnerNumberModel(Integer.parseInt(spinnerDia.getValue().toString()), 1, 31, 1));
				} else if(mes.equals("Febrero")) {
					if(new GregorianCalendar().isLeapYear(anyo)) {
						spinnerDia.setModel(new SpinnerNumberModel(Integer.parseInt(spinnerDia.getValue().toString()), 1, 29, 1));
					} else {
						if(dia>28) {
							spinnerDia.setModel(new SpinnerNumberModel(28, 1, 28, 1));
						} else if(dia<1){
							spinnerDia.setModel(new SpinnerNumberModel(1, 1, 28, 1));
						}
					}
				} else {
					if(dia>30) {
						spinnerDia.setModel(new SpinnerNumberModel(30, 1, 30, 1));
					} else if(dia<1) {
						spinnerDia.setModel(new SpinnerNumberModel(1, 1, 30, 1));
					}
				}
			}
		});
		
		comboMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tpError.setText("");
			}
		});
		
		spinnerAnyo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				String mes = comboMes.getSelectedItem().toString();
				int anyo = Integer.parseInt(spinnerAnyo.getValue().toString());
				int dia = Integer.parseInt(spinnerDia.getValue().toString());
				if(mes.equals("Enero") || mes.equals("Marzo") || mes.equals("Mayo") || mes.equals("Julio") || mes.equals("Agosto") || mes.equals("Octubre") || mes.equals("Diciembre")) {
					spinnerDia.setModel(new SpinnerNumberModel(Integer.parseInt(spinnerDia.getValue().toString()), 1, 31, 1));
				} else if(mes.equals("Febrero")) {
					if(new GregorianCalendar().isLeapYear(anyo)) {
						spinnerDia.setModel(new SpinnerNumberModel(Integer.parseInt(spinnerDia.getValue().toString()), 1, 29, 1));
					} else {
						if(dia>28) {
							spinnerDia.setModel(new SpinnerNumberModel(28, 1, 28, 1));
						} else if(dia<1){
							spinnerDia.setModel(new SpinnerNumberModel(1, 1, 28, 1));
						}
					}
				} else {
					if(dia>30) {
						spinnerDia.setModel(new SpinnerNumberModel(30, 1, 30, 1));
					} else if(dia<1){
						spinnerDia.setModel(new SpinnerNumberModel(1, 1, 30, 1));
					}
				}
			}
		});
		
		spinnerAnyo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tpError.setText("");
			}
		});
		
		spinnerDia.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				int anyo = Integer.parseInt(spinnerAnyo.getValue().toString());
				int dia = Integer.parseInt(spinnerDia.getValue().toString());
				String mes = comboMes.getSelectedItem().toString();
				
				if(mes.equals("Enero") || mes.equals("Marzo") || mes.equals("Mayo") || mes.equals("Julio") || mes.equals("Agosto") || mes.equals("Octubre") || mes.equals("Diciembre")) {
					if(dia>31) {
						spinnerDia.setModel(new SpinnerNumberModel(31, 1, 31, 1));
					} else {
						spinnerDia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
					}
				} else if(mes.equals("Febrero")) {
					if(new GregorianCalendar().isLeapYear(anyo)) {
						if(dia>29) {
							spinnerDia.setModel(new SpinnerNumberModel(29, 1, 29, 1));
						} else {
							spinnerDia.setModel(new SpinnerNumberModel(1, 1, 29, 1));
						}
					} else {
						if(dia>28) {
							spinnerDia.setModel(new SpinnerNumberModel(28, 1, 28, 1));
						} else {
							spinnerDia.setModel(new SpinnerNumberModel(1, 1, 28, 1));
						}
					}
				} else {
					if(dia>30) {
						spinnerDia.setModel(new SpinnerNumberModel(30, 1, 30, 1));
					} else {
						spinnerDia.setModel(new SpinnerNumberModel(1, 1, 30, 1));
					}
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				tpError.setText("");
			}
		});
		
		btnAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfNombre.getText().toString().equals("") || tfCantidad.getText().toString().equals("")) {
					tpError.setText("*Los campos Nombre y Cantidad son obligatorios");
				} else {
					if(checkCaducidad.isSelected()) {
						int anyo = Integer.parseInt(spinnerAnyo.getValue().toString());
						int dia = Integer.parseInt(spinnerDia.getValue().toString());
						String mes = comboMes.getSelectedItem().toString();
						int mesInt = controlador.numerarMeses(mes);
					
						if(anyo==controlador.c.get(Calendar.YEAR) && mesInt==controlador.c.get(Calendar.MONTH) && dia<=controlador.c.get(Calendar.DAY_OF_MONTH)) {
							tpError.setText("*La fecha de caducidad debe ser mayor a la del dia de hoy.");
						} else if(anyo==controlador.c.get(Calendar.YEAR) && mesInt<controlador.c.get(Calendar.MONTH)) {
							tpError.setText("*La fecha de caducidad debe ser mayor a la del dia de hoy.");
							} else {
								String msg = "Esta seguro de los datos introducidos?"
										+ "\n Nombre: " + tfNombre.getText()
										+ "\nCantidad: " + tfCantidad.getText()
										+ "\nFecha de Caducidad: " + spinnerDia.getValue() + "/" + controlador.numerarMeses(comboMes.getSelectedItem().toString()) + "/" + spinnerAnyo.getValue();
								String valore = "'"+tfNombre.getText()+ "','" + comboCategorias.getSelectedItem() + "','" + tfCantidad.getText() + "','true','"  + spinnerDia.getValue() + "/" + controlador.numerarMeses(comboMes.getSelectedItem().toString()) + "/" + spinnerAnyo.getValue() + "'";
								controlador.preguntaFinalAñadir(ventana_NuevoProducto.this, msg, valore);
								ventana_Principal.listProductos.setModel(controlador.cargarListaProductos(category,controlador.cargarProductos()));
								ventana_Principal.dialog.setVisible(false);
						}
					} else {
						String msg = "Esta seguro de los datos introducidos?"
								+ "\n Nombre: " + tfNombre.getText()
								+ "\nCantidad: " + tfCantidad.getText()
								+ "\nSin Fecha de Caducidad.";
						String valore = "'"+tfNombre.getText()+ "','" + comboCategorias.getSelectedItem() + "','" + tfCantidad.getText() + "','false',null";
						controlador.preguntaFinalAñadir(ventana_NuevoProducto.this, msg, valore);
						ventana_Principal.listProductos.setModel(controlador.cargarListaProductos(category,controlador.cargarProductos()));
						ventana_Principal.dialog.setVisible(false);
					}
				}
			}
		});
		
		tfNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tpError.setText("");
			}
		});
		
		tfCantidad.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tpError.setText("");
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventana_Principal.dialog.setVisible(false);
			}
		});
		
	}
}
