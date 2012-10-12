<%@ page import="kamatwitter.Tweet" %>



<div class="fieldcontain ${hasErrors(bean: tweetInstance, field: 'message', 'error')} ">
	<label for="message">
		<g:message code="tweet.message.label" default="Message" />
		
	</label>
	<g:textField name="message" value="${tweetInstance?.message}"/>
</div>

