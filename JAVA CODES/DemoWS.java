package project;

//package com.webServiceTest;

import java.net.*;
import java.io.*;
import java.util.ArrayList;


class DemoWS {

    public Socket clientSocket;
    public DataOutputStream outToServer;
    public BufferedReader inFromServer;
    
   
    
    public DemoWS() {}

	/**
     * a private function that parses the input from the socket, and places them in a string array
     * @param row   a string with "/" as a split indicator 
     * @return      String[] whose each index contains an element from row   
     */
    private String[] convert(String row){
        String[] partitionedRow = row.split("/");
        return partitionedRow;
    }
    
    /**
     * function that converts a 2d arraylist<string[]> to a 2d array String[][]
     * @param table the arraylist<String[]> which represents query results
     * @return String[][]
     */
    private String[][] convertToArray(ArrayList<String[]> table){
    	String[][] t = new String[table.size()][7];
        for(int i=0; i<table.size();i++) {
        	for(int j=0; j<7; j++) {
        		t[i][j] = table.get(i)[j];
        	}
        }
        return t;
    }

    /**
     * Web service operation: sends the LoginLookup query code, username, and password to the multi-threaded database server
     * @param username
     * @param password
     * @return string which prints the login in status (either login successful or user not found)
     */
    public String login( String username, String password) {
        String login = "User not found";
        try {
            clientSocket = new Socket("localhost", 55000);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 

            //sends code and parameters to the server 
            outToServer.writeBytes("loginLookUp"+"\n");
            outToServer.writeBytes(username+"\n");
            outToServer.writeBytes(password+"\n");
            clientSocket.shutdownOutput();

            login = inFromServer.readLine();
        } catch (IOException e1) {
            e1.getMessage();
        }
        return login;
    }

    /**
     * Web service operation: displays the students absent on a given lecture date and their number 
     * @param date of the lecture taken from the user 
     * @return String[][]: stores query results. Last row contains the number of absent students on the given lecture date.
     */
    public String[][] callViewAbsentees(String date) {
        ArrayList<String[]> table = new ArrayList<>();//to store query results
        String[][] tableArray = null; 
        try {
            clientSocket = new Socket("localhost", 55000);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 

            //sends code and parameters to the server 
            outToServer.writeBytes("viewAbsentees"+"\n");
            outToServer.writeBytes(date+"\n");
            clientSocket.shutdownOutput();
            //storing query results in arraylist
            String result = inFromServer.readLine();
            while(!result.equals("Exit")){                
            	table.add(convert(result));
                result = inFromServer.readLine();
            }
            clientSocket.close();
            //sends number of absent students in a string    
            
        } catch (IOException e1) {
            e1.getMessage();
        } 
        
        //convert arrayList to String[][]
        tableArray = convertToArray(table);
        //stores tableArray(String[][]) and absentees(String[]) in an object array
        
        return tableArray;
        
    }

    /**
     * Web service operation: displays the attendance and absence stats of a lecture 
     * @param date  a string passed from the user. It indicates the desired lecture date
     * @return String[][] which contains the query results in a 2D arraylist and attendance stats in a string  
     */
    public String[][] callViewAttendanceRecords(String date) {
    	ArrayList<String[]> table = new ArrayList<>();//this is to store the query results in a 2D arraylist
         String[][] tableArray = null; 
        try {
            clientSocket = new Socket("localhost", 55000);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 

            //sends code and parameters to the server 
            outToServer.writeBytes("viewAttendanceRecords"+"\n");
            outToServer.writeBytes(date+"\n");
            clientSocket.shutdownOutput();
            
            String result = inFromServer.readLine();
            
            //store query results
            while(!result.equals("Exit")){
                table.add(convert(result));
                result = inFromServer.readLine();
            } 
        } catch (IOException e1) {
            e1.getMessage();
        }  
        tableArray = convertToArray(table);
        return tableArray;    
    }

    /**
     * Web service operation: returns a set of students who attended more than 50% of the lecture
     * @param date chosen by the user
     * @return Stirng[][] which holds the results for the query
     */
    public String[][] callRemainedMoreThanHalf(String date) {
        ArrayList<String[]> table = new ArrayList<>();
        String[][] tableArray = null;
        try {
            clientSocket = new Socket("localhost", 55000);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 

            //sends code and parameters to the server 
            outToServer.writeBytes("callRemainedMoreThanHalf"+"\n");
            outToServer.writeBytes(date+"\n");
            clientSocket.shutdownOutput();
            //print query results
            String result = inFromServer.readLine();
            while(!result.equals("Exit")) {
                table.add(convert(result));
                result = inFromServer.readLine();
            }
        } catch (IOException e1) {
            e1.getMessage();
        } 
        tableArray = convertToArray(table);
        return tableArray;
    }

    /**
     * Web service operation: searches the database for the attendance records of a specific student
     * @param name chosen by the user 
     * @return ArrayList<String[]> which holds the query results
     */
    public String[][] callSearchByName(String name) {
     ArrayList<String[]> table = new ArrayList<>();
     String[][] tableArray = null; 
        try {
            clientSocket = new Socket("localhost", 55000);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 

            //sends code and parameters to the server 
            outToServer.writeBytes("searchByName"+"\n");
            outToServer.writeBytes(name+"\n");
            clientSocket.shutdownOutput();
            //print query results
            String result = inFromServer.readLine();
            while(!result.equals("Exit")) {
                table.add(convert(result));
                result = inFromServer.readLine();
            }
        } catch (IOException e1) {
            e1.getMessage();
        } 
        tableArray = convertToArray(table);
        return tableArray;
    }

    /**
     * Web service operation: takes in a lecture date, student id, and new TIME_IN and updates the database entry
     * @param date string: date of lecture chosen by the user
     * @param id   string: ID of the student the user wants to modify
     * @param modifiedIn  string of the format HH:MM:SS 
     * @return string update: update successful/not successful 
     */
    public String callModifyTimeIn( String date, String id, String modifiedIn) {
        String update = "";
        try {
            clientSocket = new Socket("localhost", 55000);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 

            //sends code and parameters to the server 
            outToServer.writeBytes("callModifyTimeIn"+"\n");
            outToServer.writeBytes(date+"\n");
            outToServer.writeBytes(id+"\n");
            outToServer.writeBytes(modifiedIn+"\n");
            clientSocket.shutdownOutput();
            //store update status
		            
            update = inFromServer.readLine();
        } catch (IOException e1) {
            e1.getMessage();
        } 
        return update;
    }

    /**
     * Web service operation: takes in a lecture date, student id, and new TIME_OUT and updates the database entry
     * @param date string: date of lecture chosen by the user
     * @param id   string: ID of the student the user wants to modify
     * @param modifiedOut  string of the format HH:MM:SS 
     * @return  string update: update successful/not successful 
     */
    public String callModifyTimeOut(String date, String id, String modifiedOut) {
        String update = "";
        try {
            clientSocket = new Socket("localhost", 55000);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 

            //sends code and parameters to the server 
            outToServer.writeBytes("callModifyTimeIn"+"\n");
            outToServer.writeBytes(date+"\n");
            outToServer.writeBytes(id+"\n");
            outToServer.writeBytes(modifiedOut+"\n");
            clientSocket.shutdownOutput();
            //store update status        
            update = inFromServer.readLine();
        } catch (IOException e1) {
            e1.getMessage();
        } 
        return update;
    }

    
//    public static void main (String args []) {
//    		DemoWS test = new DemoWS();
//    		System.out.println("Connected to Server!");
//    
//    }
}
