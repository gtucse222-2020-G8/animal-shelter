package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.PageWithTokenModel;

/**
 * The interface Page with token controller.
 */
public interface PageWithTokenController {

    /**
     * Sets token.
     *
     * @param token the token
     */
    void setToken(Token token);
}
