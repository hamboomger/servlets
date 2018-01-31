<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <title>Product add page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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
            <form action="/products/add/" method="POST">
                <div class="field is-horizontal">
                    <div class="field-label is-normal">
                        <label class="label">Nazwa: </label>
                    </div>
                    <div class="field-body">
                        <div class="field">
                            <div class="control">
                                <input class="input" type="text" id="productNameTF" name="productName" required>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="field is-horizontal">
                    <div class="field-label is-normal">
                        <label class="label">Cena: </label>
                    </div>

                    <div class="field-body">
                        <div class="field">
                            <div class="control">
                                <input class="input" type="number" step="0.01" min="0"
                                       id="productPriceTF" name="productPrice" required>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="field is-horizontal">
                    <div class="field-label"></div>
                    <div class="field-body">
                        <div class="field is-grouped">
                            <div class="control">
                                <input type="submit" class="button is-primary"
                                       value="Utwórz">
                            </div>

                            <div class="control">
                                <a href="/products/list/" class="button is-text">Anuluj</a>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>