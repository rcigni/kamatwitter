<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Timeline</title>
</head>
<body>

<h1>Timeline of Mr ${author}</h1>

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>

<g:render template="tweets" model="[tweets: tweets]"/>

</body>
</html>
