// Empty JS for your own code to be here

$(document).ready( function () {
    $('#etable').DataTable();
    
    $.get("api/equipment/search?limit=0", function( data ) {
    	 
    	console.log(data);
    	 
    	 var len = data.length;
         var txt = "";
         if(len > 0){
             for(var i=0;i<len;i++)
             {
            	 txt += "<tr><td>"+data[i]._id+"</td><td>"+data[i].eqipment_number+"</td><td>"+data[i].contract_start_date+"</td><td>"+data[i].contract_end_date+"</td><td>"+data[i].address+"</td><td>"+data[i].status+"</td></tr>";
             }
             if(txt != ""){
                 $("#etable").append(txt);
             }
         }
         
    	});
    
    
} );