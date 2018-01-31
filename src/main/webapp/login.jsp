<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <title>Product edit page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.6.2/css/bulma.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.4/css/all.css">
</head>
<body>
<div class="columns">
    <div class="column is-three-fifths-tablet is-offset-one-fifth-tablet is-one-third is-offset-one-third">
        <div class="box">
            <form action="/products/login/" method="POST">
                <div class="field is-horizontal">
                    <div class="field-label is-normal">
                        <label class="label">Login: </label>
                    </div>
                    <div class="field-body">
                        <div class="field">
                            <div class="control">
                                <input class="input" type="text" name="login" required>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="field is-horizontal">
                    <div class="field-label is-normal">
                        <label class="label">Hasło: </label>
                    </div>

                    <div class="field-body">
                        <div class="field">
                            <div class="control">
                                <input class="input" type="password" name="password" required>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="field is-horizontal">
                    <div class="field-label"></div>
                    <div class="field-body">
                        <div class="field is-grouped">
                            <div class="control">
                                <input type="submit" class="button is-primary" value="Zaloguj się">
                            </div>

                            <div class="control">
                                <a href="/products/list/" class="button is-text">Zarejestruj się</a>
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