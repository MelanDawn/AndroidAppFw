package com.zs.androidappfw;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Collection;
import java.util.Map;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();

        Collection<StackTraceElement[]> collection = map.values();
        for (StackTraceElement[] elements : collection) {
            System.out.println("====================");
            for (StackTraceElement element : elements) {
                System.out.println("---------------------");
                System.out.println(element);
                System.out.println(element.getClassName());
                System.out.println(element.getFileName());
                System.out.println(element.getLineNumber());
                System.out.println(element.getMethodName());
            }
        }
    }
}