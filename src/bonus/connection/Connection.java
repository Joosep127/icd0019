package bonus.connection;

public record Connection(String from, String to) {
    public Connection {
        if (from.equals(to)) {
            throw new IllegalArgumentException("'from' and 'to' can't be the same");
        }
    }
}
