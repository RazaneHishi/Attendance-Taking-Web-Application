package project;

import java.io.BufferedReader;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class testClient {

		private Socket clientSocket = null; 
		private BufferedReader inFromUser = null; 
		private BufferedReader inFromServer = null; 
		private DataOutputStream outToServer = null; 
		
		public static void main(String args[]) throws IOException 
	    { 
			//change ip and port to suit ur pc 
			testClient client = new testClient("localhost", 55000); 
//			String s = ""
//			ArrayList<String[]> t = new ArrayList<>();
//			String[] row1 = ("1./lama/1/2020-11-18/08:00:00/9:00:00/1:00:00").split("/");
//			String[] row2 = ("2./rana/2/2020-11-18/08:01:00/9:15:00/1:14:00").split("/");
//			t.add(row1);t.add(row2);
//			for(int i=0; i<2; i++) {
//				for(int j=0; j<7; j++) {
//					System.out.println(t.get(i)[j]);
//				}
//			}
	    }
		
		/**
		 * initializes socket, input, and output streams
		 * @param IpAddress
		 * @param port
		 */
	
		public testClient(String IpAddress, int port) {
			try {
				clientSocket = new Socket(IpAddress, port); 
				System.out.println("Connection established");
				
				inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
				outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
				inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
//				searchByName();
//				remainedMoreThanHalf();
//				viewAbsentees();
				//viewAttendanceRecords();
				LoginLookup();
//				modifyTimeOut();
				clientSocket.shutdownOutput();
				System.out.println("----------------");
				String result = inFromServer.readLine();
				ArrayList<String[]> table = new ArrayList<>();
				String[] stats = null;
		            //prints stats
		            while(!result.equals("Exit")){
		                table.add(convert(result));
		                result = inFromServer.readLine();
		            }
		            String[][] one = new String[table.size()][7]; 
		            for(int i=0; i<table.size();i++) {
		            	for(int j=0; j<4; j++) {
		            		one[i][j] = table.get(i)[j];
		            		System.out.println(one[i][j]);
		            	}
		            }
		            System.out.println("--------------------------");
		            System.out.println("done.");
	               end();
			}catch(UnknownHostException uhe) {
				System.out.println(uhe);
			}catch(IOException ioe) {
				System.out.println(ioe);
			}
			
		}

		/**
		 * closes sockets and streams
		 * @throws IOException 
		 */
		public void end() throws IOException {
			clientSocket.close();
			inFromUser.close();
		}
		
		/**
		 * calls the corresponding function
		 * @throws IOException 
		 */
		public void viewAttendanceRecords() throws IOException {
			String[] packet = new String[] {"viewAttendanceRecords","2020-11-18"};
			for(int i=0; i<packet.length; i++) {
				outToServer.writeBytes(packet[i]+"\n");
				System.out.println("Sent: "+ packet[i]);
			}

		}
		
		/**
		 * calls the corresponding function
		 * @throws IOException 
		 */
		public void modifyTimeIn() throws IOException {
			String[] packet = new String[] {"modifyTimeIn","2020-11-18","200000076","08ff5:00"};
			for(int i=0; i<packet.length; i++) {
				outToServer.writeBytes(packet[i]+"\n");
				System.out.println("Sent: "+ packet[i]);
			}
		}
		
		public void viewAbsentees() throws IOException {
			String[] packet = new String[] {"viewAbsentees","2020-11-18"};
			for(int i=0; i<packet.length; i++) {
				outToServer.writeBytes(packet[i]+"\n");
				System.out.println("Sent: "+ packet[i]);
			}
		}
		public void remainedMoreThanHalf() throws IOException {
			String[] packet = new String[] {"remainedMoreThanHalf","2020-11-18"};
			for(int i=0; i<packet.length; i++) {
				outToServer.writeBytes(packet[i]+"\n");
				System.out.println("Sent: "+ packet[i]);
			}
		}
		public void searchByName() throws IOException {
			String[] packet = new String[] {"searchByName","wsouf"};
			for(int i=0; i<packet.length; i++) {
				outToServer.writeBytes(packet[i]+"\n");
				System.out.println("Sent: "+ packet[i]);
			}
		}
		
		public void modifyTimeOut() throws IOException {
			String[] packet = new String[] {"modifyTimeOut","2020-11-18","202045076","09:00:05"};
			for(int i=0; i<packet.length; i++) {
				outToServer.writeBytes(packet[i]+"\n");
				System.out.println("Sent: "+ packet[i]);
			}
		}
		
		public void LoginLookup() throws IOException {
			String[] packet = new String[] {"loginLookUp","HassanArtail00", "haa00"};
			for(int i=0; i<packet.length; i++) {
				outToServer.writeBytes(packet[i]+"\n");
				System.out.println("Sent: "+ packet[i]);
			}
		}
		
		/**
		 * prints input from socket (a.k.a resultset)
		 * @throws IOException 
		 */

		public String[] convert(String row) {
			String[] part = row.split("/");
	
			return part;
		}
			


}
