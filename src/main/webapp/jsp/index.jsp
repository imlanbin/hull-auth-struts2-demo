<%@ page import="io.hull.HullUser" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<% HullUser user = (HullUser) session.getAttribute( "HULL_USER" ); %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Hullo World</title>
    <script src="//code.jquery.com/jquery-1.8.3.js"></script>
    <script src="//s3.amazonaws.com/hull-js/0.8/hull.js"></script>
    <script>
        Hull.init({ appId: "CHANGE-ME", orgUrl: "https://CHANGE-ME.hullapp.io" });
        Hull.on('hull.auth.login', function() { document.location.reload() });
        Hull.on('hull.auth.logout', function() { document.location.reload() });
    </script>
</head>
<body>

<% if (user != null) { %>
<h1>Hello <%= user.get("name") %> [<%= user.getId() %>] </h1>
<p>Fetched at <%= session.getAttribute("HULL_USER_FETCHED_AT") %></p>
<% } %>

<div data-hull-component="login/profile@hull"></div>
</body>
</html>
