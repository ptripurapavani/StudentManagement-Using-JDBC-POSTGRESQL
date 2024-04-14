package com.ignite.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC {
	private static final String URL="jdbc:mysql://localhost:3306/boot";
	private static final String USER_NAME="root";
	private static final String PASSWORD="Tripurapavani@22";
	private static final String create=
			"create table student01 (name varchar(10),standard int,contactNumber int);";
	private static final String insert=
			"insert into student01 values(?,?,?);";
	private static final String select="select * from student01;";
	private static final String update="update student01 set name=? where contactNumber=?;";
	private static final String delete="delete from student01 where contactNumber=?;";
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		Connection con=null;
		PreparedStatement ps;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("load and register successfully");
			con=DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			System.out.println("connection established successfully"+con);
		
		for(; ;) {
			System.out.println("please choose the options : (1-create,2-retrive,3-update,4-delete,0-exit)");
			int option=scan.nextInt();
			switch(option) {
			case 1:
				create(scan,con);
				break;
			case 2:
				retrival(con);
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
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static void create(Scanner scan, Connection con) {
		try {
			PreparedStatement pstmt=con.prepareStatement(insert);
			System.out.println("enter name");
			String name=scan.next();
			int standard=scan.nextInt();
			int number=scan.nextInt();
			pstmt.setString(1,name);
			pstmt.setInt(2,standard);
			pstmt.setInt(3, number);
			pstmt.execute();
			System.out.println("inserted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static void retrival(Connection con) {
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
			System.out.println("enter updated name");
			String name=scan.next();
			System.out.println("provide previous number to update");
			int number=scan.nextInt();
			ps.setInt(2, number);
			ps.setString(1,name);

			ps.executeUpdate();
			System.out.println("updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	private static void delete(Scanner scan, Connection con) {
		try {
			PreparedStatement ps=con.prepareStatement(delete);
			System.out.println("enter number");
			int number=scan.nextInt();
			ps.setInt(1,number);
			ps.execute();
			System.out.println("deleted successfully");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}



