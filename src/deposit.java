import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;
import java.util.concurrent.locks.Condition;

public class deposit implements Runnable {
	   private static Random generator = new Random();
	   private Buffer sharedLocation; // reference to shared object
	   int sum = 0;
	   // constructor
	   public deposit( Buffer shared, int x)
	   {
	      sharedLocation = shared;
	      sum = x;
	   } // end Producer constructor
	   // store values from 1 to 10 in sharedLocation
	   public void run()
	   {
	      for ( int count = 1; count <= 4; count++ ) 
	      {  
	         try // sleep 0 to 3 seconds, then place value in Buffer
	         {
	           Thread.sleep( generator.nextInt( 60) ); // sleep thread   
	            sharedLocation.set(generator.nextInt(900), sum); // set value in buffer

	         } // end try
	         // if sleeping thread interrupted, print stack trace
	         catch ( InterruptedException exception ) 
	         {
	            exception.printStackTrace();
	         } // end catch
	      } // end for


	   } // end method run
}
