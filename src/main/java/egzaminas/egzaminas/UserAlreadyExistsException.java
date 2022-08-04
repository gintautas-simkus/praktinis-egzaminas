package egzaminas.egzaminas;

class UserAlreadyExistsException extends Exception {
	UserAlreadyExistsException(String msg) {
		super(msg);
	}
}