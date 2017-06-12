package exception;

public class VolumeException extends ValidationException {
	private static final int code = 100005;	
	
    public VolumeException() {
        super(code, "Volume must be: \"string:string\"");
    }

    public VolumeException(String message) {
        super(code, message);
    }

    public VolumeException(String message, Throwable cause) {
        super(code, message, cause);
    }
    
    public VolumeException(Throwable cause) {
        super(code, cause);
    }
}