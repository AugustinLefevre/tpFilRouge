<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>Liste des Clients</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
	</head>
	<body>
		<c:import url="/inc/menu.jsp" />
        <div id="corps">
        <c:choose>
           
            <c:when test="${ empty sessionScope.clients }">
            </c:when>
            <c:otherwise>
            <table>
                <tr>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Adresse</th>
                    <th>Téléphone</th>
                    <th>Email</th>
                    <th>Photo</th>
                    <th class="action">Action</th>                   
                </tr>
                <c:forEach items="${ sessionScope.clients }" var="mapClients" varStatus="boucle">
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <td><c:out value="${ mapClients.value.nom }"/></td>
                    <td><c:out value="${ mapClients.value.prenom }"/></td>
                    <td><c:out value="${ mapClients.value.adresse }"/></td>
                    <td><c:out value="${ mapClients.value.telephone }"/></td>
                    <td><c:out value="${ mapClients.value.email }"/></td>
                    <td>
                        <c:if test="${ !empty mapClients.value.photo }">
                            <c:set var="image"><c:out value="${ mapClients.value.photo }"/></c:set>
                            <a href="<c:url value="/image/${ mapClients.value.photo }"/>">Voir</a>
                            <c:out value="${ mapClients.value.photo }"/>
                        </c:if>
                    </td>
                    
                    <td class="action">
                        <a href="<c:url value="/suppressionClient"><c:param name="idClient" value="${ mapClients.key }" /></c:url>">
                            Supprimer
                        </a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            </c:otherwise>
        </c:choose>
        </div>
	</body>
</html>