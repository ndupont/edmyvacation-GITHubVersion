package com.ellisdon.portal.mv.util;

public class StopWatch {
	private long start = 0;	
	private long stop = 0;
	private long count = 0;
	
    public StopWatch()
    {
    	start = System.currentTimeMillis();
    }
    
    public void reStart()
    {
    	stop = 0;
    	start = System.currentTimeMillis();
    	count = 0;
    }

    public void count()
    {
    	count++;
    }
    
    public long stopMillis()
    {
    	if (stop == 0)
    	{
    		stop = System.currentTimeMillis();
    	}
    	return stop - start;
    }
    
    public float stopSeconds()
    {
		if (stop == 0)
		{
			stop = System.currentTimeMillis();
		}
    	return (stop - start)/1000F;
    }

	public float stopMinutes()
	{
		if (stop == 0)
		{
			stop = System.currentTimeMillis();
		}
		return (stop - start)/(60*1000F);
	}
    
    public float stopHour()
    {
		if (stop == 0)
		{
			stop = System.currentTimeMillis();
		}
		return (stop - start)/(60*60*1000F);
    }
    
    public float stopDay()
    {
		if (stop == 0)
		{
			stop = System.currentTimeMillis();
		}
		return (stop - start)/(24*60*60*1000F);
    }
    
    public long meanMillis()
    {
    	if (stop == 0)
    	{
    		stop = System.currentTimeMillis();
    	}
    	return (stop - start)/count;
    }
    
    public float meanSeconds()
    {
		if (stop == 0)
		{
			stop = System.currentTimeMillis();
		}
    	return ((stop - start)/1000F)/count;
    }

	public float meanMinutes()
	{
		if (stop == 0)
		{
			stop = System.currentTimeMillis();
		}
		return ((stop - start)/(60*1000F))/count;
	}
    
    public float meanHour()
    {
		if (stop == 0)
		{
			stop = System.currentTimeMillis();
		}
		return ((stop - start)/(60*60*1000F))/count;
    }
    
    public float meanDay()
    {
		if (stop == 0)
		{
			stop = System.currentTimeMillis();
		}
		return ((stop - start)/(24*60*60*1000F))/count;
    }
}
