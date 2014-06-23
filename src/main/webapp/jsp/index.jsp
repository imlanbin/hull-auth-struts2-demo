<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Hullo World</title>
    <script src="//code.jquery.com/jquery-1.8.3.js"></script>
    <script src="//s3.amazonaws.com/hull-js/0.8/hull.js"></script>
    <script>
        Hull.init({ appId: "CHANGE_ME_APP_ID_HERE", orgUrl: "https://CHANGE_ME_ORG_NAMESPACE_HERE.hullapp.io" });
        Hull.on('hull.auth.login', function() { document.location.reload() });
        Hull.on('hull.auth.logout', function() { document.location.reload() });
    </script>
</head>
<body>
<h1>Hello : <%= session.getAttribute( "HULL_USER_ID" ) %></h1>
<div data-hull-component="login/profile@hull"></div>
</body>
</html>
