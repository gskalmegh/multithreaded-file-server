/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package fclient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author ghanshyam
 */
public class Fclient {

    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
     BufferedReader cin=new BufferedReader(new InputStreamReader(System.in));
       OutputStream fout=new FileOutputStream("./copy.mkv");
      
      
     String host;
     String filenm;
     boolean lock=false;
     int seqnum=0;
     int port;
		System.out.println("Enter host:");
                host=cin.readLine();
                System.out.println("Enter port:");
                port=Integer.parseInt(cin.readLine());
                
                System.out.println("Enter File Name:");
                filenm=cin.readLine();
		while(true)
		{
		//Socket s=new Socket("172.25.97.170",2192);
			//new Socket("technogsk-VAIO",2192);
		Socket s=new Socket(host,port);
		
		//System.out.print("\nyou: ");
		//String req=cin.readLine();
                if(!lock)
                {
                    OutputStream out=s.getOutputStream();
		PrintStream ps=new PrintStream(out);

                 ps.println("getfile<gsoft>"+filenm+"<gsoft>");
		ps.flush();
                lock=true;
                }
		else
                {
                    
                    
                    
                    
                }
		
		InputStream in=s.getInputStream();
		InputStreamReader isr=new InputStreamReader(in);
		BufferedReader bf=new BufferedReader(isr);
                        char cbuffer[]=new char[2048];
                        bf.read(cbuffer);
                        String str=new String(cbuffer);
                        String cmd[]=str.split("<gsoft>");
                        
                        switch(cmd[0])
                        {
                            case "filelength":
                                 fout=new FileOutputStream("./"+filenm);
      
                                System.out.println("file initialized..length----"+cmd[1]);
                                OutputStream out=s.getOutputStream();
		PrintStream ps=new PrintStream(out);

                                 ps.println("ack<gsoft>"+filenm+"<gsoft>1<gsoft>");
                                ps.flush();
                                
                                break;
                            case "completed":
                                System.out.println("file..read..completed");
                                break;
                            case "error":
                                    System.out.println("error..in.....reading...file....");
                                    break;
                            case "part":
                                byte bfr[]=cmd[1].getBytes();
                                seqnum+=bfr.length+1;
                                fout.write(bfr);
                                System.out.println("Read "+bfr.length+" bytes");
                                OutputStream o=s.getOutputStream();
                                PrintStream p=new PrintStream(o);

                                p.println("ack<gsoft>"+filenm+"<gsoft>"+seqnum+"<gsoft>");
                                p.flush();
                                
                                break;
                               
                                
                        }
                        
                        
		
		}
        
        
        
        
    }
    
}

