/**
 * 日期格式化
 * 使用方法 new Date().format('yyyy-MM-dd hh:mm:ss')
 * 
 * @param fmt 模式串
 * @returns string 格式化后的日期
 */
Date.prototype.format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "H+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
/**
 * 搜索条件清空
 */
$("#clearbtn").click(function(){
	$("form[search=1] :input").not(":button, :submit, :reset, :hidden,:radio").val("").remove("selected");
	$("form[search=1] :input:radio").removeAttr("checked");
	$.uniform.update('input:radio');
});
/**
 * 判断数组中是否含有某元素
 * @param obj
 * @returns {Boolean}
 */
Array.prototype.contains = function(obj) {  
    var i = this.length;  
    while (i--) {  
        if (this[i] === obj) {  
            return true;  
        }  
    }  
    return false;  
};

/**
 * format string
 * e.g.
 * 'This is a {0} not a {1}.'.format('string', 'num') => This is a string not a num.
 * 'This is a {0}, not a {1}. so parse to {2} first.'.format('string', 'num', 'num') => This is a string not a num. so parse to num first.
 * 'This is a {0}, not a {1}. so parse to {1} first.'.format('string', 'num') => This is a string not a num. so parse to num first.
 * 'This is a {0}, not a {1}. so parse to {2} first.'.format('string', 'num') => This is a string not a num. so parse to {2} first.
 */
String.prototype.format = function() {
	var args = arguments;
	return this.replace(/\{(\d+)\}/g, function(){
		var val = args[arguments[1]];
		//return (! val) ? arguments[0] : val;
		return (! val) ? '' : val;
	});
};

// 将form元素转成对象
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

// jquery ajax 请求统一处理 session 失效的情况
$(document).ajaxComplete(function(event, xhr, settings) {
	var location = xhr.getResponseHeader("Session-Timeout-Location");
	if (location != null && location.length > 0) {
		window.location.href = location;
	}
});

// jquery validator 添加 notEqualTo 规则
// 参数对象为 jq 选择器 或者一个函数, 如果函数返回false, 则直接不执行规则
$.validator.addMethod("notEqualTo", function (value, element, param) {  
	if (typeof param != 'function' && typeof param != 'string') {
		return true;
	}
	
	if (typeof param == 'function') {
		var selector = param();
		if (selector === false) {
			return true;
		}
		
		return typeof selector == 'string' ? value != $(selector).val() : true; 
	}
	
	return value != $(param).val();  
}, $.validator.format("两次输入不能相同!"));  


//jquery validator 添加 compareDate 规则
$.validator.addMethod("compareDate", function (value, element, param) {
         var startDate = jQuery(param).val();
         var date1 = new Date(Date.parse(startDate.replace(/\-/g, "/"))); 
         var date2 = new Date(Date.parse(value.replace(/\-/g, "/"))); 
         return date1 < date2; 
});  


// 使jquery支持自定义正则表达式
$.validator.addMethod("regex", 
		function(value, element, regexp) {
			var re = new RegExp(regexp);
			return this.optional(element) || re.test(value);
		}, 
	$.validator.format("输入不正确"));  

// jquery validator 消息提示
jQuery.extend(jQuery.validator.messages, {
    required: "必须输入",
	remote: "请修正该字段",
	email: "请输入正确格式的电子邮件",
	url: "请输入合法的网址",
	date: "请输入合法的日期",
	dateISO: "请输入合法的日期 (ISO).",
	number: "请输入合法的数字",
	digits: "只能输入整数",
	creditcard: "请输入合法的信用卡号",
	equalTo: "请再次输入相同的值",
	accept: "请输入拥有合法后缀名的字符串",
	maxlength: jQuery.validator.format("长度最多是 {0}位"),
	minlength: jQuery.validator.format("长度最少是 {0}位"),
	rangelength: jQuery.validator.format("长度要介于 {0}和 {1}之间"),
	range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
	max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
	min: jQuery.validator.format("请输入一个最小为 {0} 的值")
});

