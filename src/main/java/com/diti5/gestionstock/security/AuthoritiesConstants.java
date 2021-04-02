package com.diti5.gestionstock.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String GESTIONNAIRE = "ROLE_GESTIONNAIRE";
    public static final String VENDEUR = "ROLE_VENDEUR";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {
    }
}
