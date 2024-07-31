package zhoma.practice_1.exception;

public class ClientNotFoundException extends RuntimeException{


    public ClientNotFoundException(int id) {
        super("User with this id not found id = ["+ id + "] !" );
    }
}
