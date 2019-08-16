<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'une commande</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <div>
            <form enctype=multipart/form-data method="post" action="<c:url value="/creationCommande"/>" >
                <fieldset>
                    <legend>Informations client</legend>
                    <%-- Si et seulement si la Map des clients en session n'est pas vide, alors on propose un choix à l'utilisateur --%>
                    <c:if test="${ !empty sessionScope.clients }">
                    
                        <input type="radio" id="choixNouveauClient" name="choixNouveauClient" value="nouveauClient" onclick="clickButton('nouveauClient')"/>Nouveau Client
                        <input  type="radio" id="choixNouveauClient" name="choixNouveauClient" value="ancienClient" onclick="clickButton('ancienClient')" />Ancien Client
                           <br/><br />
                    </c:if>
                     
                    <c:set var="client" value="${ commande.client }" scope="request" />
                    <div id="nouveauClient">
                        <c:import url="/inc/inc_client_form.jsp" />
                    </div>
                     
                    <%-- Si et seulement si la Map des clients en session n'est pas vide, alors on crée la liste déroulante --%>
                    <c:if test="${ !empty sessionScope.clients }">
                    <div id="ancienClient" style="display:none">
                        <select name="listeClients" id="listeClients">
                            <option value="">Choisissez un client...</option>
                            <%-- Boucle sur la map des clients --%>
                            <c:forEach items="${ sessionScope.clients }" var="mapClients">
                            <%--  L'expression EL ${mapClients.value} permet de cibler l'objet Client stocké en tant que valeur dans la Map,
                                  et on cible ensuite simplement ses propriétés nom et prenom comme on le ferait avec n'importe quel bean. --%>
                            <option value="${ mapClients.value.nom }">${ mapClients.value.prenom } ${ mapClients.value.nom }</option>
                            </c:forEach>
                        </select>
                    </div>
                    </c:if>
                </fieldset>
                <fieldset>
                    <legend>Informations commande</legend>
                     
                    <label for="dateCommande">Date <span class="requis">*</span></label>
                    <input type="text" id="v" name="dateCommande" value="<c:out value="${commande.date}"/>" size="30" maxlength="30" disabled />
                    <span class="erreur">${form.erreurs['dateCommande']}</span>
                    <br />
                     
                    <label for="montantCommande">Montant <span class="requis">*</span></label>
                    <input type="text" id="montantCommande" name="montantCommande" value="<c:out value="${commande.montant}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.erreurs['montantCommande']}</span>
                    <br />
                     
                    <label for="modePaiementCommande">Mode de paiement <span class="requis">*</span></label>
                    <input type="text" id="modePaiementCommande" name="modePaiementCommande" value="<c:out value="${commande.modePaiement}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.erreurs['modePaiementCommande']}</span>
                    <br />
                     
                    <label for="statutPaiementCommande">Statut du paiement</label>
                    <input type="text" id="statutPaiementCommande" name="statutPaiementCommande" value="<c:out value="${commande.statutPaiement}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.erreurs['statutPaiementCommande']}</span>
                    <br />
                     
                    <label for="modeLivraisonCommande">Mode de livraison <span class="requis">*</span></label>
                    <input type="text" id="modeLivraisonCommande" name="modeLivraisonCommande" value="<c:out value="${commande.modeLivraison}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.erreurs['modeLivraisonCommande']}</span>
                    <br />
                     
                    <label for="statutLivraisonCommande">Statut de la livraison</label>
                    <input type="text" id="statutLivraisonCommande" name="statutLivraisonCommande" value="<c:out value="${commande.statutLivraison}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.erreurs['statutLivraisonCommande']}</span>
                    <br />
                     
                    <p class="info">${ form.resultat }</p>
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
    
        <script>
        	function clickButton(str){
        		if(str == "nouveauClient"){
        			document.getElementById("nouveauClient").style.display = "block";
        			document.getElementById("ancienClient").style.display = "none";
        		}else{
        			document.getElementById("nouveauClient").style.display = "none";
        			document.getElementById("ancienClient").style.display = "block";
        		}
        	}
        </script>
        
    </body>
</html>