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
		'purchaseDate':'请选择采购日期',
		'deliveryDate':{required:'请选择交货日期',compareDate:'交货日期不得早于采购日期'},
		'supplierId':'请选择供应商',
		'payMethod':'请选择支付方式',
		'freight':{
			'number':'运费请输入数字'
		}
	},
	rules : {
		'orderNo' : {
			required : true
		},
		'purchaseDate' : {
			required : true
		},
		'deliveryDate':{
			required : true,
			compareDate: "#purchaseDate" 
		},
		'supplierId' : {
			required : true,
		},
		'payMethod' : {
			required : true
		},	
		'freight':{
			number:true
		},
		'itemSku' : { required : true},
		'itemCount' : { required : true, digits: true, min : 1 },
		'itemCost' : { required : true, number: true },
		'itemWeight' : { required : true }
	},

	highlight : function(element) { // hightlight error inputs
		$(element).parent().addClass('has-error'); // set error class to the control group
	},

	unhighlight : function(element) { // revert the change done by hightlight
		$(element).parent().removeClass('has-error'); // set error class to the control group
	},

	success : function(label) {
		label.parent().removeClass('has-error'); // set success class to the control group
	}
});
	
	//根据sku异步获取产品信息
	function  goodsInfo(skuId){
		var tablerow = $("#tbody").find("tr").length;
		var p1 = "#"+skuId;
		var v = $.trim($(p1).val());
		$(p1).val(v);
		var supply = $("#supplierId").val();
		if (v==""){
			return;
		}
		var data ='sku='+v;
		if (supply!=''){
			data = data +"&suppliyId=" +supply;
		}
		$.ajax({
			 url:xfy.contextPath+'/purchaserequest-order/goodsInfo', 
		    type:'post',
		    data:data,     
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
		    		var url = baseImgUrl + v.substring(0,2) + '/' + v + '/g-1-S.jpg';
		    		tr.children("td:eq(1)").html(goodsjson.name+"<input type='hidden' name='itemName' value='" + goodsjson.name+"' />");
		    		tr.children("td:eq(2)").html((goodsjson.color==null?"":goodsjson.color)+"-"+(goodsjson.size==null?"":goodsjson.size)+"-"+(goodsjson.rules==null?"":goodsjson.rules)+"-"+(goodsjson.model==null?"":goodsjson.model));
		    		tr.children("td:eq(3)").html('<img class="small-img" src=" '+url+' ">');
		    		tr.children("td:eq(4)").html(goodsjson.unit+"<input type='hidden' name='itemUnit' value='" + goodsjson.unit+"' />");
		    		tr.children("td:eq(5)").html(goodsjson.newcost);
		    		if (goodsjson.price){
		    			tr.children("td:eq(6)").html("<input type='text' name='itemCost' style='width:60px'  value={0} id='{1}' onblur='totalCost($(this))' />".format(goodsjson.price,"itemCost"+skuId));
		    		}
		    		tr.children("td:eq(7)").html("<input type='text' name='itemCount' style='width:60px' id='{0}' onblur='totalCost($(this))' />".format("itemCount"+skuId));
		    		tr.children("td:eq(8)").html("");
		    		tr.children("td:eq(9)").html("<input type='text' name='itemWeight' value={0} id='{1}' onblur='totalCost($(this))' style='width:60px' /> ".format(goodsjson.weight,"itemWeight"+skuId));
		    		if (goodsjson.categoryId){
		    			tr.children("td:eq(10)").html(goodsjson.categoryName+"<input type='hidden' name='itemCategory' value='" + goodsjson.categoryId+"' />");	
		    		}else{
		    			tr.children("td:eq(10)").html("暂无分类");
		    		}
		    		tr.children("td:eq(11)").html("<input type=\"button\" id=\"delBtn\" onclick=\"DelRow('{0}')\" value=\"Del\">".format(tablerow));
		    	}else{
		    		alert("sku不存在");
		    		$(p1).val("");
		    		var tr = $(p1).closest('tr');;
		    		tr.children("td:eq(1)").html("");
		    		tr.children("td:eq(2)").html("");
		    		tr.children("td:eq(3)").html("");
		    		tr.children("td:eq(4)").html("");
		    		tr.children("td:eq(5)").html("");
		    		tr.children("td:eq(6)").html("<input type='text' name='itemCost' style='width:60px'  onblur='totalCost($(this))' />");
		    		tr.children("td:eq(7)").html("<input type='text' name='itemCount' style='width:60px' onblur='totalCost($(this))' />");
		    		tr.children("td:eq(8)").html("");
		    		tr.children("td:eq(9)").html("<input type='text' name='itemWeight' value='' onblur='totalCost($(this))' style='width:60px' />");
		    		tr.children("td:eq(10)").html("");
		    	}
		    }
		}); 
	}

	function addRow(){
		var tablerow = $("#tbody").find("tr").length+1;
		var tr = $("#tr"+(tablerow-1));
		var newtr = tr.clone(true);
		newtr.attr("id","tr"+tablerow);
		var td0 = "<input type='text' id='goodsSku"+tablerow+"' class='form-control' placeholder='Enter SKU' name='itemSku' onchange='goodsInfo(this.id)' />";
		newtr.children("td:eq(0)").html(td0);
		newtr.children("td:eq(1)").html("");
		newtr.children("td:eq(2)").html("");
		newtr.children("td:eq(3)").html("");
		newtr.children("td:eq(4)").html("");
		newtr.children("td:eq(5)").html("");
		newtr.children("td:eq(6)").html("<input type='text' name='itemCost' style='width:60px'  onblur='totalCost($(this))' />");
		newtr.children("td:eq(7)").html("<input type='text' name='itemCount' style='width:60px' onblur='totalCost($(this))' />");
		newtr.children("td:eq(8)").html("");
		newtr.children("td:eq(9)").html("<input type='text' name='itemWeight' value='' onblur='totalCost($(this))' style='width:60px' />");
		newtr.children("td:eq(10)").html("");
		newtr.children("td:eq(11)").html('<input value=\"Del\" type=\"button\" onclick=\"DelRow('+tablerow +')\" >');
		$(newtr).insertAfter(tr);
	}
	
	function DelRow(id){
		var p1 = "#tr"+id;
		var trs = $("#tbody>tr");
		if (trs.length==1){
			alert("请至少保留一个sku信息");
			return ;
		}
		$(p1).closest('tr').remove();
		totalInfo();
	}
	
	function totalCost(element){
		var tr = $(element).closest('tr');
		var costEle = $.trim(tr.children("td:eq(6)").children(0).val());
		var countEle = $.trim(tr.children("td:eq(7)").children(0).val());
		var weightEle = $.trim(tr.children("td:eq(9)").children(0).val());
	//	alert(countEle.length+"\t"+costEle.length);
		if (0==costEle.length || isNaN(costEle)){
			return;
		}
		if(0==countEle.length || isNaN(countEle)){
			return;
		}
		if(0==weightEle.length || isNaN(weightEle)){
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
		var NtotalWeight=0;
		var tb = $("#tbody");
		tb.find(" tr td[countTotal=1]").each(function(){
			NtotalCount+=parseFloat($(this).children(0).val());
		});
		tb.find("tr td[costTotal=1]").each(function(){
			NtotalCost+=parseFloat($(this).text());
		});
		tb.find("tr td[weightTotal=1]").each(function(){
			var tr = $(this).closest('tr');
			var count = tr.children("td:eq(7)").children(0).val();
			NtotalWeight+=parseFloat($(this).children(0).val())*parseFloat(count);
		});
	//	alert(NtotalCount+"\t" + NtotalCost);
		$("#totaldiv").html("数量汇总:" +NtotalCount+"&nbsp;&nbsp;&nbsp;金额汇总:"+NtotalCost+"&nbsp;&nbsp;重量汇总" + NtotalWeight);
	}
	
	
	$("#auditBtn").click(function(){
		if (confirm('请确认是否继续操作: ' + $(this).text() + '？')) {
			var id = $("#id").val();
			$.ajax({
				 url:xfy.contextPath+'/purchaseorder/audit', 
			    type:'post',
			    data:'id='+id,     
			    error:function(){
			       alert('ajax error'); 
			    }, 
			    success:function(data){
			    	var jsondata = eval("("+data+")");
			    	if(jsondata.result){
			    		alert("采购单审核成功");
			    		window.location.href=xfy.contextPath +"/purchaseorder/list";
			    	}else{
			    		alert(jsondata.errorMsg);
			    	}
			    }
			}); 
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