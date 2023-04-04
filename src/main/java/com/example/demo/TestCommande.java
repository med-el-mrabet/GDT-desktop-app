package com.example.demo;

import DAO.CommandeDAO;
import model.Commande;

import java.util.List;
import java.util.Date;
import java.time.LocalDate;

import java.sql.SQLException;


public class TestCommande {
    public static void main(String[] args) {
        try {
            CommandeDAO commandeDAO = new CommandeDAO();
            Commande commande = new Commande(0l , 1l , 1l,LocalDate.now()  ,LocalDate.now());
            commandeDAO.save(commande);
            List<Commande> commandeList = commandeDAO.getAll();
            for (Commande com :commandeList) {

                System.out.println(com.toString());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
