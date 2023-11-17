import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {

    @Test
    void constructorParamNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void constructorNullText(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorParamEmpty(){
        List<Horse> horsesEmpty = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horsesEmpty));
    }

    @Test
    void constructorParamEmptyText(){
        List<Horse> horsesEmpty = new ArrayList<>();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horsesEmpty));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesCorrectList(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i ++)
            horses.add(new Horse("Horse" + i, 15.1, 30.1));
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void moveCall(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i ++)
            horses.add(Mockito.mock(Horse.class));
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse hors: horses) {
            Mockito.verify(hors, Mockito.times(1)).move();
        }
    }

    @Test
    void getWinnerMaxDistance(){
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Horse1", 15.1, 30.1));
        horses.add(new Horse("Horse2", 16.1, 40.1));
        horses.add(new Horse("Horse3", 17.1, 50.1));
        horses.add(new Horse("Horse4", 18.1, 60.1));
        horses.add(new Horse("Horse5", 19.1, 70.1));
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses.get(4).getName(), hippodrome.getWinner().getName());
    }
}
