package co.chen.utils;

public enum ClientState {
    CREATED("CREATED"),
    PENDING("PENDING"),
    CREATING("CREATING"),
    DELETED("DELETED")
    ;

    private final String code;

    ClientState(final String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
