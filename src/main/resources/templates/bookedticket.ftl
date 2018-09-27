<!DOCTYPE html>
<html lang="en">
<body>

<h1>Booked Tickets </h1>

<!--<table class="datatable" border="+1">-->
  	<!--<tr>-->
  		<!--<th>ID</th>-->
  		<!--<th>Seat</th>-->
  		<!--<th>User Id/Lastname</th>-->
  		<!--<th>Event Id/Name</th>-->
  		<!--<th>Created Date</th>-->
  	<!--</tr>-->
    <!--<#list tickets as ticket>-->
		<!--<tr>-->
			<!--<td>${ticket.id}</td>-->
			<!--<td>${ticket.seat}</td>-->
			<!--<td>${ticket.user.id}/${ticket.user.lastName}</td>-->
			<!--<td>${ticket.event.id}/${ticket.event.name}</td>-->
			<!--<td>${ticket.dateTime}</td>-->
		<!--</tr>-->
    <!--</#list>-->
  <!--</table>-->

<table class="datatable" border="+1">
	<tr>
		<th>ID</th>
		<th>Seat</th>
		<th>User Id/Lastname</th>
		<th>Event Id/name</th>
		<th>DateTime</th>
		<th></th>
		<th></th>
	</tr>
	<#list tickets as ticket>
	<tr>
		<td>${ticket.id}</td>
		<td>${ticket.seat}</td>
		<td>${ticket.user.id}/${ticket.user.lastName}</td>
		<td>${ticket.event.id}/${ticket.event.name} <a
				href="../ticket/bookedticket?eventId=${ticket.event.id}&dateTime=${ticket.dateTime}">Get Bookied Tikets
			for the Event</a></td>
		<td>${ticket.dateTime}</td>
		<td><b>
			<a href="../ticket?ticketId=${ticket.id}">Ticket Details </a></b>
		</td>
		<td><b>
			<a href="../ticket/price?seat=${ticket.seat}&eventId=${ticket.event.id}&userId=${ticket.user.id}&dateTime=${ticket.dateTime}">View
				Price... </a></b>
		</td>
	</tr>
	</#list>
    </table>

<a href="/ticket/add"> <h3>Click here to Book tickets..</h3></a>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

</body>
</html>