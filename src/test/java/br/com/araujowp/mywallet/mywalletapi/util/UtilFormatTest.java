package br.com.araujowp.mywallet.mywalletapi.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class UtilFormatTest {

    @Test
    public void testToStringWithPositiveNumber() {
        double number = 12345.67;
        assertEquals("12.345,67", UtilFormat.toString(number));
    }

    @Test
    public void testToStringWithNegativeNumber() {
        double number = -9876.54321;
        assertEquals("-9.876,54", UtilFormat.toString(number));
    }

    @Test
    public void testToStringWithZero() {
        double number = 0.0;
        assertEquals("0,00", UtilFormat.toString(number));
    }

    @Test
    public void testToStringWithLargeNumber() {
        double number = 123456789.123456789;
        assertEquals("123.456.789,12", UtilFormat.toString(number));
    }

    @Test
    public void numberSmallerone() {
    	double number  = 0.5;
    	assertEquals("0,50", UtilFormat.toString(number));
    }
    
    
//    @Test
//    public void testToStringWithSmallNumber() {
//        double number = 0.00000123456789;
//        assertEquals("0,00", UtilFormat.toString(number)); // Números menores que 0,01 serão arredondados para 0,00
//    }

    @Test
    public void testToStringWithNull() {
        assertEquals("", UtilFormat.toString(Double.NaN));
    }
    
	
}
