/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.CategoryInterface;
import Model.Category;
import Model.Produit;
import Util.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Refka
 */
public class CategoryService implements  CategoryInterface {
    ObservableList<Category> CategoryList = FXCollections.observableArrayList();
    MaConnection instance = MaConnection.getInstance();
    Connection cnx = instance.getCnx();
    // @Override
    public void AddCategory(Category c) {
       
      String req="INSERT INTO `category`( `name`)"+" VALUES (?)";
         try {
             PreparedStatement ste = cnx.prepareStatement(req);
            ste.setString(1,c.getName());
            ste.executeUpdate();
            System.out.println("Category ajoutée");
               } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
        
    }

     public void ModifierCategory(Category c) {
            //String req = "UPDATE product SET `name`=? , `description`=? , `price`=? , `rate`=? , `release_date`=?  where `id`=?";
           String req ="UPDATE `category` SET `name`=? WHERE `id`=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(req);
            //ste.setInt(1, c.getId());
            ste.setString(1,c.getName());
            ste.setInt(2, c.getId());
            ste.executeUpdate();
            System.out.println(" Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }
     
    @Override
    public void SupprimerCategory(Category c) {
        String req="DELETE FROM `category` WHERE id=?" ;
         try {
             PreparedStatement ste = cnx.prepareStatement(req);
             ste.setInt(1, c.getId());
             ste.executeUpdate();
             System.out.println("Category bien supprimé");
            
         }catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        
         }
    }
    
    @Override
    public ObservableList<Category> getCategoryList(){

        String query="select * from category ";
        PreparedStatement pre;
        try {
            pre = cnx.prepareStatement(query);
             ResultSet rst =pre.executeQuery();
            while(rst.next())
            {    
            int id = rst.getInt("id");
            String name = rst.getString("name");
    
            Category c = new Category (id,name +"\n");
            CategoryList.add(c);
            //System.out.println(ProduitList+"\n");
            }   } catch (SQLException ex) {
           // Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        } 
     return  CategoryList;
    }

    @Override
    public void ModifierCtegory(Category c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
       public Category getCategory(Category c) {
        try {
            PreparedStatement pre = cnx.prepareStatement("SELECT * FROM `category`  WHERE id = ?");
            pre.setInt(1, c.getId());
            ResultSet rs = pre.executeQuery();
            //Produit p = new Produit();
            if (rs.next()) {
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
           // System.out.println(c);
            }
        return c;
       
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }

   

    
}
