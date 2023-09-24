package com.nhnacadmy.bingo.MainSystem.MessageTransfer;

import java.io.IOException;

import com.nhnacadmy.bingo.MainSystem.Connect.Connect;
import com.nhnacadmy.bingo.MainSystem.Connect.ConnectManager;
import com.nhnacadmy.bingo.MainSystem.Enum.MenuType;
import com.nhnacadmy.bingo.MainSystem.Enum.UserType;

public class TransferManager {
    private static TransferManager instance;

    private TransferManager() {
        super();
    }

    public static TransferManager getInstance() {
        if (instance == null) {
            instance = new TransferManager();
        }
        return instance;
    }

    public void sendMessageAll(String message, Connect fromConnect, UserType fromType) {
        for (Connect connect : ConnectManager.getInstance().getConnects()) {
            if (connect != fromConnect)
                sendMessage(message, connect, fromType);
        }
    }

    // 보내는 사람의 userType 작성
    public void sendMessage(String message, Connect toConnect, UserType fromType) {
        if (fromType == UserType.SERVER) {
            try {
                toConnect.getOutputStream().write(message.getBytes());
                toConnect.getOutputStream().flush();
                System.out.println("Send a message to " + toConnect.getSocket().getInetAddress() + " : "
                        + toConnect.getSocket().getLocalPort() + " : " + message);
            } catch (IOException e) {
                System.out.println("Failed to send a message.");
                e.printStackTrace();
            }
        } else if (fromType == UserType.CLIENT) {
            if (toConnect.getMenuType() != MenuType.LOGIN)
                try {
                    toConnect.getOutputStream().write(message.getBytes());
                    toConnect.getOutputStream().flush();
                    System.out.println("Send a message to " + toConnect.getSocket().getInetAddress() + " : "
                            + toConnect.getSocket().getLocalPort() + " : " + message);
                } catch (IOException e) {
                    System.out.println("Failed to send a message.");
                    e.printStackTrace();
                }
        }

    }
}
