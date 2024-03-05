<%-- 
    Document   : accueil
    Created on : 17 janv. 2023, 01:43:35
    Author     : To Mamiarilaza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.Vector, table.HistFabLibelle, table.*, recette.Recette"%>
<% 
    Vector<HistFabLibelle> historiques = Stock.getHistoriqueFabrication();
    Vector<Composant> produits = Recette.getAllProduit();
    Vector<Region> regions = Stock.getAllRegion();
    
    HttpSession erreur = request.getSession();
    String message = "";
    if(erreur.getAttribute("erreur") != null) {
        message = ((Exception) erreur.getAttribute("erreur")).getMessage();
        erreur.removeAttribute("erreur");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/style/produire.css">
    <title>Production</title>
</head>
<body>
    <div class="container">
        <h1>STAR : <span>Production</span></h1><br>
        <form class="formulaire form" method="post" action="validerProduction.jsp">
            <div class="mb-3">
                <label for="exampleSelectProduit" class="form-label">Produit</label>
                <select name="produit" id="exampleSelectProduit" class="form-select">
                    <% for(int i = 0; i < produits.size(); i++) { %>
                        <option value="<% out.print(produits.get(i).getId()); %>"><% out.print(produits.get(i).getNom()); %></option>
                    <% } %>
                </select>
            </div>
            <div class="mb-3">
                <label for="exampleSelectRegion" class="form-label">Region</label>
                <select name="region" id="exampleSelectRegion" class="form-select">
                    <% for(int i = 0; i < regions.size(); i++) { %>
                        <option value="<% out.print(regions.get(i).getId()); %>"><% out.print(regions.get(i).getNom()); %></option>
                    <% } %>
                </select>
            </div>
            <div class="mb-3">
                <label for="exampleInputQuantite" class="form-label">Region</label>
                <input type="number" name="quantite" class="form-control" id="exampleInputQuantite" value="50">
            </div>
            <% if(!message.equals("")) { %>
                <p class="erreur"><% out.print(message); %></p>
            <% }%>
            <button class="btn btn-info">Valider</button>

            <hr class="my-5">

            <h2>Historique Production</h2>
            <table class="table">
                <tr>
                    <th>Date</th>
                    <th>Produit</th>
                    <th>Quantite</th>
                    <th>Region</th>
                </tr>
                
                <% for(int i = 0; i < historiques.size(); i++) {    %>
                    <tr>
                        <td><% out.print(historiques.get(i).getDateProduction()); %></td>
                        <td><% out.print(historiques.get(i).getProduit()); %></td>
                        <td><% out.print(historiques.get(i).getQuantite()); %></td>
                        <td><% out.print(historiques.get(i).getRegion()); %></td>
                    </tr>
                <% } %>
            </table>
        </form>
    </div>
</body>
</html>