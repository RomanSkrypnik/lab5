import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

enum Month {
    DECEMBER("December"), JANUARY("January"), FEBRUARY("February"), MARCH("March"), APRIL("April"), MAY("May"),
    JUNE("June"), JULY("July"), AUGUST("August"), SEPTEMBER("September"), OCTOBER("October"), NOVEMBER("November");

    String name;

    Month(String name) {
        this.name = name;
    }
}

enum Fish {
    CRUCIAN("crucian"), PERCH("perch"), PIKE("pike");

    String name;
    static String fish;
    static String fishStr = null;
    
    
    Fish(String name) {
        this.name = name;
    }
    
    
    public static String getFishes() {
    	fishStr = "";
    	for(int i = 0;i<Fish.values().length;i++) {
    		if(i == Fish.values().length-1) {
    			fishStr += Fish.values()[i].name+'='+(int)(Math.random()*47+3);
    		}else {
    			fishStr += Fish.values()[i].name+'='+(int)(Math.random()*47+3)+',';
    		}
    	}
    	return fishStr;
    }
    
    
    public static String getFish() {
    	setFish(Fish.values()[(int) (Math.random()*3)].name);
    	return fish;
    }
    
    public static void setFish(String pfish) {
    	fish = pfish;
    }
}

class Fishship{ 
    static int month = 0;

    public String fishing() throws SQLException{
        if(month > 11){
            month = 0;
        }
        else {
            Statement statement = Program.connection.createStatement();
            String query = "SELECT * FROM fisher;";
            ResultSet rs = statement.executeQuery(query);
            Program program = new Program();
            String []arr = new String[program.getSize()];
            int i=0;
			while(rs.next()) {
				arr[i]="UPDATE fisher SET "+Fish.getFishes()+" WHERE name='"+rs.getString("name")+"';";
				i++;
				}
			for(int j=0;j<arr.length;j++) {
				statement.executeUpdate(arr[j]);
			}
            statement.close();
        }
        month++;
        return Month.values()[month-1].name;
    }
    

    public String winner(){
    	Program program = new Program();
        try {
			return program.defineWinner(Fish.getFish());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }
}

