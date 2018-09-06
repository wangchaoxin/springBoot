package com.wcx.springboot.demo.mokito;

import com.wcx.springboot.demo.java.reflector.A;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MokitoTest {
    @Test
    public void test() {
        A mock = mock(A.class);
        //Mocks can return different values depending on arguments passed into a method.
        // The when(…​.).thenReturn(…​.) method chain is used to specify a a return value for a
        // method call with pre-defined parameters.
        when(mock.getInt()).thenReturn(1);
        assertEquals(mock.getInt(), 2);
    }
}
