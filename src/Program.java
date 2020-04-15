import java.sql.*;
import javax.swing.JFrame;

public class Program {
    static Object[][]arr = new Object[0][];
    static Connection connection;

    void buildInterface() throws SQLException{
        FishInterface fishInterface = new FishInterface("Yaaaaaz");
        fishInterface.setVisible(true);
        fishInterface.setSize(500, 300);
        fishInterface.setLocationRelativeTo(null);
        fishInterface.setResizable(false);
        fishInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
   
    int getSize() throws SQLException {
    	int size = 0;
    	Statement statement = connection.createStatement();
        String query = "SELECT * FROM fisher;";
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()) {size++;}
        return size;
    }
    void open(){
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Admin\\eclipse-workspace\\Java\\src\\fisher.db");
            System.out.println("Connected");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    Object[][] select() throws SQLException {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM fisher;";
            ResultSet rs = statement.executeQuery(query);
            Object[][]arr = new Object[getSize()][];
            int row = 0;
            while (rs.next()){ arr[row] = new Object[]{rs.getString("name"), rs.getInt("crucian"), rs.getInt("perch"), rs.getInt("pike")}; row++;}
            statement.close();
            rs.close();
            return arr;
    }
    
    void insert(String name) throws SQLException {
        String query = "INSERT INTO fisher(name) " +
                "VALUES('"+name+"'); ";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }
    
     String defineWinner(String fish) throws SQLException {
    	Statement statement = connection.createStatement();
    	String query = "SELECT name FROM fisher ORDER BY "+fish+" DESC;";
    	ResultSet rs = statement.executeQuery(query);
    	return rs.getString("name");
    }
    
    public void close() throws SQLException {
    	connection.close();
    }
    
    public static void main(String[] args) throws SQLException {
        Program program = new Program();
        program.open();
        program.buildInterface(); 
    }
}