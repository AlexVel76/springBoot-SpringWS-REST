<#import "/spring.ftl" as spring/>

<html>

<body>
<h3>Users:</h3>
<table class="datatable" border="+1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email/Lastname</th>
    </tr>
    <#list users as user>
    <tr>
        <td>${user.id}</td>
        <td>${user.lastName}</td>
        <td>${user.email}</td>
    </tr>
    </#list>
</table>


<br><h3><a href="/ticket/add">Click here to Book tickets... </a></h3>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</body>
</html>