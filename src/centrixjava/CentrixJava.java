/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrixjava;

import Model.Category;
import Model.Produit;
import Services.CategoryService;
import Services.ProduitService;
import Util.MaConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Refka
 */
public class CentrixJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        MaConnection mc = MaConnection.getInstance();
        // TODO code application logic here
        //ajout produit
     /* ProduitService p1 = new ProduitService();
        Produit p =new Produit("iphone12 pro max", "ios", (float) 99.40,4," hhh","2022-04-05");
        p1.AddProduit(p);*/
      

       //update produit  
      /* ProduitService p2 = new ProduitService();
        Produit prod =new Produit(71,"iphone12 pro max", "ios15", (float) 10.15,2," refhh","2022-04-05");
         p2.ModifierProduit(prod);*/
       
       //delete produit
       /*ProduitService p3 = new ProduitService();
        Produit prod1 =new Produit(71);
         p3.SupprimerProduit(prod1);*/
       
       //affichage produit
    /*  ProduitService p4 = new ProduitService();
       System.out.println(p4.getProduitList());*/
        
    
      //affichage produit by id
    /* ProduitService p5 = new ProduitService();
     Produit prod1 =new Produit(70);
       System.out.println(p5.getProduit(prod1));*/
      
      
      
      
       //ajout category
      /* CategoryService c1 = new CategoryService();
        Category c =new Category("categoryyy");
        c1.AddCategory(c);*/
      

       //update category  
       /*CategoryService c2 = new CategoryService();
        Category cat =new Category(2,"category10");
         c2.ModifierCategory(cat);*/
       
       //delete category
      /* CategoryService c3 = new CategoryService();
        Category cat1 =new Category(3);
         c3.SupprimerCategory(cat1);*/
       
      
      
       //affichage category
      /*CategoryService c4 = new CategoryService();
       System.out.println(c4.getCategoryList());*/
      
      
      
      //affichage category by id
     /* CategoryService p5 = new CategoryService();
     Category categ1 =new Category(4);
       System.out.println(p5.getCategory(categ1));*/
     
     
     
     // ajouter produit avec categorie 
      /*ProduitService c1 = new ProduitService();
            Produit p =new Produit(59);
            Category c =new Category(2);
            c1.InsertProdSelonCat(p,c);*/
      
       // affichage produit par category
       /* ProduitService c1 = new ProduitService();
            Produit p =new Produit();
            Category c =new Category(2);
            System.out.println(c1.getProduitByCtegory(p,c));*/
       
       
       // tri produit 
        /*ProduitService c1 = new ProduitService();
       System.out.println( c1.triProduit());*/
        
        //max rate
      /*  ProduitService c1 = new ProduitService();
       System.out.println( c1.MaxRate());*/
        
       
        
     }
    
}
