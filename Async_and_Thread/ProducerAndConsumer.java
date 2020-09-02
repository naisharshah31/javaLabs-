class MyThread
{
    int num;
    boolean flag =false;
    synchronized int get()
    {
        if (!flag)
            try
            {
                wait();
            }
            catch (Exception e)
            {
                System.out.println("Exception occurs at : "+e);
            }
        System.out.println("Consumer got:" +num);
        try
        {
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            System.out.println("Exception occurs at : "+e);
        }
        flag=false;
        notify();
        return num;
    }
    
    synchronized int put(int num)
    {
        if (flag)
            try
            {
                wait();
            }
            catch (Exception e)
            {
                System.out.println("Exception occur at : "+e);
            }
        this.num=num;
        flag=true;
        System.out.println("Producer put:"+num);
        try
        {
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            System.out.println("Exception occur at : "+e);
        }
        notify();
        return num;
    }
}

class Producer implements Runnable
{
    MyThread t;
    Producer(MyThread t)
    {
        this.t=t;
        new Thread(this,"Producer").start();
    }
    public void run()
    {
        int x=0;
        int i = 0;
        while (x<10)
        {
            t.put(i++);
            x++;
        }
    }
}

class Consumer implements Runnable
{
    MyThread t;
    Consumer(MyThread t)
    {
        this.t=t;
        new Thread(this,"Consumer").start();
    }
    public void run()
    {
        int x=0;
        while (x<10)
        {
            t.get();
            x++;
        }
    }
}

class  ProducerAndConsumer
{
    public static void main(String args[])
    {
        MyThread t=new MyThread();
        new Producer(t);
        new Consumer(t);
    }
}