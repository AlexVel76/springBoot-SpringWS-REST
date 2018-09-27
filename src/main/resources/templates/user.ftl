<!DOCTYPE html>

<html lang="en">

<body>
<h1>User info</h1>
<br> 	ID: ${user.id}
<br>	User Name: ${user.firstName} ${user.lastName}
<br>	Email: ${user.email}


<br><h3><a href="/ticket/add">Click here to Book tickets... </a></h3>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</body>
</html>