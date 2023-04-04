package com.example.demo;

import DAO.LivreurDAO;
import model.Livreur;

import java.sql.SQLException;
import java.util.List;

public class Test {

    public static void main(String[] args) {

// trait bloc try catch
        try {


            LivreurDAO livreurDAO = new LivreurDAO();
           Livreur livV = new Livreur(0l , "liv3" , "200000000");

            livreurDAO.save(livV);
            Livreur livv = new Livreur(35l , "liv4" , "200");
            livreurDAO.update(livv);


            //Livreur liv2 = new Livreur(0l , "liv2" , "100000000");


           // livreurDAO.save(liv2);


          List<Livreur> livlist =  livreurDAO.getAll();

            for (Livreur liv :livlist) {

                System.out.println(liv.toString());

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
