import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Student {
	private static final String URL="jdbc:postgresql://localhost:5432/user";
	private static final String USERNAME="postgres";
	private static final String PASSWORD="pavani";
	private static final String create="create table student01(name varchar(5),standard int,contactNumber int);";
	private static final String insert="insert into student01 values(?,?,?);";
	private static final String select="select * from student01;";
	private static final String update="update student01 set contactNumber=? where contactNumber =?;";
	private static final String delete="delete from student01 where contactNumber=?;";
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("loaded");
			Connection con=DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("connected");
			for(;;){
				System.out.println("please choose options 1-create 2-retrive 3-update 4-delete 0-exit");
				int option=scan.nextInt();
				switch(option) {
				case 0:
					System.exit(0);
				case 1:
					create(scan,con);
					break;
				case 2:
					retrive(con);
					break;
				case 3:
					update(scan,con);
					break;
				case 4:
					delete(scan,con);
					break;
				default:
					System.out.println("invalid");	
				}


			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void create(Scanner scan, Connection con) {
		try {
			PreparedStatement ps=con.prepareStatement(insert);
			System.out.println("enter Student name");
			String name=scan.next();
			System.out.println("enter Student standard");
			int standard=scan.nextInt();
			System.out.println("enter Student number");
			int number=scan.nextInt();
			ps.setString(1, name);
			ps.setInt(2,standard);
			ps.setInt(3, number);
			ps.execute();
			System.out.println("inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static void retrive(Connection con) {
		try {
			PreparedStatement ps=con.prepareStatement(select);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				System.err.println("name "+rs.getString(1)+" standard "+rs.getInt(2)+" number "+rs.getInt(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static void update(Scanner scan, Connection con) {
		try {
			PreparedStatement ps=con.prepareStatement(update);
			System.out.println("enter updated number");
			int updatedNumber=scan.nextInt();
			System.out.println("enter previous number");
			int number=scan.nextInt();
			ps.setInt(1,updatedNumber);
			ps.setInt(2,number);
			ps.execute();
			System.out.println("number updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void delete(Scanner scan, Connection con) {
		try {
			PreparedStatement ps=con.prepareStatement(delete);
			System.out.println("enter number for delete");
			int number=scan.nextInt();
			ps.setInt(1, number);
			ps.execute();
			System.out.println("deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



}
