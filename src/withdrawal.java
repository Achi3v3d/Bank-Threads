import java.util.Random;
public class withdrawal implements Runnable 
{
	 private static Random generator = new Random();
	   private Buffer sharedLocation; // reference to shared object
	   int sums = 0;
	   // constructor
	   public withdrawal( Buffer shared, int y )
	   {
	      sharedLocation = shared;
	     sums = y;
	   } // end Consumer constructor
	   // read sharedLocation's value four times and sum the values
	   public void run()
	   {
	      int sum = 0;

	      for ( int count = 1; count <= 4; count++ ) 
	      {
	         // sleep 0 to 3 seconds, read value from buffer and add to sum
	         try 
	         {
	            Thread.sleep( generator.nextInt( 30 ) );
	            sum = generator.nextInt(1000);
	           sharedLocation.get(sum, sums);
	            // uncomment for unsync version - 
	            //System.out.printf( "\t\t\t%2d\n", sum );
	         } // end try
	         // if sleeping thread interrupted, print stack trace
	         catch ( InterruptedException exception ) 
	         {
	            exception.printStackTrace();
	         } // end catch
	      } // end for
	   } // end method run
}
