package katas.iq_test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class IqTestTest {
    @Test
    public void shouldReturnCorrectValue() {
        assertEquals(3, new IqTest("2 4 7 8 10").solve());
        assertEquals(1, new IqTest("1 2 2").solve());
    }
}
