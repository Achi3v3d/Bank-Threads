import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*  Name: Maduabuchi Udokwu  
 * Course: CNT 4714
 * Spring 2021
 * Assignment title: Project2–Synchronized, 
 * Cooperating ThreadsUnder LockingDue Date:
 * February14, 2021
 * */
public class SharedBank {

		   public static void main( String[] args ){
		      // create new thread pool with two threads
		      ExecutorService application = Executors.newFixedThreadPool( 15 );
		      int x = 0;
		      int y = 0;
		      // create UnsynchronizedBuffer to store ints
		      Buffer sharedLocation = new ScynedBank();
		      System.out.println( "deposit Threads\t\t\tWithdrawal Threads\t\t\tBalance" );
		      System.out.println( "---------------\t\t\t------------------\t\t-----------------------\n" );

		      // try to start producer and consumer giving each of them access to SharedLocation
			try  {
		         application.execute( new deposit( sharedLocation, x=1 ) );		  
		         application.execute( new deposit( sharedLocation, x=2 ) );				
		         application.execute( new deposit( sharedLocation, x=4 ) );
		         application.execute( new withdrawal( sharedLocation, y=1) );

		         application.execute( new withdrawal( sharedLocation, y=2 ) );		         

		         application.execute( new withdrawal( sharedLocation, y=3) );
		         application.execute( new withdrawal( sharedLocation, y=4 ) );
		         application.execute( new withdrawal( sharedLocation, y=5 ) );
		         application.execute( new withdrawal( sharedLocation, y=6 ) );
		         application.execute( new withdrawal( sharedLocation, y=7 ) );
		         application.execute( new withdrawal( sharedLocation, y=8 ) );
		         application.execute( new withdrawal( sharedLocation, y=9 ) );

		         application.execute( new deposit( sharedLocation, x=3) );
		         application.execute( new deposit( sharedLocation, x=5) );		         
		         
		      } // end try
		      catch ( Exception exception ) {
		         exception.printStackTrace();
		      } // end catch

		      application.shutdown(); // terminate application when threads end
		   } // end main
		 // end class SharedBufferTest
}
