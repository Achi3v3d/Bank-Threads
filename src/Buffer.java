// Buffer interface specifies methods called by Producer and Consumer.

public interface Buffer 
{
   public void set( int value, int x ); // place int value into Buffer
   public int get(int value, int y); // return int value from Buffer
} // end interface Buffer