public class Deadlock 
{
   public static Object Lock1 = new Object();
   public static Object Lock2 = new Object();
   
   public static void main(String args[]) 
   {
      MyThread demo1 = new MyThread();
      MyThread2 demo2 = new MyThread2();
      demo1.start();
      demo2.start();
   }
   
   private static class MyThread extends Thread 
   {
      public void run() 
	  {
         synchronized (Lock1)
		 {
            System.out.println("MyThread 1: Holding lock 1...");
            try 
			{ 
				Thread.sleep(10); 
			}
            catch (InterruptedException e) {}
            System.out.println("MyThread 1: Waiting for lock 2...");
            
            synchronized (Lock2)
			{
               System.out.println("MyThread 1: Holding lock 1 & 2...");
            }
         }
      }
   }
   private static class MyThread2 extends Thread 
   {
      public void run() 
	  {
         synchronized (Lock2) 
		 {
            System.out.println("MyThread 2: Holding lock 2...");
            try 
			{ 
				Thread.sleep(10); 
			}
            catch (InterruptedException e) {}
            System.out.println("MyThread 2: Waiting for lock 1...");
            
            synchronized (Lock1)
			{
               System.out.println("MyThread 2: Holding lock 1 & 2...");
            }
         }
      }
   } 
}