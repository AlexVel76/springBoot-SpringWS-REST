<html>
<head>
    <title>Login Page</title>
</head>
<body>
<h3>Login Page</h3>

<form role="form" action="/login" method="post">
    Enter Login: <input type="text" name="login" required autofocus/><br />
    <br>Test user login - user1 (ROLE_REGISTERED_USER,ROLE_BOOKING_MANAGER)</br>
    <br>Test user login - user2 (ROLE_REGISTERED_USER)</br>
    <br /> Enter Password (all default users password is 'password'): <input type="password" name="app_password" required/> <br />
    <br />

    <div class="checkbox">
        <label><input type="checkbox" id="remember-me"
                      name="remember-me">Remember Me</label>
    </div>

    <input type="submit" value="Login" />

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

    <#if error.isPresent()>
        <p>The email or password you have entered is invalid, try again.</p>
    </#if>

</body>
</html>