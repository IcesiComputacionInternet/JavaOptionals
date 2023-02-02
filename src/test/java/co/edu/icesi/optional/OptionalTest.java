package co.edu.icesi.optional;

import co.edu.icesi.model.IcesiUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OptionalTest {

    @Test
    public void testOptionalEmpty() {
        Optional<String> emptyStringOptional = Optional.empty();

        assertTrue(emptyStringOptional.isEmpty());
    }

    @Test
    public void testOptionalOfNonNullable() {
        Optional<String> nonNullableOptional = Optional.of("Value");

        assertTrue(nonNullableOptional.isPresent());
        assertEquals("Value", nonNullableOptional.get());
    }

    @Test
    public void testPassingANullValueToANonNullableOptional() {
        assertThrows(Exception.class, () -> Optional.of(null));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"Value"})
    public void testOptionalOfNullable(String value) {
        assertDoesNotThrow(() -> Optional.ofNullable(value));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"Value"})
    public void testGetValueOfAnOptional(String value) {
        Optional<String> optionalString = Optional.ofNullable(value);

        if (optionalString.isPresent()) {
            assertEquals("Value", optionalString.get());
        } else {
            assertThrows(NoSuchElementException.class, optionalString::get);
        }
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"Value"})
    public void testGetValueOfAnOptionalWithOrElse(String value) {
        Optional<String> optionalString = Optional.ofNullable(value);
        String result = optionalString.orElse("Value");
        assertEquals("Value", result);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"Value"})
    public void testGetValueOfAnOptionalWithOrElseGet(String value) {
        Optional<String> optionalString = Optional.ofNullable(value);
        String result = optionalString.orElseGet(() -> "Value");
        assertEquals("Value", result);
    }

    @Test
    public void testGetValueOfAnOptionalWithOrElseThrow() {
        Optional<String> optionalString = Optional.empty();
        assertThrows(NoSuchElementException.class, () -> optionalString.orElseThrow(NoSuchElementException::new));
    }

    @Test
    public void testOptionalMap() {
        Optional<IcesiUser> optionalIcesiUser = Optional.of(defaultIcesiUser());
        Optional<String> optionalLastName = optionalIcesiUser.map(IcesiUser::getLastName);
        assertTrue(optionalIcesiUser.isPresent());
        assertTrue(optionalLastName.isPresent());
        assertEquals("Doe", optionalLastName.get());
    }

    private IcesiUser defaultIcesiUser() {
        IcesiUser icesiUser = new IcesiUser();
        icesiUser.setActive(true);
        icesiUser.setId(1);
        icesiUser.setName("John");
        icesiUser.setLastName("Doe");
        return icesiUser;
    }

}
