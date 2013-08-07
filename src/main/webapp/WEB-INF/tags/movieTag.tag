<%@ attribute name="movie" type="com.zhadan.bean.Movie" %>
<%@ tag import="com.zhadan.bean.Movie" %>
<ul class="list-group">
    <li class="list-group-item">
        Id <%=movie.getId()%>
    </li>
    <li class="list-group-item">
        Name <%=movie.getName()%>
    </li>
    <li class="list-group-item">
        Russian name <%=movie.getRussianName()%>
    </li>
    <li class="list-group-item">
        Rating <%=movie.getRating()%>
    </li>
    <li class="list-group-item">
        Slogan <%=movie.getSlogan()%>
    </li>
    <li class="list-group-item">
        Year <%=movie.getYear()%>
    </li>
    <li class="list-group-item">
        Country <%=movie.getCountry()%>
    </li>
</ul>