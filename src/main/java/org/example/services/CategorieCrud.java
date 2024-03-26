package org.example.services;

import org.example.entities.Activite;
import org.example.entities.Categorie;
import org.example.tools.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategorieCrud implements IcrudA<Categorie>{

    @Override
    public void ajouter(Categorie categorie) {
        String req1 = "INSERT INTO categorie(description,type_categorie) VALUES (?,?)";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req1);
            pst.setString(1, categorie.getDescription()); //tab3a ? eli fel values
            pst.setString(2, categorie.getType_categorie());

            pst.executeUpdate();
            System.out.println("categorie ajoutéé!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Categorie categorie) throws SQLException {
        final String query="UPDATE categorie SET description = ?, type_categorie = ? WHERE id= ?";
        try( PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);){

            pst.setString(1,categorie.getDescription());
            pst.setString(2,categorie.getType_categorie());

            pst.setInt(3,categorie.getId());



            pst.executeUpdate();
        }
    }

    @Override
    public void supprimer(Categorie categorie) {
        final String query = "DELETE FROM categorie WHERE id=? ";
        try(PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query)){
            pst.setInt(1,categorie.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Categorie> afficher() {
        List<Categorie>categories=new ArrayList<>();
        String req3="SELECT * FROM categorie";
        try {
            Statement st=MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(req3);
            while (rs.next())
            {
                Categorie p=new Categorie();
                p.setId(rs.getInt("id"));
                p.setDescription(rs.getString("description"));
                p.setType_categorie(rs.getString("type_categorie"));

                categories.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }
}
