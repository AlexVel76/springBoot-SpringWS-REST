<html>
<head>
    <title>Hometask</title>
    <style type="text/css">

    </style>
</head>
<body>
<br/>
<div style="text-align:center">
    <h2>
        Welcome on Tickets Booking<br/> <br/>
    </h2>
    <form action="/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit">Log out</button>
    </form>
    <br/>
    <h3><a href="/export/tickets/pdf">Get PDF with all Tickets </a></h3>
    <br/>
    <h3><a href="/export/users/xml">Get XML with all Users </a></h3>
    <br/>
    <h3><a href="/export/events/xml">Get XML with all Events </a></h3>
    <br/>
    <h3><a href="/fileUpload">Upload files </a></h3>
    <br></br>
    <h3><a href="/user/users">Users </a></h3>
    <br/>
    <h3><a href="/ticket/add">Add ticket </a></h3>
    <br/>
    <h3><a href="/ticket/tickets">Booked tickets </a></h3>
    <br/>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

</div>
</body>
</html>