<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en" charset="UTF-8">
<head>
    <meta charset="UTF-8">
    <title>Product edit page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.6.2/css/bulma.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.4/css/all.css">
</head>
<body>
    <div class="columns">
        <div class="column is-three-fifths is-offset-one-fifth">
            <div class="box">
                <form action="/products/edit?action=save_product&productId=${product.id}" method="POST">
                    <div class="field is-horizontal">
                        <div class="field-label is-normal">
                            <label class="label">Nazwa: </label>
                        </div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control">
                                    <input class="input" type="text"
                                    name="productName" value="${product.name}" required>
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
                                     name="productPrice" value="${product.price}" required>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="field is-horizontal">
                        <div class="field-label"></div>
                        <div class="field-body">
                            <div class="field is-grouped">
                                <div class="control">
                                    <input type="submit" class="button is-primary" value="Zapisz">
                                </div>
                                <div class="control">
                                    <a href="/products/list" class="button is-text">Anuluj</a>
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