function changeSelect(){
	$('#marca_id').val($('#list_marca').val());
};

function changeSelectCliente(){
	$('#cliente_id').val($('#list_cliente').val());
};

function sendProducts(){
	var inputSelectedProducts = "";
	$(".productClass").each(function(){
		if($(this).val() !== "" || $(this).val() !== undefined || $(this).val() !== '0'){
			inputSelectedProducts = inputSelectedProducts + this.value + '-' + $(this).data('product') + ';';
		}
	});
	$('#selectedProducts').val(inputSelectedProducts);
}

