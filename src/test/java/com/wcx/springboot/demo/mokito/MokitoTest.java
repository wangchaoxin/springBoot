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

    @Test
    public void test1() {
        String s = "hello world";
        char[] chars = s.toCharArray();
        int start = 0;
        int end = 0;

        while (end < chars.length) {
            if (chars[end] == ' ') {
                reverseChar(chars, start, end - 1);
                start = end + 1;
            }
            if (end == chars.length - 1) {
                reverseChar(chars, start, end);
            }
            end++;
        }
        reverseChar(chars, 0, chars.length - 1);
        System.out.println(new String(chars));
    }

    private void reverseChar(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }
}
