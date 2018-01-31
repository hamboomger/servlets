//Widzę, że pisane w ES6 - niestety normalnie raczej tego nie robimy z racji tego, że IE tego nie obsługuje :(
//Nie zapominaj o średnikach, a console.log to domyślam się, że zapomnienie
$(".delete-product").click(function () {
    let rowElem = $(this).parent().parent();
    let productId = rowElem.attr("productId");

    deleteProductFromServer(productId);
    rowElem.remove()
});

function deleteProductFromServer(productId) {
    fetch('/products/delete/?productId=' + productId, {
        method: 'DELETE';
    }).then(response = > {
        console.log('Response status: ' + response.status)
    if (!response.ok) {
        console.log('Response message: ' + response.statusText)
    }
})
}