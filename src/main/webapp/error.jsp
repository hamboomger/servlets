<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" charset="UTF-8">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>List page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.6.2/css/bulma.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.4/css/all.css">
</head>
<body>
    <section class="hero">
        <div class="hero-body">
            <div class="container">
                <h1 class="title">
                    Error <%= request.getAttribute("javax.servlet.error.status_code") %>
                </h1>
                <h2 class="subtitle">
                    <%= request.getAttribute("javax.servlet.error.exception") %>
                </h2>
                <a href="products/" class"is-button is-text">Retry</a>
            </div>
        </div>
    </section>
</body>
</html>