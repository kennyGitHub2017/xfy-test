//datepicker show
$('.date-picker').datepicker({
	orientation: "right",
	autoclose: true
});

var form1 = $('#data_form');
form1.validate({
	errorElement : 'span', //default input error message container
	errorClass : 'help-block help-block-error', // default input error message class
	focusInvalid : false, // do not focus the last invalid input
	ignore : "", // validate all fields including form hidden input
	messages : {
		'orderNo': '请输入请购单号',
		'buyUserId':'请选择采购员',
		'supplierId':'请选择供应商',
		'type':'请选择源单类型',
		'deliveryDate':'请选择要求交货日期'
	},
	rules : {
		'orderNo' : {
			required : true
		},
		'buyUserId' : {
			required : true
		},
		'supplierId' : {
			required : true,
			number:true
		},
		'type':{
			required : true
		},		
		'deliveryDate':{
			required : true
		},		
	},

	highlight : function(element) { // hightlight error inputs
		$(element).closest('.form-group').addClass('has-error'); // set error class to the control group
	},

	unhighlight : function(element) { // revert the change done by hightlight
		$(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
	},

	success : function(label) {
		label.closest('.form-group').removeClass('has-error'); // set success class to the control group
	}
});
	
	
	
	//根据sku异步获取产品信息
	function  goodsInfo(skuId){
		var p1 = "#"+skuId;
		var v = $.trim($(p1).val());
		if (v==""){
			return;
		}
		$.ajax({
			 url:xfy.contextPath+'/purchaserequest-order/goodsInfo', 
		    type:'post',
		    data:'sku='+v,     
		    async : false, //默认为true 异步     
		    error:function(){
		       alert('ajax error');     
		    }, 
		    success:function(data){
		    	var jsondata = eval("("+data+")");
		    	if(jsondata.result){
		    		var  goodsjson= jsondata.datas;
		    		//tr的id
		    		var tr = $(p1).parent().parent();
		    	//	alert(tr.attr("id"));
		    		var url = baseImgUrl + v.substring(0,2) + '/' + v + '/g-1-S.jpg';
		    		tr.children("td:eq(1)").html(goodsjson.name+"<input type='hidden' name='itemName' value='" + goodsjson.name+"' />");
		    		tr.children("td:eq(2)").html(goodsjson.color+"-"+goodsjson.size+"-"+goodsjson.rules+"-"+goodsjson.model);
		    		tr.children("td:eq(3)").html('<img class="small-img" src=" '+url+' ">');
		    		tr.children("td:eq(4)").html(goodsjson.unit+"<input type='hidden' name='itemUnit' value='" + goodsjson.unit+"' />");
		    		tr.children("td:eq(5)").html(goodsjson.newcost);
		    		var t = goodsjson.newcost!=''?goodsjson.newcost:goodsjson.cost;
		    		tr.children("td:eq(6)").html("<input type='text' name='itemCost' style='width:80px' value='{0}' onblur='totalCost($(this))' />".format(t));
		    		tr.children("td:eq(7)").html("<input type='text' name='itemCount' style='width:80px' onblur='totalCost($(this))' />");
		    		tr.children("td:eq(8)").html("");
		    		tr.children("td:eq(9)").html($("#deliveryDate").val()+"<input type='hidden' name='itemDeliveryDate' value='" + $("#deliveryDate").val()+"' />");
		    		if (goodsjson.categoryId){
		    			tr.children("td:eq(10)").html(goodsjson.categoryName+"<input type='hidden' name='itemCategory' value='" + goodsjson.categoryId+"' />");	
		    		}else{
		    			tr.children("td:eq(10)").html("暂无分类");
		    		}
		    		
		    		
		    	}
		    }
		}); 
	}

	function addRow(){
		var tablerow = $("#tbody").find("tr").length+1;
		var tr = $("#tr"+(tablerow-1));
		var newtr = tr.clone(true);
		newtr.attr("id","tr"+tablerow);
		var td0 = "<input type='text' id='goodsSku"+tablerow+"' class='form-control input-small' placeholder='Enter SKU' name='itemSku' onchange='goodsInfo(this.id)' />";
		newtr.children("td:eq(0)").html(td0);
		newtr.children("td:eq(1)").html("");
		newtr.children("td:eq(2)").html("");
		newtr.children("td:eq(3)").html("");
		newtr.children("td:eq(4)").html("");
		newtr.children("td:eq(5)").html("");
		newtr.children("td:eq(6)").html("");
		newtr.children("td:eq(7)").html("");
		newtr.children("td:eq(8)").html("");
		newtr.children("td:eq(9)").html("");
		newtr.children("td:eq(10)").html("");
		newtr.children("td:eq(11)").html('<input value=\"Del\" type=\"button\" onclick=\"DelRow('+tablerow + ')\" >');
		$(newtr).insertAfter(tr);
	}
	
	function DelRow(id){
		
		var p1 = "#tr"+id;
		var trs = $("#tbody>tr");
		if (trs.length==1){
			alert("请至少保留一个sku信息");
			return ;
		}
		var tr = $(p1).closest('tr').remove();
		totalInfo();
	}
	
	
	function totalCost(element){
		var tr = $(element).closest('tr');
		var countEle = $.trim(tr.children("td:eq(7)").children(0).val());
		var costEle = $.trim(tr.children("td:eq(6)").children(0).val());
	//	alert(countEle.length+"\t"+costEle.length);
		if (0==costEle.length || isNaN(costEle)){
			return;
		}
		if(0==countEle.length || isNaN(countEle)){
			return;
		}
		var totalTd = tr.children("td:eq(8)");
		var total = parseFloat(countEle) * parseFloat(costEle);
		totalTd.html(total);
		//alert(total);
		totalInfo();
	}
	
	function totalInfo(){
		var NtotalCount=0;
		var NtotalCost=0;
		var tb = $("#tbody");
		tb.find(" tr td[countTotal=1]").each(function(){
			NtotalCount+=parseFloat($(this).children(0).val());
		});
		tb.find("tr td[costTotal=1]").each(function(){
			NtotalCost+=parseFloat($(this).text());
		});
	//	alert(NtotalCount+"\t" + NtotalCost);
		$("#totaldiv").html("数量汇总:" +NtotalCount+"&nbsp;&nbsp;&nbsp;金额汇总:"+NtotalCost);
	}
	
	
	$("#deliveryDate").change(function(){
		var v = $(this).val();
		$("td[deliveryDate=1]").each(function(){
				$(this).html(v+"<input type='hidden' name='itemDeliveryDate' value='" +v+"' />");
		});
	});
	
	//页面load完成
	$(function(){
		if ("${not empty purchasereq.deliveryDate}"){
			$("#deliveryDate").change();
		}
	});
	
	  function handleSupplierSelect(hiddenFieldId) {
			$(".c-supplier-picker").select2({
				minimumInputLength: 1,
				ajax: {
					delay: 1000,
					url: xfy.contextPath+'/supplier/page-json?status=1',
					data: function (params) {
						var data = {
							draw: 1,
							length: 10,
							start: 0,
							search: {value: params}
						};
						return JSON.stringify( data );
					},
					cache: true,
					params: {
						type: 'post',
						contentType:"application/json;charset=UTF-8"
					},
					results: function (result) {
						return {
							results: result.data
						};
					}
				},
				formatResult: function(item) {
					return item.companyName;
				},
				formatSelection: function(item) {
					$('#' + hiddenFieldId).val(item.id);
					return item.companyName;
				}
			});
		}
	   
	   $(function(){
		   handleSupplierSelect('supplierId');
	   });