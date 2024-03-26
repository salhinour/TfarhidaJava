package org.example.tests;
import org.example.entities.Activite;
import org.example.entities.Categorie;
import org.example.services.ActiviteCrud;
import org.example.services.CategorieCrud;
import org.example.tools.MyConnection;

import java.sql.SQLException;

public class MainClass {
    public static void main(String[] args) {
        MyConnection mc = new MyConnection();
        MyConnection mc1 = MyConnection.getInstance();

        Categorie c=new Categorie(3,"allo","allo");
        CategorieCrud cc = new CategorieCrud();
        //cc.ajouter(c);
        System.out.println("c id is "+c.id);
        Activite p = new Activite(2,"Bikee", 20, "Nabeul", 10, "En cours","1", "5.jpg", 2);
        ActiviteCrud ac = new ActiviteCrud();
       //ac.ajouter(p);
        System.out.println(ac.afficher());
        System.out.println(cc.afficher());
        try {
            ac.modifier(p);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            cc.modifier(c);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            ac.supprimer(p);
            cc.supprimer(c);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
