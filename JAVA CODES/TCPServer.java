package project;

import java.io.BufferedReader;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.ServerSocket;

import java.sql.*;


public class TCPServer {

	private int port = 55000;
  
	private ServerSocket serverSocket;

	//URL of DB server and authentication string
	static final String dbURL = "jdbc:mysql://localhost:3306/eece351_classdb" + "user=root&password=7+HUJ89k_";
	static final String db2URL = "jdbc:mysql://localhost:3306/instructors_db" + "user=root&password=7+HUJ89k_";
//	static final String dbURL = "jdbc:sqlite:C://sqlite//test_s.db";
//	static final String db2URL = "jdbc:sqlite:C://sqlite//test_i.db";
//	
	public TCPServer() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}

	  public void acceptConnections() {
	
		  try {
			  //general socket
			  serverSocket = new ServerSocket(port);
		  }
		  catch (IOException e) {
		      System.err.println("ServerSocket instantiation failed!");
		      e.printStackTrace();
		      System.exit(0);
		  }
		  // wait for connection request from GUI
		  while (true) {
			  try {
				  System.out.println("waiting for client");
				  Socket newConnection = serverSocket.accept();
				  //System.out.println("Connected to Server!");
			 
				  ServerThread st = new ServerThread(newConnection);
				  System.out.println("thread");
				  new Thread(st).start();
			  }
			  
			  catch (IOException ioe) {
				  System.err.println("Connection to Server Failed.");
			  }
		  }
	  }
	
	  public static void main(String args[]) {
	
		  TCPServer server = null;
		  try {
			  //Instantiate an object of this class. This will load the JDBC database driver
			  server = new TCPServer();
		  }
		  catch (ClassNotFoundException e) {
		      System.out.println("Failed to load JDBC driver.");
		      e.printStackTrace();
		      System.exit(1);
		  }
		  //call this function, which will start it all...
		  server.acceptConnections();
	  }
	  
	  //Internal class
	  class ServerThread implements Runnable {
		  
		  private Socket socket;
		  private BufferedReader inFromClient;
		  private DataOutputStream outToClient;
		  private String [] arguments = new String [7];
	
	    public ServerThread(Socket socket) {
	    	this.socket = socket;
	    }
	    
	    public void run() {

	    	int i = 0;
	    	try {
	    		inFromClient = new BufferedReader (new InputStreamReader(socket.getInputStream())); 
	    		outToClient = new DataOutputStream(socket.getOutputStream());
	    		String message = inFromClient.readLine();
	    		
	    		while( message != null) {
	    			arguments[i] = message;
	    			i++;
	    			message = inFromClient.readLine();
	    		}
	    	}
	    	catch (IOException ioe) {
	    		ioe.printStackTrace();
	    	}
	    	
	    	// CASES
	    	if (arguments[0].equals("loginLookUp")) {	//arguments = {"loginLookUp", 'username', 'password'}
	    		try {
	    			loginLookUp(arguments[1], arguments[2]);
				} catch (IOException e1) {
					System.out.println("Error calling viewAbsentees");
					e1.printStackTrace();
				}
	    	}
	    	
	    	else if (arguments[0].equals("viewAbsentees")) {	//arguments = {"viewAbsentees", 'date'}
				try {
					viewAbsentees(arguments[1]);
				} catch (IOException e1) {
					System.out.println("Error calling viewAbsentees");
					e1.printStackTrace();
				}
	    	}
	    	else if (arguments[0].equals("viewAttendanceRecords")) {	//arguments = {"viewAttendanceRecords", 'date'}
	    		try {
					viewAttendanceRecords(arguments[1]);
				} catch (IOException e) {
					System.out.println("Error calling viewAttendanceRecords");
					e.printStackTrace();
				}
	    	}
	    	
	    	else if (arguments[0].equals("remainedMoreThanHalf")) {	// arguments = {"remainedMoreThanHalf", 'date'}
	    		try {
	    			remainedMoreThanHalf(arguments[1]);
				} catch (IOException e) {
					System.out.println("Error calling remainedMoreThanHalf");
					e.printStackTrace();
				}
	    	}
	    	
	    	else if (arguments[0].equals("searchByName")) {	// arguments = {"searchById",'name'}
	    		try {
	    			searchByName(arguments[1]);
				} catch (IOException e) {
					System.out.println("Error calling searchById");
					e.printStackTrace();
				}
	    	}
	    	
	    	else if (arguments[0].equals("modifyTimeIn")) {	// arguments = {"modifyTimeIn",'date', 'ID', 'modifiedIn'}
	    		try {
	    			modifyTimeIn(arguments[1], arguments[2], arguments[3]);
				} catch (IOException e) {
					System.out.println("Error calling modifyTimeIn");
					e.printStackTrace();
				}
	    	}
	    	
	    	else if (arguments[0].equals("modifyTimeOut")) {	// arguments = {"modifyTimeOut",'date', 'ID', 'modifiedOut'}
	    		try {
	    			modifyTimeOut(arguments[1], arguments[2], arguments[3]);
				} catch (IOException e) {
					System.out.println("Error calling modifyTimeOut");
					e.printStackTrace();
				}
	    	}
    	 	
	    try {
	    	outToClient.writeBytes("Exit"+"\n");//When the GUI receives "Exit", it closes the connection
	        System.out.println("closing socket");
	        socket.close();
	      }catch (IOException e) {
	    	  System.out.println("Error closing connection with server.");
	      }
	    }
	    
	  //QUERY METHODS
	    
	    /**
	     * connects to the Instructors Database and gives access to the user if the username and password are registered
	     * @param username
	     * @param pass
	     * @throws IOException
	     */
	    private void loginLookUp(String username, String pass) throws IOException {
	    	Connection conn = null;
	    	try {
	    		conn = DriverManager.getConnection(db2URL);
				PreparedStatement logIn = conn.prepareStatement("SELECT * FROM instructor_records WHERE USERNAME = ? AND PASSWORD = ?");
				logIn.setString(1, username);
				logIn.setString(2, pass);
				ResultSet rs = logIn.executeQuery();
				
	    		if (rs.next()) {
	    			String row = rs.getRow()+"./"+rs.getString("NAME")+'/'
							+rs.getString("USERNAME")+'/'
	    					+rs.getString("PASSWORD");
	    			outToClient.writeBytes(row+"\n");
//	    			String welcome = "Welcome "+rs.getString("NAME")+'!';
//	    			outToClient.writeBytes(welcome +"\n");
	    		}
	    		else {
	    			outToClient.writeBytes("User not found"+"\n");
	    		}
	    	}catch (SQLException | IOException e){;
	    		System.out.println(e.getMessage());
			    outToClient.writeBytes("ERROR: Error Connecting to Server or IO Exception Occurred" +"\n");
	    	}finally {
	    		
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
					}
				}
			}
	    }
	    
	    
	   /** Displays names of absent students on a provided lecture date
	    * includes number of absent students
	    * @param String date of lecture in YYYY-MM-DD format
	    * @return String of absent students and their number
	    * @throws IOException, SQLException
	    */
	    private void viewAbsentees(String date) throws IOException {
	       	int count = 0;
	    	Connection conn = null;
	    	try {
	    		conn = DriverManager.getConnection(dbURL);
	    		Statement st = conn.createStatement();
	    		String query = "SELECT * FROM student_records "
	    					+ "WHERE TOTAL_TIME = '0hr 00mins 00s' "
	    					+ "and DATE_of_LECT = '" + date+"'";
	    		ResultSet rs = st.executeQuery(query);
	    		
	    		if (!rs.next()) { 
					outToClient.writeBytes("ERROR: We could not find what you're looking for!\nPlease check the date you entered."+"\n");
				}
	    		else {
	    			do{
		    			String row = rs.getRow()+"./"+rs.getString("NAME")+'/'
								+rs.getString("ID")+'/'
		    					+rs.getString("DATE_of_LECT")+'/'
								+rs.getString("TIME_IN")+'/'
								+rs.getString("TIME_OUT")+'/'
								+rs.getString("TOTAL_TIME");	
		    			outToClient.writeBytes(row+"\n");	// this sends the current row string to client socket
		    			count++;
		    		}while (rs.next()); 
	    			String s_count = String.valueOf(count);
		    		outToClient.writeBytes("Absent studnets on " + date + " : "+s_count+'\n');
	    		}
	    	}
	    	catch (SQLException | IOException e) {
				System.out.println(e.getMessage());
			    outToClient.writeBytes("ERROR: Error Connecting to Server or IO Exception Occurred");
			}
	    	finally {
	    		
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
					}
				}
			}
	    }
	    
	    /**
		 * Displays the records of attendance of all students in a table format
		 * includes # of students, ID, Date of lecture, Time in, time out, total time spent in lecture 
		 * Displays percentage of absentees and attendees
		 * @param String date of lecture in YYYY-MM-DD format
	     * @throws IOException, SQLException
		 */
	    private void viewAttendanceRecords(String date) throws IOException {
			double attending = 0.0; 
			double absent = 0.0;
			String row;
	    	Connection conn = null;
	    	try {
	    		conn = DriverManager.getConnection(dbURL);
	    		Statement st = conn.createStatement();
	    		String query = "SELECT * FROM student_records WHERE DATE_of_LECT = '"+date+"'";
	    		ResultSet rs = st.executeQuery(query);
	    		
	    		if (!rs.next()) { 
					outToClient.writeBytes("ERROR: We could not find what you're looking for!\nPlease check the date you entered and try again."+"\n");
				}
				
				else {					
		    		do{
		    			 row = rs.getRow()+"./"+rs.getString("NAME")+'/'
								+rs.getString("ID")+'/'
		    					+rs.getString("DATE_of_LECT")+'/'
								+rs.getString("TIME_IN")+'/'
								+rs.getString("TIME_OUT")+'/'
								+rs.getString("TOTAL_TIME");
						outToClient.writeBytes(row+"\n");
						if(rs.getString("TOTAL_TIME").equals("0hr 00mins 00s")) {
							absent++;
						}
						else {
							attending++;
						}
		    		}while (rs.next());
		    		//Send statistics to GUI
					double attendance = (attending/(attending+absent)) *100;
					double absence = 100-attendance;
					String s_att = String.valueOf(attendance);
					String s_abs = String.valueOf(absence);
					outToClient.writeBytes("Statistics:/Attendance:/"+ s_att +"%/Absence:/" + s_abs +"%"+"\n");
				}
	    	}catch(SQLException | IOException e) {
				e.printStackTrace();
				outToClient.writeBytes("ERROR: Error Connecting to Server or IO Exception Occurred");
			}
	    	finally {
	    		if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
					}
				}
	    	}
	    }
	    
		/**
		 * Displays the ID, Name, date of lecture, time in, time out, total time of students who attended more than half the lecture time
		 * @throws IOException, SQLException
		 */
		private void remainedMoreThanHalf(String date) throws IOException {
			Connection conn = null;
			String row;
			try{
				conn = DriverManager.getConnection(dbURL);
				
				Statement half = conn.createStatement();
				ResultSet halfTime = half.executeQuery("SELECT * FROM student_records WHERE DATE_of_LECT = '" + date + "' and TOTAL_TIME >= '0hr 37mins 00s'");
				
				if (!halfTime.next()) { 
					outToClient.writeBytes("ERROR: We could not find what you're looking for!\nPlease check the date you entered and try again." +"\n");
				}
				
				else {
					do {
						row = halfTime.getRow()+"./"+halfTime.getString("NAME")+'/'
								+halfTime.getString("ID")+'/'
								+halfTime.getString("DATE_of_LECT")+'/'
								+halfTime.getString("TIME_IN")+'/'
								+halfTime.getString("TIME_OUT")+'/'
								+halfTime.getString("TOTAL_TIME");
						outToClient.writeBytes(row+'\n');
					}while(halfTime.next());
				}
			}catch(SQLException | IOException e) {
				outToClient.writeBytes("Error Connecting to Server or IO Exception Occurred");
				e.printStackTrace();
			}
			finally {
	    		if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
					}
				}
	    	}
		}
		
		/**
		 * takes student name as input from the user and returns the attendance records 
		 * (i.e date of lecture, time in, time out, and total time) 
		 * of the student
		 * @throws IOException, SQLException
		 */
		private void searchByName(String name) throws IOException {
			Connection conn = null;
			String row;
			try{	
				conn = DriverManager.getConnection(dbURL);
				Statement st = conn.createStatement();
				String query = "SELECT * FROM student_records WHERE NAME = '" + name+"'"; 
				ResultSet rs = st.executeQuery(query);
	    		if (!rs.next()) { 
					outToClient.writeBytes("ERROR: We could not find what you're looking for!\nPlease make sure you entered a valid student name." +"\n");
				}
				
				else {
					//print the result
					do {
						 row = rs.getRow()+"./"+rs.getString("NAME")+'/'
									+rs.getString("ID")+'/'
			    					+rs.getString("DATE_of_LECT")+'/'
									+rs.getString("TIME_IN")+'/'
									+rs.getString("TIME_OUT")+'/'
									+rs.getString("TOTAL_TIME");
						outToClient.writeBytes(row+"\n");
					}while(rs.next());
				}
				
			}catch(SQLException | IOException e) {
				outToClient.writeBytes("ERROR: Error Connecting to Server or IO Exception Occurred");
				e.printStackTrace();
			}
			finally {
	    		if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
					}
				}
	    	}
		}
		
		/**
		 * allows the user to input the new TIME_IN
		 * modifies the TIME_IN of the specified student and recomputes the TOTAL_TIME attribute
		 * @throws IOException, SQLException
		 */
		private void modifyTimeIn(String date, String id, String modifiedIn) throws IOException {
			//checking if correct time format:
			Connection conn = null;
			try{
	    		conn = DriverManager.getConnection(dbURL);
				//prepare the query 
				PreparedStatement modifyIn = conn.prepareStatement("SELECT * FROM student_records WHERE ID = ? AND DATE_of_LECT = ?");
				modifyIn.setString(1, id);
				modifyIn.setString(2, date);
				ResultSet rs = modifyIn.executeQuery();
				
				String total = "";
				if(rs.next()) {
					//recompute TOTAL_TIME
					total = totalTime(modifiedIn, rs.getString("TIME_OUT"));
				}
				
				PreparedStatement update = conn.prepareStatement("UPDATE student_records SET TIME_IN = ?, TOTAL_TIME = ? WHERE ID = ? AND DATE_of_LECT = ?");
				update.setString(1, modifiedIn);
				update.setString(2, total);
				update.setString(3, id);
				update.setString(4, date);
				int count = update.executeUpdate();
				
				if(count>0){
					outToClient.writeBytes("Time_in successfully updated"+"\n");
				}		
				else {
					//the user might have specified the wrong ID
					outToClient.writeBytes("ERROR: Error updating. Please check your entries."+"\n");
				}
				conn.close();
			}catch(SQLException | IOException e) {
				outToClient.writeBytes("ERROR: Error Connecting to Server or IO Exception Occurred");
				e.printStackTrace();
			}
		}
		
		/**
		 * allows the user to input the new TIME_OUT
		 * modifies the TIME_OUT of the specified student and recomputes the TOTAL_TIME attribute
		 * @throws IOException, SQLException
		 */
		private void modifyTimeOut(String date, String id, String modifiedOut) throws IOException {
			Connection conn = null;
			try{
	    		conn = DriverManager.getConnection(dbURL);
				//prepare the query 
				PreparedStatement modifyOut = conn.prepareStatement("SELECT * FROM student_records WHERE ID = ? AND DATE_of_LECT = ?");
				modifyOut.setString(1, id);
				modifyOut.setString(2, date);
				ResultSet rs = modifyOut.executeQuery();
				
				String total = "";
				if(rs.next()) {
					//recompute TOTAL_TIME
					total = totalTime(modifiedOut, rs.getString("TIME_OUT"));
				}
				
				PreparedStatement update = conn.prepareStatement("UPDATE student_records SET TIME_IN = ?, TOTAL_TIME = ? WHERE ID = ? AND DATE_of_LECT = ?");
				update.setString(1, modifiedOut);
				update.setString(2, total);
				update.setString(3, id);
				update.setString(4, date);
				int count = update.executeUpdate();
				
				if(count>0){
					outToClient.writeBytes("Time_out successfully updated"+"\n");
				}		
				else {
					//the user might have specified the wrong ID
					outToClient.writeBytes("ERROR: Error updating. Please check your entries."+"\n");
				}
				System.out.println("closing connection");
				conn.close();
			}catch(SQLException | IOException e) {
				outToClient.writeBytes("Error Connecting to Server or IO Exception Occurred");
				e.printStackTrace();
			}
		}
		
		
		/**
		 * A function that calculates the total time the student spent in a lecture
		 * @param	String timeIn, String timeOut which are the times the student entered and left the lecture both in "HH:MM:SS" format
		 * @return	String total which is timeOut-timeIn in the following format "Hhr MMmins SSs"
		 * @throws IOException 
		 */
		private String totalTime(String timeIn, String timeOut) throws IOException {
			String totalTime ="";
			if(timeIn.charAt(2) == ':' && timeIn.charAt(5) == ':' && timeOut.charAt(2) == ':' && timeOut.charAt(5) == ':'){
			
				//extract hrs, min, secs of each time format and convert to seconds  
				int in = Integer.valueOf(timeIn.substring(0,2))*3600 
						+ Integer.valueOf(timeIn.substring(3, 5))*60
						+ Integer.valueOf(timeIn.substring(6,8));
				int out = Integer.valueOf(timeOut.substring(0,2))*3600
						+ Integer.valueOf(timeOut.substring(3, 5))*60
						+ Integer.valueOf(timeOut.substring(6,8));
		
				//subtract the out time from the in time
				int total = out - in; 
				int hr = total / 3600; 
				int min = (total - 3600*hr) / 60; 
				int sec = total - 3600*hr - 60*min;
				
				//cast to string
				String h = String.valueOf(hr);
				String m = String.valueOf(min);
				String s = String.valueOf(sec);
				
				//add necessary zeroes for HH:MM:SS format
				if(m.length()<2) {
					m = "0"+m;
				}
				if(s.length()<2) {
					s = "0"+s;
				}
				
				totalTime = h+"hr "+m+"mins "+s+"s"; 
				
			}else{
				outToClient.writeBytes("Wrong input. Your time should be in HH:MM:SS format"+"\n");
			}
			return totalTime;
			
		}
	  }
	}
	
	
