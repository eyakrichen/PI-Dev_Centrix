/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Produit;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Refka
 */
public interface ProduitInterface {
    
    public void addProduit(Produit p);
    public void ModifierProduit(Produit p);
    public void SupprimerProduit (Produit p);
    //public List<Produit> AfficherProduit ();
    public ObservableList<Produit> getProduitList();
}
