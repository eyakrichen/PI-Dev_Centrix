/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.ProduitInterface;
import Model.Category;
import Model.Produit;
import Util.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
    ObservableList<Produit> ProduitLis = FXCollections.observableArrayList();
    ObservableList<Produit> ProduitListt = FXCollections.observableArrayList();
   // ObservableList<Produit> Produits = FXCollections.observableArrayList();
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
    
    
    public void InsertProdSelonCat(Produit p , Category c){
            
            String req = "INSERT INTO `product_category`(`product_id`, `category_id`)"+" VALUES (?,?)";
            try{
            PreparedStatement ste = cnx.prepareStatement(req);
            
             ste.setInt(1,p.getId());
             ste.setInt(2,c.getId());
             ste.executeUpdate();
             System.out.println("barvoo");
           
            }catch (SQLException ex) {
                System.out.println("nooo");
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


    public List<Produit> AfficherProduit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
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
     
    
    @Override
       public Produit getProduit(Produit p) {
        try {
            PreparedStatement pre = cnx.prepareStatement("SELECT * FROM `product`  WHERE id = ?");
            pre.setInt(1, p.getId());

            ResultSet rs = pre.executeQuery();
            //Produit p = new Produit();
            if (rs.next()) {
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("description"));
            p.setPrice(rs.getFloat("price"));
            p.setRate(rs.getInt("rate"));
            p.setImage(rs.getString("image"));
            p.setRelease_date(rs.getString("release_date"));
            //System.out.println(c);
            }
        return p;
       
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    @Override
    public void SupprimerProduit(Produit p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Produit getProduitByCtegory( Produit p,Category c) {
        try {
            //String req = "SELECT * FROM `product`  WHERE id = ?";
           // PreparedStatement pre1 = cnx.prepareStatement(req);
           PreparedStatement pre = cnx.prepareStatement("SELECT product_id FROM `product_category`  WHERE category_id = ?");
            pre.setInt(1, c.getId());
           // pre1.setInt(1, p.getId());
             ResultSet rs1 = pre.executeQuery();
           // ResultSet rs1 = pre1.executeQuery();
            //  Produit p1 = new Produit();
           // if (rs1.next()) {
            while(rs1.next()){
            p.setId(rs1.getInt("product_id"));
        
            //System.out.println(p);
            }
        return p;
        
       
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }
    
   
    @Override
  public float TotalProduct(){
      try{
                float totalproduct=0.0f;
                PreparedStatement pst;
                String requete = "SELECT COUNT(id) FROM product ";
                pst = cnx.prepareStatement(requete);
                ResultSet rs = pst.executeQuery(requete);
                while (rs.next()) {
                   totalproduct = rs.getFloat(1);  
                   System.out.println("😃😈 total product😍 succeeds 😈😃");
                   //System.out.println(totalproduct);
                    }
                return totalproduct;
                
                
      }
      catch (SQLException ex) {
        } 
        return 0.0f;
  }
      
  
        
        public ObservableList<Produit> triProduit(){

        String query="select * from product ORDER BY price desc ";
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
            ProduitLis.add(p);
            //System.out.println(ProduitLis+"\n");
            }   } catch (SQLException ex) {
           // Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        } 
     return  ProduitLis;
    }
  
  
  
          public ObservableList<Produit> MaxRate(){

        //String query="select Max(rate) from product ";
        //String query ="SELECT `id`, `name`, `description`, `price`, `rate`, `image`, `release_date` FROM `product`";
        String query ="SELECT * FROM `product` ORDER BY rate desc";
        PreparedStatement pre;
        try {
            pre = cnx.prepareStatement(query);
             ResultSet rst =pre.executeQuery();
             int i=0;
      
            while(rst.next()&& i<5)
            
            {   
                
            int id = rst.getInt("id");
            String name = rst.getString("name");
            String description = rst.getString("description");
            float price = rst.getFloat("price");
            int rate = rst.getInt("rate");
            String image = rst.getString("image");
            String release_date = rst.getString("release_date");
            
            Produit p = new Produit (id,name,description,price,rate,image,release_date+"\n");
                
             ProduitListt.add(p);
             i=i+1;
                }
             
            //System.out.println(i);
           // System.out.println(ProduitLis+"\n");
               } catch (SQLException ex) {
           // Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        } 
     return  ProduitListt;
    }
}
