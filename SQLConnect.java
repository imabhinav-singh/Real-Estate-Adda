import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;

public class SQLConnect {
	
	private Connection conn;
	private PreparedStatement preparedstmt;
	private ResultSet resultSet;
	
	public SQLConnect() {
		
		try
		{
			String url = "jdbc:mysql://localhost:3306/realestateoffice";
			String username = "root";
			String password = "abhi1810";
			this.conn= DriverManager.getConnection(url, username, password); 
			this.resultSet = null;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
	}

	public boolean checkLoginDetails(String username, String password) {
		
		try {
			this.preparedstmt = conn.prepareStatement("select aes_decrypt(agentLoginPassword, 'secret') from agent where agentLoginID = "+username);
			this.resultSet = this.preparedstmt.executeQuery();
			while(this.resultSet.next()) {
				if(password.equals(this.resultSet.getString(1))) {
					return true;
				}
			}
			return false;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void updatePassword(String username, String newPassword) {
		
		try {
			this.preparedstmt = this.conn.prepareStatement("update agent " + 
					"set agentLoginPassword = aes_encrypt('"+newPassword+"', 'secret') " + 
					"where agentLoginID = "+username);
			this.preparedstmt.execute();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public boolean verify(String propertyID, String agentID, String buyerID, String sellerID) {
		try {
			this.preparedstmt = this.conn.prepareStatement("select * from property where propertyID = "+propertyID);
			this.resultSet = this.preparedstmt.executeQuery();
			if(!resultSet.next()) return false;
			
			this.preparedstmt = this.conn.prepareStatement("select * from agent where agentLoginID = "+agentID);
			this.resultSet = this.preparedstmt.executeQuery();
			if(!resultSet.next()) return false;
			
			this.preparedstmt = this.conn.prepareStatement("select * from person where person_ID = "+buyerID);
			this.resultSet = this.preparedstmt.executeQuery();
			if(!resultSet.next()) return false;
			
			this.preparedstmt = this.conn.prepareStatement("select * from person where person_ID = "+sellerID);
			this.resultSet = this.preparedstmt.executeQuery();
			if(!resultSet.next()) return false;
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void updateSales(String propertyID, String agentID, String buyerID, String sellerID, boolean isRented,
			String price, String date) {
		
		try {
			this.preparedstmt = this.conn.prepareStatement("select count(distinct saleID) from sales");
			this.resultSet = this.preparedstmt.executeQuery();
			int saleID = 0;
			while(this.resultSet.next()) {
				saleID = this.resultSet.getInt(1);
			}
			saleID++;
			saleID = 2020000 + saleID;
			
			this.preparedstmt = this.conn.prepareStatement("insert into sales value(" +saleID+ ", " +propertyID+", "+agentID+", "+buyerID+", "+sellerID+", "+isRented+", "+price+", '"+date+"')");
			this.preparedstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

	public boolean verify(String username) {
		
		try {
			this.preparedstmt = this.conn.prepareStatement("select * from person where person_ID = "+username);
			this.resultSet = this.preparedstmt.executeQuery();
			if(!resultSet.next()) return false;
			
			this.preparedstmt = this.conn.prepareStatement("select * from agent where agentLoginID = "+username);
			this.resultSet = this.preparedstmt.executeQuery();
			if(resultSet.next()) return false;
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void updateAgent(String username, String password) {
		
		try {
			this.preparedstmt = this.conn.prepareStatement("insert into agent values("+username+", aes_encrypt("+password+"))");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean verifyAgent(String agentID) {
		try {
			this.preparedstmt = this.conn.prepareStatement("select * from agent where agentLoginID = "+agentID);
			this.resultSet = this.preparedstmt.executeQuery();
			if(!resultSet.next()) return false;
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	public void salesResult(String agentID, boolean sortByPrice, boolean sortByDate) {
		
		try {
			if(sortByPrice) this.preparedstmt = this.conn.prepareStatement("select saleID, p.propertyID, p.propertyName, concat(p.locality, ', ', p.district) as 'propertyAddress', agentID, buyerID, sellerID, sellingPriceOrRent as 'selling price', dateOfSale from sales, property p where p.propertyId = sales.propertyID and agentID = "+agentID+" and not isRented order by sellingPriceOrRent");
			else if(sortByDate) this.preparedstmt = this.conn.prepareStatement("select saleID, p.propertyID, p.propertyName, concat(p.locality, ', ', p.district) as 'propertyAddress', agentID, buyerID, sellerID, sellingPriceOrRent as 'selling price', dateOfSale from sales, property p where p.propertyId = sales.propertyID and agentID = "+agentID+" and not isRented order by dateOfSale");
			else this.preparedstmt = this.conn.prepareStatement("select saleID, p.propertyID, p.propertyName, concat(p.locality, ', ', p.district) as 'propertyAddress', agentID, buyerID, sellerID, sellingPriceOrRent as 'selling price', dateOfSale from sales, property p where p.propertyId = sales.propertyID and agentID = "+agentID+" and not isRented");
			this.resultSet = this.preparedstmt.executeQuery();
			Report.resultset = this.resultSet;
			Report.columns = 10;
			Report.main(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void salesResultAgentYear(String agentID, String year, boolean sortByPrice, boolean sortByDate) {
		try {
			if(sortByPrice) this.preparedstmt = this.conn.prepareStatement("select saleID, p.propertyID, p.propertyName, concat(p.locality, ', ', p.district) as 'propertyAddress', agentID, buyerID, sellerID, sellingPriceOrRent as 'selling price', dateOfSale from sales, property p where p.propertyId = sales.propertyID and agentID = "+agentID+" and not isRented and year(dateOfSale) = "+year+" order by sellingPriceOrRent");
			else if(sortByDate) this.preparedstmt = this.conn.prepareStatement("select saleID, p.propertyID, p.propertyName, concat(p.locality, ', ', p.district) as 'propertyAddress', agentID, buyerID, sellerID, sellingPriceOrRent as 'selling price', dateOfSale from sales, property p where p.propertyId = sales.propertyID and agentID = "+agentID+" and not isRented and year(dateOfSale) = "+year+" order by dateOfSale");
			else this.preparedstmt = this.conn.prepareStatement("select saleID, p.propertyID, p.propertyName, concat(p.locality, ', ', p.district) as 'propertyAddress', agentID, buyerID, sellerID, sellingPriceOrRent as 'selling price', dateOfSale from sales, property p where p.propertyId = sales.propertyID and agentID = "+agentID+" and not isRented and year(dateOfSale) = "+year);
			this.resultSet = this.preparedstmt.executeQuery();
			Report.resultset = this.resultSet;
			Report.columns = 10;
			Report.main(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void salesResultYear(String year, boolean sortByPrice, boolean sortByDate) {
		try {
			if(sortByPrice) this.preparedstmt = this.conn.prepareStatement("select saleID, p.propertyID, p.propertyName, concat(p.locality, ', ', p.district) as 'propertyAddress', agentID, buyerID, sellerID, sellingPriceOrRent as 'selling price', dateOfSale from sales, property p where p.propertyId = sales.propertyID and not isRented and year(dateOfSale) = "+year+" order by sellingPriceOrRent");
			else if(sortByDate) this.preparedstmt = this.conn.prepareStatement("select saleID, p.propertyID, p.propertyName, concat(p.locality, ', ', p.district) as 'propertyAddress', agentID, buyerID, sellerID, sellingPriceOrRent as 'selling price', dateOfSale from sales, property p where p.propertyId = sales.propertyID and not isRented and year(dateOfSale) = "+year+" order by dateOfSale");
			else this.preparedstmt = this.conn.prepareStatement("select saleID, p.propertyID, p.propertyName, concat(p.locality, ', ', p.district) as 'propertyAddress', agentID, buyerID, sellerID, sellingPriceOrRent as 'selling price', dateOfSale from sales, property p where p.propertyId = sales.propertyID and not isRented and year(dateOfSale) = "+year);
			this.resultSet = this.preparedstmt.executeQuery();
			Report.resultset = this.resultSet;
			Report.columns = 10;
			Report.main(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void rentResultAgentYear(String agentID, String year, boolean sortByPrice, boolean sortByDate) {
		try {
			if(sortByPrice) this.preparedstmt = this.conn.prepareStatement("select saleID, p.propertyID, p.propertyName, concat(p.locality, ', ', p.district) as 'propertyAddress', agentID, buyerID, sellerID, sellingPriceOrRent as 'rent', dateOfSale from sales, property p where p.propertyId = sales.propertyID and agentID = "+agentID+" and isRented and year(dateOfSale) = "+year+" order by sellingPriceOrRent");
			else if(sortByDate) this.preparedstmt = this.conn.prepareStatement("select saleID, p.propertyID, p.propertyName, concat(p.locality, ', ', p.district) as 'propertyAddress', agentID, buyerID, sellerID, sellingPriceOrRent as 'rent', dateOfSale from sales, property p where p.propertyId = sales.propertyID and agentID = "+agentID+" and isRented and year(dateOfSale) = "+year+" order by dateOfSale");
			else this.preparedstmt = this.conn.prepareStatement("select saleID, p.propertyID, p.propertyName, concat(p.locality, ', ', p.district) as 'propertyAddress', agentID, buyerID, sellerID, sellingPriceOrRent as 'rent', dateOfSale from sales, property p where p.propertyId = sales.propertyID and agentID = "+agentID+" and isRented and year(dateOfSale) = "+year);
			this.resultSet = this.preparedstmt.executeQuery();
			Report.resultset = this.resultSet;
			Report.columns = 10;
			Report.main(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public void rentResult(String agentID, boolean sortByPrice, boolean sortByDate) {
		try {
			if(sortByPrice) this.preparedstmt = this.conn.prepareStatement("select saleID, p.propertyID, p.propertyName, concat(p.locality, ', ', p.district) as 'propertyAddress', agentID, buyerID, sellerID, sellingPriceOrRent as 'rent', dateOfSale from sales, property p where p.propertyId = sales.propertyID and agentID = "+agentID+" and isRented order by sellingPriceOrRent");
			else if(sortByDate) this.preparedstmt = this.conn.prepareStatement("select saleID, p.propertyID, p.propertyName, concat(p.locality, ', ', p.district) as 'propertyAddress', agentID, buyerID, sellerID, sellingPriceOrRent as 'rent', dateOfSale from sales, property p where p.propertyId = sales.propertyID and agentID = "+agentID+" and isRented order by dateOfSale");
			else this.preparedstmt = this.conn.prepareStatement("select saleID, p.propertyID, p.propertyName, concat(p.locality, ', ', p.district) as 'propertyAddress', agentID, buyerID, sellerID, sellingPriceOrRent as 'rent', dateOfSale from sales, property p where p.propertyId = sales.propertyID and agentID = "+agentID+" and isRented");
			this.resultSet = this.preparedstmt.executeQuery();
			Report.resultset = this.resultSet;
			Report.columns = 10;
			Report.main(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public void rentResultYear(String year, boolean sortByPrice, boolean sortByDate) {
		try {
			if(sortByPrice) this.preparedstmt = this.conn.prepareStatement("select saleID, p.propertyID, p.propertyName, concat(p.locality, ', ', p.district) as 'propertyAddress', agentID, buyerID, sellerID, sellingPriceOrRent as 'rent', dateOfSale from sales, property p where p.propertyId = sales.propertyID and isRented and year(dateOfSale) = "+year+" order by sellingPriceOrRent");
			else if(sortByDate) this.preparedstmt = this.conn.prepareStatement("select saleID, p.propertyID, p.propertyName, concat(p.locality, ', ', p.district) as 'propertyAddress', agentID, buyerID, sellerID, sellingPriceOrRent as 'rent', dateOfSale from sales, property p where p.propertyId = sales.propertyID and isRented and year(dateOfSale) = "+year+" order by dateOfSale");
			else this.preparedstmt = this.conn.prepareStatement("select saleID, p.propertyID, p.propertyName, concat(p.locality, ', ', p.district) as 'propertyAddress', agentID, buyerID, sellerID, sellingPriceOrRent as 'rent', dateOfSale from sales, property p where p.propertyId = sales.propertyID and isRented and year(dateOfSale) = "+year);
			this.resultSet = this.preparedstmt.executeQuery();
			Report.resultset = this.resultSet;
			Report.columns = 10;
			Report.main(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
