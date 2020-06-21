package cse222.group8.desktop.models;

import cse222.group8.desktop.client.models.Token;

public class HomePageModel {
    private Token token;

    public void setToken(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }
}
