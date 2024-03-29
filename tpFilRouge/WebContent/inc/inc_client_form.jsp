<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<label for="nomClient">Nom <span class="requis">*</span></label>
<input type="text" id="nomClient" name="nomClient" value="${client.nom}" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['nomClient']}</span>
<br />
 
<label for="prenomClient">Prénom </label>
<input type="text" id="prenomClient" name="prenomClient" value="${client.prenom}" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['prenomClient']}</span>
<br />
     
<label for="adresseClient">Adresse de livraison <span class="requis">*</span></label>
<input type="text" id="adresseClient" name="adresseClient" value="${client.adresse}" size="30" maxlength="60" />
<span class="erreur">${form.erreurs['adresseClient']}</span>
<br />
 
<label for="telephoneClient">Numéro de téléphone <span class="requis">*</span></label>
<input type="text" id="telephoneClient" name="telephoneClient" value="${client.telephone}" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['telephoneClient']}</span>
<br />
 
<label for="emailClient">Adresse email</label>
<input type="email" id="emailClient" name="emailClient" value="${client.email}" size="30" maxlength="60" />
<span class="erreur">${form.erreurs['emailClient']}</span>
<br />

<label for="photoClient">Photo</label>
<input type="file" id="photoClient" name="photoClient" value="${client.photo}" size="30" maxlength="60" />
<span class="erreur">${form.erreurs['photoClient']}</span>
<br />