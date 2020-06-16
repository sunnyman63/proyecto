package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.event.ChangeListener;

import controlador.controlador;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventana_Opciones extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ventana_Opciones dialog = new ventana_Opciones();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ventana_Opciones(JFrame parent, boolean modal) {
		super(parent,modal);
		setUndecorated(true);
		setBounds(100, 100, 574, 203);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(72, 209, 204));
		contentPanel.setBorder(new LineBorder(new Color(0, 128, 128), 2));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(parent);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 128, 128));
		separator.setForeground(new Color(0, 128, 128));
		separator.setBounds(28, 126, 502, 7);
		contentPanel.add(separator);
		
		JLabel lblDiasAviso = new JLabel("Con cuantos d\u00EDas de antelaci\u00F3n quieres los avisos de la caducidad ");
		lblDiasAviso.setEnabled(false);
		lblDiasAviso.setFont(new Font("Source Serif Pro Semibold", Font.BOLD, 12));
		lblDiasAviso.setBounds(138, 91, 367, 14);
		contentPanel.add(lblDiasAviso);
		
		JComboBox<String> comboDiasAviso = new JComboBox<String>();
		comboDiasAviso.setFocusable(false);
		comboDiasAviso.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7"}));
		comboDiasAviso.setBackground(new Color(0, 128, 128));
		comboDiasAviso.setBorder(new LineBorder(new Color(0, 128, 128), 1, true));
		comboDiasAviso.setBounds(95, 95, 36, 20);
		comboDiasAviso.setSelectedItem(controlador.options[1]);
		contentPanel.add(comboDiasAviso);
		
		JLabel lblDiasAviso2 = new JLabel("proxima de los productos.");
		lblDiasAviso2.setEnabled(false);
		lblDiasAviso2.setFont(new Font("Source Serif Pro Semibold", Font.BOLD, 12));
		lblDiasAviso2.setBounds(138, 104, 183, 14);
		contentPanel.add(lblDiasAviso2);
		
		JLabel lblNewLabel_3 = new JLabel("Opciones");
		lblNewLabel_3.setForeground(new Color(0, 128, 128));
		lblNewLabel_3.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 30));
		lblNewLabel_3.setBounds(28, 11, 151, 43);
		contentPanel.add(lblNewLabel_3);
		
		JCheckBox checkRecibirAviso = new JCheckBox("Recibir aviso al abrir la aplicaci\u00F3n de los productos proximos a su fecha de caducidad.");
		checkRecibirAviso.setFont(new Font("Source Serif Pro Semibold", Font.BOLD, 12));
		checkRecibirAviso.setBackground(new Color(72, 209, 204));
		checkRecibirAviso.setBounds(28, 61, 502, 23);
		checkRecibirAviso.setSelected(Boolean.valueOf(controlador.options[0]));
		contentPanel.add(checkRecibirAviso);
		
		if(checkRecibirAviso.isSelected()) {
			comboDiasAviso.setEnabled(true);
			lblDiasAviso.setEnabled(true);
			lblDiasAviso2.setEnabled(true);
		} else {
			comboDiasAviso.setEnabled(false);
			lblDiasAviso.setEnabled(false);
			lblDiasAviso2.setEnabled(false);
		}
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(new Color(0, 128, 128));
		btnAceptar.setBorder(new LineBorder(new Color(0, 128, 128)));
		btnAceptar.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 14));
		btnAceptar.setBounds(358, 144, 190, 38);
		contentPanel.add(btnAceptar);
		
		//Listeners---------------------------------------------------------------------------------
		
		checkRecibirAviso.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(checkRecibirAviso.isSelected()) {
					comboDiasAviso.setEnabled(true);
					lblDiasAviso.setEnabled(true);
					lblDiasAviso2.setEnabled(true);
				} else {
					comboDiasAviso.setEnabled(false);
					lblDiasAviso.setEnabled(false);
					lblDiasAviso2.setEnabled(false);
				}
			}
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if((checkRecibirAviso.isSelected()!=Boolean.valueOf(controlador.options[0])) ||(!comboDiasAviso.getSelectedItem().toString().equals(controlador.options[1]))) {
					String[] select = new String[]{String.valueOf(checkRecibirAviso.isSelected()),comboDiasAviso.getSelectedItem().toString()};
					try {
						controlador.actualizarFichero(controlador.settings, select);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(ventana_Opciones.this, "La aplicación debe reiniciarse\npara aplicar los cambios.", "Reinicio necesario", JOptionPane.OK_OPTION);
					ventana_Opciones.this.setVisible(false);
					ventana_Principal.frame.setVisible(false);
					ventana_Principal.main(null);
					if(checkRecibirAviso.isSelected()==true) {
						controlador.aviso = true;
					} else {
						controlador.aviso = false;
					}
				} else {
					ventana_Opciones.this.setVisible(false);
				}
				
			}
		});
		
	}
}
