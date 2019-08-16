<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>Liste des commandes</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
	</head>
	<body>
		<c:import url="/inc/menu.jsp" />
        <c:choose>
            <c:when test="${ empty sessionScope.commandes }">
            	<p>aucune commandes existante</p>
            </c:when>
            <c:otherwise>
            <table>
                <tr>
                    <th>Nom client</th>
                    <th>Prénom client</th>
                    <th>Adresse client</th>
                    <th>Téléphone client</th>
                    <th>Email client</th>
                    <th>Date</th>
                    <th>Montant</th>
                    <th>Mode de paiement</th>
                    <th>Status paiement</th>
                    <th>Mode de livraison</th>
                    <th>Status de livraison</th>
                    <th class="action">Action</th>                   
                </tr>
                <c:forEach items="${ sessionScope.commandes }" var="mapCommandes" varStatus="boucle">
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <td><c:out value="${ mapCommandes.value.client.nom }"/></td>
                    <td><c:out value="${ mapCommandes.value.client.prenom }"/></td>
                    <td><c:out value="${ mapCommandes.value.client.adresse }"/></td>
                    <td><c:out value="${ mapCommandes.value.client.telephone }"/></td>
                    <td><c:out value="${ mapCommandes.value.client.email }"/></td>
                    <td><c:out value="${ mapCommandes.value.date }"/></td>
                    <td><c:out value="${ mapCommandes.value.montant }"/></td>
                    <td><c:out value="${ mapCommandes.value.modePaiement }"/></td>
                    <td><c:out value="${ mapCommandes.value.statutPaiement }"/></td>
                    <td><c:out value="${ mapCommandes.value.modeLivraison }"/></td>
                    <td><c:out value="${ mapCommandes.value.statutLivraison }"/></td>
                  
                    <td class="action">
                        <a href="<c:url value="/suppressionCommande"><c:param name="idCommande" value="${ mapCommandes.key }" /></c:url>">
                            Supprimer
                        </a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            </c:otherwise>
        </c:choose>
	
	</body>
</html>