import java.io.FileNotFoundException;

// update the class
class BadRequestException extends FileNotFoundException {
    public BadRequestException(String message) {
        super(message);
    }
}