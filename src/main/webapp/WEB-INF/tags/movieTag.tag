<%@ tag import="java.util.Date" %>
<%@ tag import="java.text.DateFormat" %>
<%
    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
    Date now = new Date(System.currentTimeMillis());
    System.out.println(dateFormat.format(now));
%>