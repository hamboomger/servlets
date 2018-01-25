$(".delete-product").click(function() {
	let rowElem = $(this).parent().parent()
    let productId = rowElem.attr("productId")

    console.log(productId)

	deleteProductFromServer(productId)

	rowElem.fadeTo("slow",0.7, function(){
           $(this).remove();
    })
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