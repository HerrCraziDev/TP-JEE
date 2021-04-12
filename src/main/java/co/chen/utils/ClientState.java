package co.chen.utils;

public enum ClientState {
    /* ======[]  >>>---->             // */
    CREATED("A"),   // Active
    PENDING("P"),   // Pending creation
    DELETED("D"),   // Deleted/desactivated
    STANDBY("S"),   // Unused
    ;

    private final String code;

    ClientState(final String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }

    public static ClientState fromString(String text) {
        for (ClientState s : ClientState.values()) {
            if (s.code.equalsIgnoreCase(text)) {
                return s;
            }
        }
        return null;
    }
}
