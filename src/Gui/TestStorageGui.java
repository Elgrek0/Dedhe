/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import dedheproject.dataclasses.PowerPlant;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Paris
 */
public class TestStorageGui extends JFrame {

    PowerPlant p;
    public TestStorageGui() {
        JTextField plantname = new JTextField();
        plantname.setSize(200, 30);
        plantname.setVisible(true);
        add(plantname);
        

    }

}
