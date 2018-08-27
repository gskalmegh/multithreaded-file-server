/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadedserver;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ghanshyam
 */
public class server extends Thread
{
    int threadid;
    private server(int num)
    {
        this.threadid=num;
    }
    
    public void run()
    {
        System.out.println("entered in thread"+threadid);
            
        for(int i=0;i<10;i++)
        {
            System.out.println("thread no."+threadid);
            
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                
            }
        }
         System.out.println("exit from thread"+threadid);
        
    }
}


