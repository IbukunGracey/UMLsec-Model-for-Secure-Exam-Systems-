$(document).ready(function() {
     /**
     * Hide the error div if empty
    **/
	var errTagText = $("div.alert p").text();
	if ( errTagText.length < 4 ) {
		$("div.alert").hide();
	}
});