(function() {
	var xfy = window.xfy || {};

	// 设置 jquery dataTable 默认选项
	xfy.initDataTable = function() {
		$.extend($.fn.dataTable.defaults, {
			processing: true,
			searching : false,
			ordering : false,
			lengthMenu: [ 10, 20, 50, 100, 200, 300, 500 ],
			pageLength: 20,
			language : {
				search : "输入关键字: ",
				info : ", 当前页显示 _TOTAL_ 记录中的第  _START_ 到 _END_ 条",
				lengthMenu : "  _MENU_ 条记录每页",
				paginate : {
					previous : "上一页",
					next : "下一页",
					last : "最后一页",
					first : "第一页"
				},
				infoEmpty : "",	// table 分页行左边当没有记录是显示
				zeroRecords : "没有符合条件的记录",
				loadingRecords : "数据加载中...",
				processing : "数据加载中...",
				emptyTable : "没有符合条件的记录"
			},
			stateSave: true,
			stateSaveParams: function (settings, data) {
				data.search.search = '';
				data.start = 0;
				data.order = [];
			}
		});
	};
	
	// 使用 uniform 样式的 checkbox
	xfy.initUniformCheckbox = function() {
		if ($().uniform) {
			var test = $("input[type=checkbox]:not(.toggle, .make-switch, .icheck), input[type=radio]:not(.toggle, .star, .make-switch, .icheck)");
			if (test.size() > 0) {
				test.each(function() {
					if ($(this).parents(".checker").size() === 0) {
						$(this).show();
						$(this).uniform();
					}
				});
			}
		}
	};
	
	xfy.toastr = function(type, title, msg, positione) {
		if (! toastr) {
			console.log('toastr undifined.');
			return;
		}
		
		toastr.options = {
			"closeButton": true,
			"debug": false,
			"positionClass": "toast-top-center",
			"onclick": null,
			"showDuration": "1000",
			"hideDuration": "1000",
			"timeOut": "3000",
			"extendedTimeOut": "1000",
			"showEasing": "swing",
			"hideEasing": "linear",
			"showMethod": "fadeIn",
			"hideMethod": "fadeOut"
		};
			
		toastr[type](msg, title);
	};
	
	// 常用html格式
	xfy.html = {
		// 能弹出 ajax modal框 的编辑链接 格式
		editLinkAjaxModal: '<a class="btn default btn-xs purple" href="{0}" data-target="#ajax" data-toggle="modal" data-backdrop="static"><i class="fa fa-edit"></i> {1}</a>',
		// 能弹出 普通modal框 的编辑链接 格式
		editLinkModal: '<a class="btn default btn-xs purple" href="#modal" data-toggle="modal" data-backdrop="static"><i class="fa fa-edit"></i> {0}</a>',
		// 普通编辑链接格式
		editLink: '<a class="btn default btn-xs purple" href="{0}"><i class="fa fa-edit"></i> {1}</a>',
		// 删除链接格式
		deleteLink: '<a href="{0}" class="btn default btn-xs black c-del-btns"><i class="fa fa-trash-o"></i> {1}</a>',
		// 需要确认的链接
		confirmLink: '<a href="{0}" class="btn default btn-xs green c-confirm-link"><i class="fa fa-trash-o"></i> {1}</a>',
		// 需要确认的链接
		confirmLink2: '<a href="{0}" class="btn default btn-xs black c-confirm-link"><i class="fa fa-trash-o"></i> {1}</a>',
		// 普通链接格式
		normalLink: '<a class="btn default btn-xs blue" href="{0}"><i class="fa fa-edit"></i> {1}</a>',
		// 普通链接格式新窗口
		normalLinkNewWin: '<a class="btn default btn-xs blue" href="{0}" target="_blank"><i class="fa fa-edit"></i> {1}</a>',
		// 能弹出大modal框 的编辑链接 格式
		editModalLarge: '<a class="btn default btn-xs purple" href="{0}" data-target="#modal-large" data-toggle="modal" data-backdrop="static"><i class="fa fa-edit"></i> {1}</a>'
	};
	
	// 常用正则表达式
	xfy.regex = {
		// 手机号匹配
		mobile: '(^1[358][0-9]{9}$)|(^((14[013578])|(16[058])|(17[05678]))[0-9]{8}$)|(^170[059][0-9]{7}$)'
	};
	
	// 根据数组数据填充到select
	xfy.fillSelect = function(jqSelector, items, valueField, textFiled) {
		if (!items || typeof items != 'object' || !items.push) {
			return;
		}
		
		var $select = jqSelector instanceof jQuery ? jqSelector : $(jqSelector);
		if ($select.length == 0) {
			return;
		}
		
		valueField = valueField ? valueField : 'id';
		textFiled = textFiled ? textFiled : 'text';
		
		var html = '<option value="">请选择...</option>';
		for (var i = 0; i < items.length; i++) {
			html += '<option value="{0}">{1}</option>'.format(items[i][valueField], items[i][textFiled])
		}
		$select.html(html);
	}
	
	/**
	 * 拼接以选择checkbox 元素的值，通过 , 分隔
	 * @prama checkboxName checkbox元素的 name 值
	 */
	xfy.getCheckedValues = function(checkboxName) {
		if (!checkboxName || typeof checkboxName != 'string' || checkboxName == '') {
			return '';
		}
		
		var t = [];
		$('input[type=checkbox][name=' + checkboxName + ']:checked').each(function(){
			t.push($(this).val());
		});
		
		return t.join(',');
	};
	
	/**
	 * 通过form提交一组数据
	 */
	xfy.requestByForm = function(option) {
		if (! option || !option.action || option.action == '') {
			return;
		}
		
		var opt = {
			action: option.action,
			method: (option.method ? option.method : 'get'),
			target: option.target ? option.target : '',
			data : option.data
		};
		
		var $form = $('<form></form>');
		$form.attr('action', opt.action).attr('method', opt.method).attr('target', opt.target);
		if (opt.data) {
			var data = opt.data;
			for (var p in data) {
				console.log(p + ' - ' + data[p]);
				$form.append('<input type="hidden" name="{0}" value="{1}" />'.format(p, data[p]));
			}
		}
		
		$('body').append($form);
		$form.submit();
		$form.remove();
	}
	
	window.xfy = xfy;
})();

