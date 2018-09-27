<#import "/spring.ftl" as spring/>

<html>

<body>
<h1>Error info</h1>

<br><br><h3><a href="/welcome">Back to Welcome page... </a></h3>

<br><br> 	<h3>Request:</h3> ${request}
<br><br> 	<h3>Message:</h3> ${exception}
<br> <br>	<h3>Message details:</h3> ${message}
<br> <br>	<h3>Stack trace:</h3> ${stacktrace}

<body>
</html>