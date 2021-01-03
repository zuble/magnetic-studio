/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author henrique
 */

public class player {
    private String username, password;
    private int wallet=0;
    player(String username, String password){
        this.username=username;
        this.password=password;
    }
    String getPlayerName(){
        return this.username;
    }
    int getWallet(){
        return this.wallet;
    }
    
}
