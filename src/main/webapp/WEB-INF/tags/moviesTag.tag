<%@ tag import="com.zhadan.bean.Movie" %>
<%@ tag import="java.util.List" %>
<table class="table table-striped table-hover table-bordered">
    <thead>
    <td>Id</td>
    <td>Name</td>
    <td>Russian name</td>
    <td>Rating</td>
    <td>Slogan</td>
    <td>Year</td>
    <td>Country</td>
    </thead>
    <tbody>
    <%for (Movie movie : (List<Movie>) request.getAttribute("movies")) {%>
    <tr>
        <td>
            <a href="/movie?id=<%=movie.getId()%>">
                <%=movie.getId()%>
            </a>
        </td>
        <td><%=movie.getName()%>
        </td>
        <td>
            <%=movie.getRussianName()%>
        </td>
        <td>
            <%=movie.getRating()%>
        </td>
        <td>
            <%=movie.getSlogan()%>
        </td>
        <td>
            <%=movie.getYear()%>
        </td>
        <td>
            <%=movie.getCountry()%>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>