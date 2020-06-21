package cse222.group8.desktop.models;

import cse222.group8.desktop.client.models.Token;

public abstract class PageWithTokenModel {
    private Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
