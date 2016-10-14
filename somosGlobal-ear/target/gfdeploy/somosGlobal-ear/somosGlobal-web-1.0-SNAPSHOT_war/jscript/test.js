/**
 * 
 */
	var directionsDisplay = new google.maps.DirectionsRenderer();
	var directionsService = new google.maps.DirectionsService();

	
	var pMap = new google.maps.Map(htmlElement, myOptions); directionsDisplay.setMap(map);
	//var pMap = wmap.getMap();
	//directionsDisplay.setMap(pMap);

	var end = "21.030300, 105.78600";
	var start = "21.030350, 105.78654";

	var request = {

	         origin:start,

	         destination:end,

	         travelMode: google.maps.DirectionsTravelMode.DRIVING

	      };


	      directionsService.route(request, function(response, status) {
	         if (status == google.maps.DirectionsStatus.OK) {
	            directionsDisplay.setDirections(response);
	         } else {
	            if (status == 'ZERO_RESULTS') {

	               alert('No route could be found between the origin and destination.');

	            } else if (status == 'UNKNOWN_ERROR') {

	               alert('A directions request could not be processed due to a server error. The request may succeed if you try again.');

	            } else if (status == 'REQUEST_DENIED') {

	               alert('This webpage is not allowed to use the directions service.');

	            } else if (status == 'OVER_QUERY_LIMIT') {

	               alert('The webpage has gone over the requests limit in too short a period of time.');

	            } else if (status == 'NOT_FOUND') {

	               alert('At least one of the origin, destination, or waypoints could not be geocoded.');

	            } else if (status == 'INVALID_REQUEST') {

	               alert('The DirectionsRequest provided was invalid.');

	            } else {

	               alert("There was an unknown error in your request. Requeststatus: \n\n"+status);

	            }

	         }

	      });