package cse222.group8.desktop.models;

import cse222.group8.desktop.client.models.Token;

/**
 * The type Page with token model.
 */
public abstract class PageWithTokenModel {
    private Token token;

    /**
     * Gets token.
     *
     * @return the token
     */
    public Token getToken() {
        return token;
    }

    /**
     * Sets token.
     *
     * @param token the token
     */
    public void setToken(Token token) {
        this.token = token;
    }
}
