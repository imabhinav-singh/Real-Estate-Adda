import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class Report  {

	private JFrame frame;
	private JTable table;
	public static ResultSet resultset;
	public static int columns = 8;
	private JScrollPane scrollPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report window = new Report();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Report() {
		initialize();
	}
	private void initialize(){
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setBounds(100, 100, columns*100+100, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setTitle("Report");
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, columns*100 + 84, 261);
		frame.getContentPane().add(scrollPane);
		try {
			table = new JTable(buildTableModel(resultset));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		scrollPane.setViewportView(table);
		
		table.setFillsViewportHeight(true);
		frame.getContentPane().add(table.getTableHeader(), BorderLayout.PAGE_START);
		
		TableColumn column = null;
		for (int i = 0; i < columns-1; i++) {
		    column = table.getColumnModel().getColumn(i);
		    if(i==3) {
		    	column.setPreferredWidth(200);
		    	i++;
		    }
		    else column.setPreferredWidth(100);
		}
	}

	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnLabel(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
}
	
