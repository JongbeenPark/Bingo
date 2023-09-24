package com.nhnacadmy.bingo;

import com.nhnacadmy.bingo.MainSystem.System.ServerManager;

public class Bingo {
    public static void main(String[] args) {
        int port = 12343;
        ServerManager.getInstance().start(port);
    }
}
