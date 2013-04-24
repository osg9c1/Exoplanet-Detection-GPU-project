/*
 * @author :surabhi_pandey
 *
 * 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JTableExample
		extends 	JFrame
 {
	// Instance attributes used in this example
	private	JPanel		topPanel;
	private	JTable		table;
	private	JScrollPane scrollPane;
	String dataValues[][] = null;

	// Constructor of main frame
	public JTableExample(String data[][])
	{
		// Set the frame characteristics
		setTitle( "Results of your run" );
		setSize( 300, 200 );
		setBackground( Color.gray );

		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create columns names
		String columnNames[] = { "Candidate", "Confirmed", "False Positive","Candidates Classified as Confirm" };

		
		
		// Create a new table instance
		table = new JTable( data, columnNames );

		// Add the table to a scrolling pane
		scrollPane = new JScrollPane( table );
		topPanel.add( scrollPane, BorderLayout.CENTER );
	}

	
}
