package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.controlador;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventana_aviso extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ventana_aviso dialog = new ventana_aviso();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ventana_aviso(JFrame parent, boolean modal) {
		super(parent,modal);
		setResizable(false);
		setBounds(100, 100, 924, 475);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(72, 209, 204));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JLabel lblNewLabel = new JLabel("Productos Proximos a Caducar");
			lblNewLabel.setForeground(new Color(0, 128, 128));
			lblNewLabel.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 30));
			lblNewLabel.setBounds(25, 22, 453, 40);
			contentPanel.add(lblNewLabel);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(25, 68, 404, 299);
		contentPanel.add(scrollPane);
		
		JList<String> list = new JList<String>();
		list.setRequestFocusEnabled(false);
		list.setFocusable(false);
		list.setFont(new Font("Segoe UI Black", Font.ITALIC, 20));
		list.setBackground(new Color(72, 209, 204));
		scrollPane.setViewportView(list);
		list.setModel(controlador.cargarListaProductos("", controlador.listarProximasCaducaciones(controlador.cargarProductos(), Integer.parseInt(controlador.options[1]))));
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(488, 22, 9, 345);
		contentPanel.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Productos Caducados");
		lblNewLabel_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_1.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 30));
		lblNewLabel_1.setBounds(531, 22, 330, 40);
		contentPanel.add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		scrollPane_1.setBounds(531, 68, 330, 299);
		contentPanel.add(scrollPane_1);
		
		JList<String> list_1 = new JList<String>();
		list_1.setRequestFocusEnabled(false);
		list_1.setFocusable(false);
		list_1.setFont(new Font("Segoe UI Black", Font.ITALIC, 20));
		list_1.setBackground(new Color(72, 209, 204));
		list_1.setModel(controlador.cargarListaProductos("", controlador.caducados));
		scrollPane_1.setViewportView(list_1);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBackground(new Color(0, 128, 128));
		btnNewButton.setBorder(new LineBorder(new Color(0, 128, 128)));
		btnNewButton.setFont(new Font("Source Serif Pro Black", Font.ITALIC, 14));
		btnNewButton.setBounds(718, 397, 190, 38);
		contentPanel.add(btnNewButton);
		
		//Listeners-----------------------------------------------------------------------------------------------------------------
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventana_aviso.this.setVisible(false);
			}
		});
	}
}
