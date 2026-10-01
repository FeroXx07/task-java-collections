package utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class ScannerUtility {
    private static Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger();

    static {
        scanner.useLocale(Locale.US);
    }
    public static void cleanInputBuffer() {
        while (true) {
            try {
                if (!(System.in.available() > 0))
                    break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            scanner.nextLine();
        }
    }

    // For reasons, I don't understand the cleanInputBuffer() doesn't clean the buffer after numeric mismatch exception
    // and throw exceptions goes infinitely.
    private static void forceCleanInputBuffer() {
        scanner.nextLine();
    }

    public static String fetchStringInput(String message) {
        String str = "";

        while (str.isEmpty()) {
            try {
                LOGGER.info(message);
                String newChar = scanner.nextLine();
                if (newChar.trim().isEmpty()) {
                    throw new EmptyStringException("Empty String");
                }
                str = newChar;
            } catch (InputMismatchException e) {
                LOGGER.warn("Input mismatch error <<{}>>: ", e.getClass().getSimpleName());
            } catch (EmptyStringException e){
                LOGGER.error("Known exception <<{}>> : {}", e.getClass().getSimpleName(), e.getMessage());
            } catch (Exception e){
                LOGGER.error("Unknown exception <<{}>> : {}", e.getClass().getSimpleName(),e.getMessage());
            } finally {
                cleanInputBuffer();
            }
        }
        return str;
    }

    public static <T extends Number> T fetchNumber(String showMessage, Class<T> type) {
        T number = null;
        boolean validNumber = false;
        while (!validNumber) {
            try {
                LOGGER.info(showMessage);
                number = readNumber(type);
                validNumber = true;
            } catch (InputMismatchException e) {
                LOGGER.warn("Input mismatch error <<{}>>. It must be type of {}", e.getClass().getSimpleName(), type.getSimpleName());
            } catch (Exception e){
                LOGGER.error("Unknown exception <<{}>> : {}", e.getClass().getSimpleName(),e.getMessage());
            } finally {
                //cleanInputBuffer();
                forceCleanInputBuffer();
            }
        }
        return number;
    }

    // Since there are no reflection capabilities, it is imperative to pass class type
    @SuppressWarnings("unchecked")
    private static <T extends Number> T readNumber(Class<T> type) {
        if (type == Integer.class || type == int.class) {
            return (T) Integer.valueOf(scanner.nextInt());
        }
        if (type == Long.class || type == long.class) {
            return (T) Long.valueOf(scanner.nextLong());
        }
        if (type == Float.class || type == float.class) {
            return (T) Float.valueOf(scanner.nextFloat());
        }
        if (type == Double.class || type == double.class) {
            return (T) Double.valueOf(scanner.nextDouble());
        }
        if (type == Byte.class || type == byte.class) {
            return (T) Byte.valueOf(scanner.nextByte());
        }
        if (type == Short.class || type == short.class) {
            return (T) Short.valueOf(scanner.nextShort());
        }
        if (type == java.math.BigInteger.class) {
            return (T) scanner.nextBigInteger();
        }
        if (type == java.math.BigDecimal.class) {
            return (T) scanner.nextBigDecimal();
        }
        throw new IllegalArgumentException("Unsupported numeric type: " + type.getName());
    }
}
