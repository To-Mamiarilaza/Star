<%@page import="table.*, java.util.Vector" %>

<%
    Vector<Region> regions = Stock.getAllRegion();
    Vector<StockLibelle> listes;
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/style/stockage.css">
    <title>Stockage</title>
</head>
<body>
    <div class="container">
        <h1>STAR</h1>
        <h3>Stock dans tous les regions</h3>
        <p>Valeur actuelle du stock : <% out.print(Stock.getStockValue()); %> AR</p>
        <div class="listes row">
            <% for(int i = 0; i < regions.size(); i++) { 
               listes = regions.get(i).getStock();
            %>
                <div class="col-md-4">
                    <div class="stock">
                        <div>
                            <h4><% out.print(regions.get(i).getNom()); %></h4>
                            <p> <strong>valeur :</strong>  <% out.print(regions.get(i).getValeurStock(listes)); %> AR</p>
                            <table class="table">
                                <tr>
                                    <th>Produit</th>
                                    <th>Quantite</th>
                                    <th>Pu</th>
                                </tr>
                                <% for(int a = 0; a < listes.size(); a++) { %>
                                    <tr>
                                        <td><% out.print(listes.get(a).getProduit()); %></td>
                                        <td><% out.print(listes.get(a).getQuantite()); %></td>
                                        <td><% out.print(listes.get(a).getCump()); %></td>
                                    </tr>
                                <% } %>
  
                            </table>
                        </div>
                    </div>
                </div>
            <% } %>
            
                
        </div>
    </div>
</body>
</html>