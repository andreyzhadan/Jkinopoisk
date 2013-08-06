<%@ tag import="com.zhadan.bean.Actor" %>
<%@ tag import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: azhadan
  Date: 8/5/13
  Time: 5:37 PM
--%>
<table class="table table-striped table-hover">
    <thead>
    <td>Id</td>
    <td>First name</td>
    <td>Last name</td>
    <td>Date</td>
    <td>Country</td>
    </thead>
    <tbody>
    <%for (Actor actor : (List<Actor>) request.getAttribute("actors")) {%>
    <tr>
        <td>
            <a href="/actor?id=<%=actor.getId()%>">
                <%=actor.getId()%>
            </a>
        </td>
        <td>
            <%=actor.getFirstName()%>
        </td>
        <td>
            <%=actor.getLastName()%>
        </td>
        <td>
            <%=actor.getBirthday()%>
        </td>
        <td>
            <%=actor.getCountry()%>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>