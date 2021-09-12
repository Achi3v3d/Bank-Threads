import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.Random;

public class ScynedBank implements Buffer {
	   // Lock to control synchronization with this buffer   
	   private Lock accessLock = new ReentrantLock();

	   // conditions to control reading and writing
	   private Condition canWrite = accessLock.newCondition();
	   private Condition canRead = accessLock.newCondition();
	   private static Random genorator = new Random();

	   private int buffer = 0; // shared by producer and consumer threads
	   private boolean occupied = false; // whether buffer is occupied
	   
	   // setter method for producer to write into buffer
	   public void set( int value, int x )
	   {
	      accessLock.lock(); // lock this object
	               
	      // output thread information and buffer information, then wait
	      try
	      {
	    	  buffer += value ; 
	    	  System.out.println( "Thread D"+ x + " deposits $" + value + "\t\t\t\t\t\t(+) Balance is $" + buffer );	         
	    	//canWrite.await();// wait until buffer is empty
	        // set new buffer value
	         // indicate producer cannot store another value
	         // until consumer retrieves current buffer value

	         // signal thread waiting to read from buffer
	         canRead.signal(); 
	      } // end try
	      finally
	      {
	         accessLock.unlock(); // unlock this object
	      } // end finally
	   } // end method set
	    
	   // getter method for consumer to retrieve value from buffer
	   public int get(int value, int y)
	   {
	      int readValue = 0; // initialize value read from buffer
	      int testValue = 0;
	      accessLock.lock(); // lock this object
	      // output thread information and buffer information, then wait
	      try
	      {
	    	 // System.out.println( "\t\t\tThread W"+ y + " withdraws $" + value+"\t\t\t" );
	         // while no data to read, place thread in waiting state
	         while ( (buffer - value) <= 0 ) 

	         {
	            System.out.println( "\t\t\t\tThread W"+ y + " withdraws $" + value+"\t (*******) WITHDRAWAL BLOCKED - INSUFFISIANT FUNDS!!!!" );
					canRead.await(); // wait until buffer is full
	         } // end while
	         // indicate that producer can store another value 
	         // because consumer just retrieved buffer value
	         buffer -= value; // retrieve value from buffer
	    	 System.out.println( "\t\t\t\tThread W"+ y + " withdraws $" + value+"\t (-) Balance is $" + buffer );
	        // System.out.print( "\t\t\t (+) Balance is $" + buffer +"\n" );
	         // signal thread waiting for buffer to be empty
	        // canWrite.signal();
	      } // end try
	      // if waiting thread interrupted, print stack trace
	      catch ( InterruptedException exception ) 
	      {
	         exception.printStackTrace();
	      } // end catch
	      finally
	      {
	         accessLock.unlock(); // unlock this object
	      } // end finally

	     return buffer;
	   } // end method get
	    
	   // display current operation and buffer state
	   public void displayState( String operation )
	   {
	      System.out.printf( "%-40s%d\t\t\t\t%b\n", operation, buffer, 
	         occupied );
	   } // end method displayState
}
