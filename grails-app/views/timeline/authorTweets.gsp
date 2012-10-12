<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Timeline</title>
</head>
<body>

<h1>Timeline of Mr ${author}</h1>

<g:form action="tweet">
    <div class="fieldcontain ${hasErrors(bean: tweetInstance, field: 'message', 'error')} ">
        <label for="message">
            <g:message code="tweet.message.label" default="Message" />
        </label>
        <g:textField name="message" value="${tweetInstance?.message}"/>
    </div>

</g:form>

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>

<g:render template="tweets" model="[tweets: tweets]"/>

</body>
</html>
