import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

class FishInterface extends JFrame {
    Program program = new Program();
    static int size = 0;
    String [] columns = {"Name", "Crucian", "Perch", "Pike"};
    JButton bAdd, bFishing;
    JLabel l1, lWinner, lFish, lMonth;
    JTextField t1,tWinner, tFish, tMonth;
    JTable table;
    JPanel tablePanel = new JPanel(new BorderLayout());

    EventHandler eventHandler = new EventHandler();

    public FishInterface(String string) throws SQLException {
        super(string);
        this.setBackground(Color.black);
        setLayout(new FlowLayout());
        l1 = new JLabel("Name");
        t1 = new JTextField(22);
        bAdd = new JButton("Add");
        bAdd.setPreferredSize(new Dimension(75,25));
        bFishing = new JButton("Fishing");
        tWinner = new JTextField(8);
        lWinner = new JLabel("Winner");
        tFish = new JTextField(8);
        lFish = new JLabel("Fish");
        tMonth = new JTextField(8);
        lMonth = new JLabel("Month");
        buildInterface();
    }
    void buildInterface() throws SQLException {
        add(l1);
        add(t1);
        add(bAdd);
        add(bFishing);
        add(lWinner);
        add(tWinner);
        add(lFish);
        add(tFish);
        add(lMonth);
        add(tMonth);
        buildTable();
        bAdd.addActionListener(eventHandler);
        bFishing.addActionListener(eventHandler);        
    }
    
    public void buildTable() throws SQLException {
    	table = new JTable(program.select(), columns);
    	tablePanel.add(table, BorderLayout.CENTER);
	    tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
	    add(tablePanel);
	    add(table);
    }

    class EventHandler implements ActionListener {

        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == bAdd) {
                try {
					program.insert(t1.getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
                t1.setText(null);
            }
            if(actionEvent.getSource() == bFishing){
                Fishship ob = new Fishship();
                try {
                	remove(table);
                	remove(tablePanel);
					tMonth.setText(ob.fishing());
					tMonth.setEditable(false);
	                tWinner.setText(ob.winner());
	                tWinner.setEditable(false);
	                tFish.setText(Fish.fish);
	                tFish.setEditable(false);
	                buildTable();
	                SwingUtilities.updateComponentTreeUI(table);
	                
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
        }
    }
}


