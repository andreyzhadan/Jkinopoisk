<%@ attribute name="actor" type="com.zhadan.bean.Actor" %>
<%@ tag import="com.zhadan.bean.Actor" %>
<ul class="list-group">
    <li class="list-group-item">
        Id <%=actor.getId()%>
    </li>
    <li class="list-group-item">
        First name <%=actor.getFirstName()%>
    </li>
    <li class="list-group-item">
        Last name <%=actor.getLastName()%>
    </li>
    <li class="list-group-item">
        Year <%=actor.getDate()%>
    </li>
    <li class="list-group-item">
        Country <%=actor.getCountry()%>
    </li>
</ul>