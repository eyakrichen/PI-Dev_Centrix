/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Category;
import Model.Produit;
import javafx.collections.ObservableList;

/**
 *
 * @author Refka
 */
public interface CategoryInterface {
    public void AddCategory(Category c);
    public void ModifierCtegory(Category c);
    public void SupprimerCategory (Category c);
    //public List<Produit> AfficherProduit ();
    public ObservableList<Category> getCategoryList();
    public Category  getCategory(Category c);
}
