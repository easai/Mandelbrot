package com.github.easai.math;

import com.github.easai.math.mandelbrot.MandelbrotPanel;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     **/
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }
    

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	Complex z=new Complex(1,1);
    	Complex c=new Complex(0,-1);
    	Complex res=MandelbrotPanel.f(z,c);
        assertTrue( res.Re()==0 );
        assertTrue( res.Im()==1 );
    }
}
