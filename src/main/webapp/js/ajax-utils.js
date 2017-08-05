/** @author danny*/
var ajaxEngine = ajaxEngine || new AjaxEngine();

function AjaxEngine(){
	
	this.isAnsy = true;
	
	this.isCache = false;
	
	this.POST_TYPE = "POST";
	
	this.GET_TYP = "GET";
	
	this.dataType = "json";
	
	this.curOPObj = {};
	
	this.postAjax = function(u, d, callback){
		this.core(this.POST_TYPE, u, d, callback);
	};
	
	this.getAjax = function(u, d, callback){
		this.core(this.GET_TYPE, u, d, callback);
	};
	
	this.formAjax = function(formObj, callback){
		this.core(this.POST_TYPE, formObj.attr("action"), formObj.serialize(), callback);
	};
	
	this.core = function(t, u, d, callback, errcallback){
		var me = this;
		$.ajax({
			type : t,
			url : u,
			data : d,
			ansy : this.isAnsy,
			cache : this.isCache,
			dataType : this.dataType,
			error : function(msg){
				if(errcallback){
					errcallback.call(this, msg);
				} else {
					me._errorFunction(msg);
				}
			},
			success : function(msg){
				if(typeof(callback) != "function"){
					return;
				}
			   callback.call(this, msg, me.curOPObj);
			}
		});
	};
	
	this.setIsAnsy = function(ansy){
		isAnsy = ansy;
	};
	
	this.setIsCache = function(cache){
		isCache = cache;
	};
	
	this._errorFunction = function(msg){
		window.ZBSload.hide(); 
		//alert("服务器压力过大，请稍后再试！");
	};

	this._callbackFunction = function(msg){
		alert("请求成功！");
	};

};
var JSON = JSON || new Json();
if(typeof(JSON) == "undefined"){
	function Json(){
		this.stringify = function(json){
			var str = "";
			if(typeof(json) != "object"){
				return "\"" + json + "\"";
			}
			try{
				if(json.length){
					str += "[";
					for(var i = 0; i < json.length; i++){
						str += this.stringify(json[i]) + ",";
					}
					str = str.substring(0, str.length - 1);
					str += "]";
				} else {
					str += "{";
					for(var j in json){
						 str += "\"" + j + "\":" + this.stringify(json[j]) + ",";
					}
					str = str.substring(0, str.length - 1);
					str += "}";
				}
			} catch(err){
				return "";
			}
			return str;
		};
		
		this.parse = function(str){
			return eval("(" + str + ")");
		};
	}
};

String.prototype.trim = function()  
{  
    return this.replace(/(^\s*)|(\s*$)/g, "");  
}  

var ClickCheckObj = {};
var clickCheck = function(key, type){
	if(ClickCheckObj[key]){
		if(type) {
			ClickCheckObj[key] = false;
			return false;
		}
		return false;
	}
	ClickCheckObj[key] = true;
	return true;
};

String.prototype.startsWith = String.prototype.startsWith || function(str){
	return this.indexOf(str) == 0;
};
