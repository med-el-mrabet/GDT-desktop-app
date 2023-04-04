package com.example.demo;

import DAO.ProduitDAO;
import model.Produit;

import java.sql.SQLException;
import java.util.List;

public class TestProduit {

    public static void main(String[] args) {
        try {
            ProduitDAO produitDAO = new ProduitDAO();
            Produit produit = new Produit(0l , "prod1" , "description" , 40d , 15);
            produitDAO.save(produit);
            List<Produit> produitList = produitDAO.getAll();
            for (Produit prod :produitList) {

                System.out.println(prod.toString());

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
