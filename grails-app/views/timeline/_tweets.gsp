<g:each in="${tweets}" var="tweet">
    <div>
        <i>&nbsp; ${tweet.message}</i> <prettytime:display date="${tweet.time}"/>
    </div>
</g:each>
