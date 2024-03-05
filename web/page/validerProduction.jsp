<%@page import="recette.Recette"%>
<%@page import="table.*, production.Production" %>
<% 
    Production outil = new Production();
    try {
        Recette recette = new Recette();
        Composant composant = recette.getProduit(request.getParameter("produit"));
        
        outil.produire(composant, Integer.valueOf(request.getParameter("quantite")), request.getParameter("region"));
        
        response.sendRedirect("produire.jsp");
    } 
    catch(Exception e) {
        
        HttpSession erreur = request.getSession();
        erreur.setAttribute("erreur", e);
        e.printStackTrace();
        response.sendRedirect("produire.jsp");
    }
%>