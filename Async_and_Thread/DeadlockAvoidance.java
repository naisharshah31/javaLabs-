class DeadlockAvoidance
{
   public static Object L1 = new Object();
   public static Object L2 = new Object();
   
   public static void main(String args[]) 
   {
      ThreadDemo1 T1 = new ThreadDemo1();
      ThreadDemo2 T2 = new ThreadDemo2();
      T1.start();
      T2.start();
   }
   
   private static class ThreadDemo1 extends Thread 
   {
      public void run() 
	  {
         synchronized (L1) 
		 {
            System.out.println("Thread 1: Holding lock 1...");
            
            try 
			{
               Thread.sleep(10);
            } catch (InterruptedException e) {}
            System.out.println("Thread 1: Waiting for lock 2...");
            
            synchronized (L2) 
			{
               System.out.println("Thread 1: Holding lock 1 & 2...");
            }
         }
      }
   }
   
  private static class ThreadDemo2 extends Thread 
  {
    public void run() 
	{
      synchronized (L1)
	  {
            System.out.println("Thread 2: Holding lock 1...");
            try 
			{
               Thread.sleep(10);
            } 
			catch (InterruptedException e) {}
            System.out.println("Thread 2: Waiting for lock 2...");
            
            synchronized (L2) 
			{
               System.out.println("Thread 2: Holding lock 1 & 2...");
            }
      }
    }
  } 
}