$(function() {
	$(document).on("click", ".c-del-btns", function() {
		return confirm("请确认删除操作？");
	});
	$(document).on("click", ".c-confirm-link", function() {
		console.log($(this).attr('class'));
		return confirm("请确认是否继续操作？");
	});
	
	// 临时提示
	$('.fun-coming-soon').click(function() {
		xfy.toastr("info", "", "功能正在紧急开发中，敬请期待...");
		return false;
	});
	
	// 全选
	$('.c-all-select').click(function() {
		var selector = $(this).attr('data-selector');
		if (! selector) {
			console.log('missed option: data-selector');
			return false;
		}
		$(selector).attr('checked', true);
		$.uniform.update(selector);
	});
	
	// 全不选
	$('.c-no-select').click(function() {
		var selector = $(this).attr('data-selector');
		if (! selector) {
			console.log('missed option: data-selector');
			return false;
		}
		$(selector).attr('checked', false);
		$.uniform.update(selector);
	});

	// 反选
	$('.c-reserve-select').click(function() {
		var selector = $(this).attr('data-selector');
		if (! selector) {
			console.log('missed option: data-selector');
			return false;
		}
		$(selector).each(function() {
			var $this = $(this);
			$this.attr('checked', ! ($this.attr('checked') == 'checked'));
		});
		$.uniform.update(selector);
	});
	
	// 跟随checkbox 选定 
	$('input[type=checkbox].c-all-and-no-select').click(function() {
		var selector = $(this).attr('data-selector');
		if (! selector) {
			console.log('missed option: data-selector');
			return false;
		}
		
		$(selector).attr('checked', $(this).attr('checked') == 'checked');
		$.uniform.update(selector);
	});
	
	// 日期组件
	if ($.datepicker) {
		$('.date-picker').datepicker({
			orientation: "right",
			autoclose: true
		});
	}
});

if ($.validator) {
	//fix: when several input elements shares the same name, but has different id-ies....
	$.validator.prototype.elements = function() {
		var validator = this, rulesCache = {};

		// select all valid inputs inside the form (no submit or reset buttons)
		return $(this.currentForm)
				.find("input, select, textarea")
				.not(":submit, :reset, :image, [disabled]")
				.not(this.settings.ignore)
				.filter(
						function() {
							if (!this.name && validator.settings.debug
									&& window.console) {
								console.error("%o has no name assigned", this);
							}

							// select this element if this has the same name as one in cache (may be was dynamically added)
							if (this.name in rulesCache) {
								if (!validator.objectLength($(this).rules())) {
									$(this).rules('add', rulesCache[this.name]);
								}
								return true;
							}

							// select only the element with rules specified
							if (!validator.objectLength($(this).rules())) {
								return false;
							}

							// Add rules to the cache
							rulesCache[this.name] = $(this).rules();
							return true;
						});
	}
}