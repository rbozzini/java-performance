package it.rbozzini.corso_java_ee_developer.rubrica.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import it.rbozzini.corso_java_ee_developer.rubrica.business.RubricaBusiness;
import it.rbozzini.corso_java_ee_developer.rubrica.model.Contatto;

public class Rubrica {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtCognome;
	private JTextField txtTelefono;
	private JTable listaContatti;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rubrica window = new Rubrica();
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
	public Rubrica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "serial", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 849, 658);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 38, 843, 592);
		frame.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setToolTipText("Inserisci contatto");
		tabbedPane.addTab("Inserisci contatto", null, panel, null);
		panel.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(21, 34, 61, 16);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(88, 29, 130, 26);
		panel.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(21, 79, 61, 16);
		panel.add(lblCognome);

		txtCognome = new JTextField();
		txtCognome.setBounds(88, 74, 130, 26);
		panel.add(txtCognome);
		txtCognome.setColumns(10);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(21, 122, 61, 16);
		panel.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(88, 117, 130, 26);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);

		JButton btnAggiungi = new JButton("Aggiungi");

		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contatto nuovoContatto = new Contatto();
				nuovoContatto.setNome(txtNome.getText());
				nuovoContatto.setCognome(txtCognome.getText());
				nuovoContatto.setTelefono(txtTelefono.getText());

				try {
					int id = RubricaBusiness.getInstance().aggiungiContatto(nuovoContatto);
					if (id > 0) {
						JOptionPane.showMessageDialog(panel, "Contatto " + nuovoContatto.getNome() + " "
								+ nuovoContatto.getCognome() + " inserito correttamente");
						txtNome.setText("");
						txtCognome.setText("");
						txtTelefono.setText("");

						DefaultTableModel dtm = (DefaultTableModel) listaContatti.getModel();
						Vector rowData = new Vector();
						rowData.add(id);
						rowData.add(nuovoContatto.getNome());
						rowData.add(nuovoContatto.getCognome());
						rowData.add(nuovoContatto.getTelefono());
						dtm.addRow(rowData);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAggiungi.setBounds(101, 195, 117, 29);
		panel.add(btnAggiungi);

		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(232, 195, 117, 29);
		panel.add(btnAnnulla);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Ricerca contatti", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 810, 534);
		panel_1.add(scrollPane);

		listaContatti = new JTable();
		listaContatti.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Nome", "Cognome", "Telefono" }) {
					boolean[] columnEditable = new boolean[] { false, true, true, true };

					@SuppressWarnings("unused")
					public boolean isCellEditabled(int row, int column) {
						return columnEditable[column];
					}
				});

		listaContatti.getColumnModel().getColumn(0).setPreferredWidth(50);
		listaContatti.getColumnModel().getColumn(1).setPreferredWidth(150);
		listaContatti.getColumnModel().getColumn(2).setPreferredWidth(150);
		listaContatti.getColumnModel().getColumn(3).setPreferredWidth(150);

		DefaultTableModel dtm = (DefaultTableModel) listaContatti.getModel();
		try {
			List<Contatto> contatti = RubricaBusiness.getInstance().ricercaContatti();
			for (Contatto contatto : contatti) {
				@SuppressWarnings("rawtypes")
				Vector rowData = new Vector();

				rowData.add(contatto.getId());
				rowData.add(contatto.getNome());
				rowData.add(contatto.getCognome());
				rowData.add(contatto.getTelefono());

				dtm.addRow(rowData);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		scrollPane.setViewportView(listaContatti);
	}
}
