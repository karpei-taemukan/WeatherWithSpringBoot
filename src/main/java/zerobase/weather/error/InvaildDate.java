package zerobase.weather.error;

public class InvaildDate extends RuntimeException {
    private static final String MESSAGE = "TOO FAR FUTURE";

    public InvaildDate(){
        super(MESSAGE);
    }
}
