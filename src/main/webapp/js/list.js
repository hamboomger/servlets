$(".delete-product").click(function() {
	let rowElem = $(this).parent().parent()
    let productId = rowElem.attr("productId")

	deleteProductFromServer(productId)
	rowElem.remove()
});

function deleteProductFromServer(productId) {
	fetch('/products/delete?productId='+productId, {
	    method: 'DELETE'
	}).then(response => {
	    console.log('Response status: ' + response.status)
	    if(!response.ok) {
	        console.log('Response message: ' + response.statusText)
	    }
	})
}