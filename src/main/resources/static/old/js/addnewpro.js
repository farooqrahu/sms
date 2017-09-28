/*window.onload = function() {
	// SMS PRODUCTS
	document.querySelector('#ifgprocat').onclick = function () {
		swal({
			title : "Add Product Category!",
			text : 'Type Product Category Name:',
			type : 'input',
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top",
			inputPlaceholder : "Category Name",
		}, function(inputValue) {
			$("input").attr("id", "ifgcategoryvalue");
			if (inputValue === false)
				return false;
			if (inputValue === "") {
				swal.showInputError("You need to write something!");
				return false;
			} else {
				var url = "/saveProductCategory";
				var msg = "Product Category";
				var id = "#ifgproductcat";
				test(inputValue, url, msg, id,'1');
				 swal("Added!", "Category Name Added!", "success"); 
			}
		});
	};
	document.querySelector('#ifgproname').onclick = function() {
		swal({
			title : "Add Product Name!",
			text : 'Type Product Name:',
			type : 'input',
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top",
			inputPlaceholder : "Product Name",
		}, function(inputValue) {

			if (inputValue === false)
				return false;

			if (inputValue === "") {
				swal.showInputError("You need to write something!");
				return false;
			} else {
				var url = "/saveProductName";
				var msg = "Prodcut";
				var id = "#ifgproductname";
				
				test(inputValue, url, msg, id,'1');
			}

		});
	};
	document.querySelector('#ifgprocolor').onclick = function() {
		swal({
			title : "Add New Color!",
			text : 'Type Color Name:',
			type : 'input',
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top",
			inputPlaceholder : "Type Color Name",
		}, function(inputValue) {

			if (inputValue === false)
				return false;

			if (inputValue === "") {
				swal.showInputError("You need to write something!");
				return false;
			} else {
				var url = "/saveColor";
				var msg = "Color";
				var id = "#ifgproductcolor";				
				test(inputValue, url, msg, id,'1');
			}
		});
	};
	document.querySelector('#ifgpromat').onclick = function() {
		swal({
			title : "Add New Material!",
			text : 'Type Material Name:',
			type : 'input',
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top",
			inputPlaceholder : "Material Name",
		}, function(inputValue) {

			if (inputValue === false)
				return false;

			if (inputValue === "") {
				swal.showInputError("You need to write something!");
				return false;
			} else {
				var url = "/saveMaterial";
				var msg = "Material";
				var id = "#ifgproductmat";
				test(inputValue, url, msg, id,'1');
			}

		});
	};
	document.querySelector('#ifgprocomp').onclick = function() {
		swal({
			title : "Add Material Composition!",
			text : 'Type Material Composition Name:',
			type : 'input',
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top",
			inputPlaceholder : "Material Composition Name",
		}, function(inputValue) {

			if (inputValue === false)
				return false;

			if (inputValue === "") {
				swal.showInputError("You need to write something!");
				return false;
			} else {
				var url = "/saveMaterialComposition";
				var msg = "Material Composition";
				var id = "#ifgproductcomp";
				test(inputValue, url, msg, id,'1');
			}

		});
	};
	document.querySelector('#ifgprocup').onclick = function() {
		swal({
			title : "Add New Cup!",
			text : 'Type Cup Alphabet:',
			type : 'input',
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top",
			inputPlaceholder : "Cup",
		}, function(inputValue) {

			if (inputValue === false)
				return false;

			if (inputValue === "") {
				swal.showInputError("You need to write something!");
				return false;
			} else {
				var url = "/saveCup";
				var msg = "Cup ";
				var id = "#ifgproductcup";
				test(inputValue, url, msg, id,'1');
			}

		});
	};
	document.querySelector('#ifgprosize').onclick = function() {
		swal({
			title : "Add New Size!",
			text : 'Type New Size:',
			type : 'input',
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top",
			inputPlaceholder : "Size",
		}, function(inputValue) {

			if (inputValue === false)
				return false;

			if (inputValue === "") {
				swal.showInputError("You need to write something!");
				return false;
			} else {
				var url = "/saveSize";
				var msg = "Size";
				var id = "#ifgproductsize";
				test(inputValue, url, msg, id,'1');
			}
		});
	};
	// Triumph Products
	document.querySelector('#triprocat').onclick = function() {
		swal({
			title : "Add Product Category!",
			text : 'Type Product Category Name:',
			type : 'input',
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top",
			inputPlaceholder : "Category Name",
		}, function(inputValue) {

			if (inputValue === false)
				return false;

			if (inputValue === "") {
				swal.showInputError("You need to write something!");
				return false;
			} else {
				var url = "/saveProductCategory";
				var msg = "Product Category";
				var id = "#triproductcat";
				var brand = $('#triumphCode').val();
				console.log(brand);
				test(inputValue, url, msg, id,brand);
			}

		});
	};
	document.querySelector('#triproname').onclick = function() {
		swal({
			title : "Add Product Name!",
			text : 'Type Product Name:',
			type : 'input',
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top",
			inputPlaceholder : "Product Name",
		}, function(inputValue) {

			if (inputValue === false)
				return false;

			if (inputValue === "") {
				swal.showInputError("You need to write something!");
				return false;
			} else {
				var url = "/saveProductName";
				var msg = "Product Name";
				var id = "#triproductname";
				test(inputValue, url, msg, id,'2');
			}

		});
	};
	document.querySelector('#triprocolor').onclick = function() {
		swal({
			title : "Add New Color!",
			text : 'Type Color Name:',
			type : 'input',
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top",
			inputPlaceholder : "Type Color Name",
		}, function(inputValue) {

			if (inputValue === false)
				return false;

			if (inputValue === "") {
				swal.showInputError("You need to write something!");
				return false;
			} else {
				var url = "/saveColor";
				var msg = "Color";
				var id = "#tricolor";
				test(inputValue, url, msg, id,'2');
			}

		});
	};
	document.querySelector('#triprotype').onclick = function() {
		swal({
			title : "Add New Product Type!",
			text : 'Type Product Type:',
			type : 'input',
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top",
			inputPlaceholder : "Product Type",
		}, function(inputValue) {

			if (inputValue === false)
				return false;

			if (inputValue === "") {
				swal.showInputError("You need to write something!");
				return false;
			} else {
				var url = "/saveProductType";
				var msg = "Product Type";
				var id = "#triproducttype";
				test(inputValue, url, msg, id,'2');
			}

		});
	};
	document.querySelector('#triprocup').onclick = function() {
		swal({
			title : "Add New Cup!",
			text : 'Type Cup Alphabet:',
			type : 'input',
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top",
			inputPlaceholder : "Cup",
		}, function(inputValue) {
			$("input").attr("id", "tricup");

			if (inputValue === false)
				return false;

			if (inputValue === "") {
				swal.showInputError("You need to write something!");
				return false;
			} else {
				var url = "/saveCup";
				var msg = "Cup";
				var id = "#tricup";
				test(inputValue, url, msg, id,'2');
			}

		});
	};
	document.querySelector('#triprosize').onclick = function() {
		swal({
			title : "Add New Size!",
			text : 'Type New Size:',
			type : 'input',
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top",
			inputPlaceholder : "Size",
		}, function(inputValue) {

			if (inputValue === false)
				return false;

			if (inputValue === "") {
				swal.showInputError("You need to write something!");
				return false;
			} else {
				var url = "/saveSize";
				var msg = "Size";
				var id = "#trisize";
				test(inputValue, url, msg, id,'2');
			}

		});
	};

};

function test(inputValue, url, msg, optId,brand) {
	$.ajax({
		type : "post",
		url : url,		
		 data: {
             'inputValue': inputValue,
             'brand': brand
         },
		success : function(data) {
		}
	}).done(
			function(data) {
				if (data != 0) {
					$(optId).append(
							'<option value="' + data + '" selected="selected">'
									+ inputValue + '</option>');
					swal("Added!", msg + "Name Added!", "success");

				} else {
					swal("Oops", msg + " Creation Failed", "error");
				}
			}).error(function(data) {
		swal("Oops", "We couldn't connect to the server!", "error");
	});
};
*/