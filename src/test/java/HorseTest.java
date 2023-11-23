import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {

    @Test
    void constructorFirstParamNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 18.5, 25.6));
    }

    @Test
    void constructorNullText() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 18.5, 25.6));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "\r", "\f"})
    void constructorFirstParamIsBank(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 8.5, 25.6));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "\r", "\f"})
    void constructorFirstParamText(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 8.5, 25.6));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorSecondParamNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Jack", -1.5, 25.6));
    }

    @Test
    void constructorSecondParamText() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Jack", -2.6, 20.6));
        assertEquals("Speed cannot be negative.", exception.getMessage());

    }

    @Test
    void constructorThirdParamNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("John", 15.45, -3));
    }

    @Test
    void constructorThirdParamText() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("John", 25.55, -3.78));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameCorrectRow() {
        assertEquals("Artem", new Horse("Artem", 25.4, 50.6).getName());
    }

    @Test
    void getSpeedCorrectNumber() {
        assertEquals(22, new Horse("Olesya", 22, 55).getSpeed());
    }

    @Test
    void getDistanceCorrectNumber() {
        assertEquals(60, new Horse("Maxym", 20, 60).getDistance());
    }

    @Test
    void getDistanceReturnZero() {
        assertEquals(0, new Horse("Ira", 35.56).getDistance());
    }

    @Test
    void moveReturnCorrectNumber() {
        try (MockedStatic<Horse> numbers = Mockito.mockStatic(Horse.class)) {
            new Horse("Ira", 40.56).move();
            numbers.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5, 0.6, 0.7})
    void moveVerifyDistance(double testValue) {
        double distance = 250;
        Horse horse = new Horse("Name", 14.5, distance);
        double expectedResult = distance + horse.getSpeed() * testValue;

        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(testValue);
            horse.move();
        }
        assertEquals(expectedResult, horse.getDistance());
    }
}
