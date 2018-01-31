<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" charset="UTF-8">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>List page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.6.2/css/bulma.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.4/css/all.css">
    <link rel="stylesheet" href="/products/css/main.css">
</head>
<body>
<div class="navbar has-shadow">
    <div class="navbar-menu is-active">
        <div class="navbar-end">
            <div class="navbar-item">
                <a href="?action=logout" class="button is-dark">Wyloguj</a>
            </div>
        </div>
    </div>
</div>
<div class="columns">
    <div class="column is-three-fifths-tablet is-offset-one-fifth-tablet is-one-third is-offset-one-third">
        <div class="box">
            <table class="table is-hoverable is-fullwidth">
                <tr>
                    <th>Nazwa</th>
                    <th>Cena</th>
                    <th></th>
                </tr>
                <c:forEach items="${products}" var="product" varStatus="status">
                    <tr productId="${product.id}">
                        <td>
                            <a href="/products/edit/?productId=${product.id}" class="is-text is-clickable">
                                    ${product.name}
                            </a>
                        </td>
                        <td>${product.price}</td>
                        <td class="has-text-centered">
                                <span class="icon is-clickable delete delete-product">
                                    <i class="fa fa-times-circle" aria-hidden="true"></i>
                                </span>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="control has-text-centered">
                <a href="/products/add/" class="button is-primary">Dodaj produkt</a>
            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="/products/js/list.js"></script>
</body>
</html>