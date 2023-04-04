package model;

import java.time.LocalDate;
import java.util.Date;
public class Commande {
    private Long id_commande;
    private Long id_produit;
    private Long id_livreur;
    private LocalDate date_debut;
    private LocalDate date_fin;

    public Commande() {
    }

    public Commande(Long id_commande, Long id_produit, Long id_livreur, LocalDate date_debut, LocalDate date_fin) {
        this.id_commande = id_commande;
        this.id_produit = id_produit;
        this.id_livreur = id_livreur;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Long getId_commande() {
        return id_commande;
    }

    public void setId_commande(Long id_commande) {
        this.id_commande = id_commande;
    }

    public Long getId_produit() {
        return id_produit;
    }

    public void setId_produit(Long id_produit) {
        this.id_produit = id_produit;
    }

    public Long getId_livreur() {
        return id_livreur;
    }

    public void setId_livreur(Long id_livreur) {
        this.id_livreur = id_livreur;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id_commande=" + id_commande +
                ", id_produit=" + id_produit +
                ", id_livreur=" + id_livreur +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                '}';
    }
}

