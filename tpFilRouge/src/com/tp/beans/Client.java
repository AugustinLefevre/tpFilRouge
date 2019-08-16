package com.tp.beans;

public class Client {

	    private String nom;
	    private String prenom;
	    private String adresse;
	    private String telephone;
	    private String email;
	    private String photo;
	    private Long id;
	    
	    public void setId(Long id) {
	    	this.id = id;
	    }
	    public Long getId() {
	    	return id;
	    }
	 
	    public void setNom( String nom ) {
	        this.nom = nom;
	    }
	 
	    public String getNom() {
	        return nom;
	    }
	    
	    public void setPhoto( String image ) {
	        this.photo = image;
	    }
	 
	    public String getPhoto() {
	        return photo;
	    }
	 
	    public void setPrenom( String prenom ) {
	        this.prenom = prenom;
	    }
	 
	    public String getPrenom() {
	        return prenom;
	    }
	 
	    public void setAdresse( String adresse ) {
	        this.adresse = adresse;
	    }
	 
	    public String getAdresse() {
	        return adresse;
	    }
	 
	    public String getTelephone() {
	        return telephone;
	    }
	 
	    public void setTelephone( String telephone ) {
	        this.telephone = telephone;
	    }
	 
	    public void setEmail( String email ) {
	        this.email = email;
	    }
	 
	    public String getEmail() {
	        return email;
	    }	
}
