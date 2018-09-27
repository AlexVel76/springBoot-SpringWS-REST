<!DOCTYPE html>

<html lang="en">

<body>
<h1>Ticket info</h1>
<br> 	ID: ${ticket.id}
<br> 	Event: ${ticket.event.name}
<br>	Date: ${ticket.dateTime}
<br>	User Name: ${ticket.user.firstName} ${ticket.user.lastName}	
<br>	Seat: ${ticket.seat}


<br><h3><a href="/ticket/add">Click here to Book tickets... </a></h3>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

</body>
</html>