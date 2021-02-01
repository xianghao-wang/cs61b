import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    @Test
    public void testEqualChars() {
        CharacterComparator ob2 = new OffByN(2);
        assertTrue(ob2.equalChars('a', 'c'));
        assertTrue(ob2.equalChars('0', '2'));
        assertFalse(ob2.equalChars('a', 'a'));
        assertFalse(ob2.equalChars('a', 'b'));
    }
}
