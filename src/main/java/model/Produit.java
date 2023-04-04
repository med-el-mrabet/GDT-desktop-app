package model;

public class Produit {
    private long id_produit;
    private String libelle;
    private String description;
    private double prix;
    private int onStock;

    public Produit() {
    }

    public Produit(long id_produit, String libelle, String description, double prix, int onStock) {
        this.id_produit = id_produit;
        this.libelle = libelle;
        this.description = description;
        this.prix = prix;
        this.onStock = onStock;
    }

    public long getId_produit() {
        return id_produit;
    }

    public void setId_produit(long id_produit) {
        this.id_produit = id_produit;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getOnStock() {
        return onStock;
    }

    public void setOnStock(int onStock) {
        this.onStock = onStock;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id_produit=" + id_produit +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", onStock=" + onStock +
                '}';
    }
}
