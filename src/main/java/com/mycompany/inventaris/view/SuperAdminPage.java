/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventaris.view;

import com.mycompany.inventaris.model.User;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author pnady
 */
public class SuperAdminPage extends BorderPane {

    private Stage stage;
    private User user;
    
    public SuperAdminPage(User user){
        this.user = user;
    }
}
