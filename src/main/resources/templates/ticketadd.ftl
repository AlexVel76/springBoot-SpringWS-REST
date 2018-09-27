<!DOCTYPE html>

<html lang="en">

<body>
<h1>Create ticket </h1>
<br/>

<form action = "/ticket/add" method = "post">
    EventID (3): <input type="text" name="eventId" />	<br/>
    UserId (5, 6, 7): <input type="text" name="userId" />	<br/>
    Seats (34,33): <input type="text" name="seats" />	<br/>
    Date Time (2020/06/15 19:30): <input type="text" name="dateTime" />	<br/>
    <input type="submit" value="   Save   " />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>


<br/>
<h3><a href="/ticket/tickets">Booked tickets (only for Manager role) </a></h3>
<br/>

</body>
</html>