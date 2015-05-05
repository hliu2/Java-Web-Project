/**
 * 
 */
 $(function() {
    $( "#time" ).slider({
      range: true,
      min: 0,
      max:24,
      values: [0, 24 ],
      slide: function( event, ui ) {
        $( "#from" ).val(ui.values[ 0 ] );
        $( "#to" ).val(ui.values[ 1 ] );
      }
    });
    $( "#from" ).val( $( "#time" ).slider( "values", 0 ) );
    $( "#to" ).val(  $( "#time" ).slider( "values", 1 ) );
  });
 
 $(function() {
	    $( "#atime" ).slider({
	      range: true,
	      min: 0,
	      max:24,
	      values: [0, 24 ],
	      slide: function( event, ui ) {
	        $( "#afrom" ).val(ui.values[ 0 ] );
	        $( "#ato" ).val(ui.values[ 1 ] );
	      }
	    });
	    $( "#afrom" ).val( $( "#atime" ).slider( "values", 0 ) );
	    $( "#ato" ).val(  $( "#atime" ).slider( "values", 1 ) );
	  });
 

 function seatVali( indexPlan){
 if(Number(document.fnumber[indexPlan].minseat.value)<Number(document.fnumber[indexPlan].numbers.value)){
	     alert('Fail to buy! Available tickets insufficient!');
	     return false;
 
	}
	   
	     return true;
}
