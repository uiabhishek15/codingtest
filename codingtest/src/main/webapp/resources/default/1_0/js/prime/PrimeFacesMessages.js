function PrimeFacesMessages(){};

PrimeFacesMessages.prototype.renderInfoMessage = function(messages){
	renderMsgs(messages, 'info');
};

PrimeFacesMessages.prototype.renderErrorMessage = function(messages){
	renderMsgs(messages, 'error');
};

PrimeFacesMessages.prototype.clearMessages = function(){
	$('#errorFacesMessages').html('');
	$('#infoFacesMessages').html('');
};

function renderMsgs(messages, severity){
	if(messages){		
		var waitTime = 100;
		setTimeout(function() { 
			buildMessages(messages, severity);
		}, waitTime);
	}
};

function clearFacesMessages(){
	$('#errorFacesMessages').html('');
	$('#infoFacesMessages').html('');
};

function buildMessages(messages, sev){

	var div = $(document.createElement('div'));
	div = addStyleClasses(div, ['ui-messages-' + sev, 'ui-corner-all']);
	

	var summary = $(document.createElement('span'));
	var detail = $(document.createElement('span'));
		
	summary = addStyleClasses(summary, ['ui-messages-'+ sev + '-summary']);
	detail = addStyleClasses(detail, ['ui-messages-'+ sev + '-detail']);
		
	summary.html(!messages.summary ? '' : messages.summary);
	detail.html(!messages.detail ? '' : messages.detail);

	var li = $(document.createElement('li'));
	li.append(summary);
	li.append(detail);
	
	var ul = $(document.createElement('ul'));
	ul.append(li);
	
	var iconSpan = $(document.createElement('span'));
	iconSpan = this.addStyleClasses(iconSpan, ['ui-messages-'+ sev + '-icon']);
	
	div.append(iconSpan);
	div.append(ul);
	
	$('#' + sev + 'FacesMessages').append(div);
};

function addStyleClasses(object, styleClasses){
	for(var key in styleClasses){
		object.addClass(styleClasses[key]);
	}
	return object;
};
