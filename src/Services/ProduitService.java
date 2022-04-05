/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.ProduitInterface;
import Model.Produit;
import Util.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Refka
 */
public class ProduitService implements ProduitInterface{
    ObservableList<Produit> ProduitList = FXCollections.observableArrayList();
    MaConnection instance = MaConnection.getInstance();
    Connection cnx = instance.getCnx();
    // @Override
    public void AddProduit(Produit p) {
       
      String req="INSERT INTO `product`( `name`, `description`, `price`, `rate`, `image`, `release_date`)"+" VALUES (?,?,?,?,?,?)";
         try {
             PreparedStatement ste = cnx.prepareStatement(req);
           // ste = cnx.prepareStatement(req);
            //ste.setInt(1, p.getId_produit());
           //ste.setInt(1,id);
            //ste.setInt(2,1);
           // ste.setString(3,p.getName());
            ste.setString(1,p.getName());
            ste.setString(2,p.getDescription());
            ste.setFloat(3,(float) p.getPrice());
            ste.setInt(4,p.getRate());
            ste.setString(5,p.getImage());
            ste.setString(6,p.getRelease_date());
            ste.executeUpdate();
            System.out.println("Produit ajoutée");
               } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
        
    }

  /*  @Override
    public void addProduit(Produit p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
     public void ModifierProduit(Produit p) {
            //String req = "UPDATE product SET `name`=? , `description`=? , `price`=? , `rate`=? , `release_date`=?  where `id`=?";
           String req ="UPDATE `product` SET `name`=?,`description`=?,`price`=?,`rate`=?,`image`=?,`release_date`=? WHERE `id`=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(req);
            //ste.setInt(1, p.getId());
            ste.setString(1,p.getName());
            ste.setString(2,p.getDescription());
            ste.setFloat(3,(float) p.getPrice());
            ste.setInt(4,p.getRate());
            ste.setString(5,p.getImage());
            ste.setString(6,p.getRelease_date());
            ste.setInt(7, p.getId());
            
            
            ste.executeUpdate();
            System.out.println(" Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }
     

    @Override
    public void addProduit(Produit p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   /* @Override
    public void ModifierProduit(Produit p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    public void SupprimerProduit(Produit p) {
        String req="DELETE FROM `product` WHERE id=?" ;
         try {
             PreparedStatement ste = cnx.prepareStatement(req);
             ste.setInt(1, p.getId());
             ste.executeUpdate();
             System.out.println("Produit bien supprimé");
             
             
             
         }catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        
         }
    }
    
    
    /*@Override
    public List<Produit> AfficherProduit() throws SQLException {
        Statement stm =cnx.createStatement();
        
         String query="select * from `product` " ;
            
         ResultSet rst =stm.executeQuery(query);
         List<Produit> produits ;
         produits = new ArrayList<>();
         
         
         while(rst.next())
         { Produit p =new Produit();
                p.setName(rst.getString("name"));
                p.setDescription(rst.getString("description"));
                p.setPrice( (float) rst.getFloat("price"));
                p.setRate(rst.getInt("rate"));
                p.setImage(rst.getString("image"));
                p.setRelease_date( rst.getString("release_date"));
                produits.add(p);
         
         }
        
        return produits;
    }*/
    /*@Override
    public List<Produit> AfficherProduit() {
        ArrayList<Produit> produits = new ArrayList();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM product";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
            produits.add(new Produit(
                    rs.getInt("id"), 
                    rs.getString("name"),
                    rs.getString("description"), 
                    rs.getFloat("price"), 
                    rs.getInt("rate"),
                    rs.getString("image"),
                    rs.getString("release_date")));
                    
            
                   //System.out.println(produits);
            
        
            System.out.println("succes");
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
          System.out.println("erreur");
        }
        
        return produits;
        
    }*/

    public List<Produit> AfficherProduit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public ObservableList<Produit> getProduitList(){

        String query="select * from product ";
        PreparedStatement pre;
        try {
            pre = cnx.prepareStatement(query);
             ResultSet rst =pre.executeQuery();
            while(rst.next())
            {    
            int id = rst.getInt("id");
            String name = rst.getString("name");
            String description = rst.getString("description");
            float price = rst.getFloat("price");
            int rate = rst.getInt("rate");
            String image = rst.getString("image");
            String release_date = rst.getString("release_date");
            Produit p = new Produit (id,name,description,price,rate,image,release_date+"\n");
            ProduitList.add(p);
            //System.out.println(ProduitList+"\n");
            }   } catch (SQLException ex) {
           // Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        } 
     return  ProduitList;
    }
    
}
