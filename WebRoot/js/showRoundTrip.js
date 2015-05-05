/**
 * 
 */
 $(function() {
    $( "#dtime" ).slider({
      range: true,
      min: 0,
      max:24,
      values: [0, 24 ],
      slide: function( event, ui ) {
        $( "#dfrom" ).val(ui.values[ 0 ] );
        $( "#dto" ).val(ui.values[ 1 ] );
      }
    });
    $( "#dfrom" ).val( $( "#dtime" ).slider( "values", 0 ) );
    $( "#dto" ).val(  $( "#dtime" ).slider( "values", 1 ) );
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
 
 
 $(function() {
	    $( "#dbtime" ).slider({
	      range: true,
	      min: 0,
	      max:24,
	      values: [0, 24 ],
	      slide: function( event, ui ) {
	        $( "#dbfrom" ).val(ui.values[ 0 ] );
	        $( "#dbto" ).val(ui.values[ 1 ] );
	      }
	    });
	    $( "#dbfrom" ).val( $( "#dbtime" ).slider( "values", 0 ) );
	    $( "#dbto" ).val(  $( "#dbtime" ).slider( "values", 1 ) );
	  });
	 
	 $(function() {
		    $( "#abtime" ).slider({
		      range: true,
		      min: 0,
		      max:24,
		      values: [0, 24 ],
		      slide: function( event, ui ) {
		        $( "#abfrom" ).val(ui.values[ 0 ] );
		        $( "#abto" ).val(ui.values[ 1 ] );
		      }
		    });
		    $( "#abfrom" ).val( $( "#abtime" ).slider( "values", 0 ) );
		    $( "#abto" ).val(  $( "#abtime" ).slider( "values", 1 ) );
		  });
	 
	 function seatVali( indexPlan){

		 if(Number(document.fnumber[indexPlan].minseat.value)<Number(document.fnumber[indexPlan].numbers.value)){
			     alert('Fail to buy! Available tickets insufficient!');
			     return false;
		 
			}
			   
			     return true;
		}
