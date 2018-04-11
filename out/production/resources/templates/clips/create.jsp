<%@ page import="java.util.ArrayList" %>
<%@ page import="domain.Artist" %>
<%@ page import="domain.Style" %><%--
  Created by IntelliJ IDEA.
  User: volodymyr
  Date: 12.03.18
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/partials/_header.jsp" %>
<%ArrayList artists = (ArrayList)request.getAttribute("artists");%>
<%ArrayList styles = (ArrayList)request.getAttribute("styles");%>
<body>
<%@ include file="/partials/_nav.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h1>Create Clip працює?</h1>
            <hr>
            <form action="create" method="post">
                <div class="form-group">
                    <label name="name">Clip Name:</label>
                    <input id="name" name="name" class="form-control" required="" maxlength="255">
                </div>
                <div class="form-group">
                    <label name="url">Clip Url:</label>
                    <input id="url" name="url" class="form-control" required="" maxlength="255">
                </div>
                <div class="form-group">
                    <label name="artist_id">Artist:</label>
                    <select class="form-control" name="artist_id">
                        <% for(int i = 0; i < artists.size();i++) {
                            Artist artist = (Artist) artists.get(i);
                            out.println("<option value="+artist.getId() +">"+
                                    artist.getName()+"</option>");
                        } %>
                    </select>
                </div>
                <div class="form-group">
                    <label name="style_id">Style:</label>
                    <select class="form-control" name="style_id">
                        <% for(int i = 0; i < styles.size();i++) {
                            Style style = (Style) styles.get(i);
                            out.println("<option value="+style.getId() +">"+
                                    style.getName()+"</option>");
                        } %>
                    </select>
                </div>
                <input class="btn btn-primary" type=submit value="create">
            </form>
        </div>
    </div>
</div>
    <script src= "parsley.min.js"></script>
</body>
</html>
