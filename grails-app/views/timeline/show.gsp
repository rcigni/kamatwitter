<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Timeline</title>
</head>
<body>

    <h1>Timeline</h1>

    <g:each in="${tweets}" var="tweet">
        <div>
            <i>&nbsp; ${tweet.message}</i> <prettytime:display date="${tweet.time}"/>
        </div>
    </g:each>

</body>
</html>
