<%@ page import="com.worldmusic.WorldMusicSpring.model.Clip" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: volodymyr
  Date: 12.03.18
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/templates/partials/_header.jsp" %>
<body>
<%@ include file="/templates/partials/_nav.jsp" %>
<div class="container">
    <h1 class="text-center">Clips</h1>
    <div class="row addButton">
        <div class="col-md-10"></div>
        <div class="col-md-2">
            <a href='clips/create' class="btn btn-primary addButton">Add Clip</a>
        </div>
    </div>
    <%ArrayList clips = (ArrayList)request.getAttribute("clips");%>
    <div class="row">
        <div class="col-md-12">
            <table class="table">
                <thead>
                <th>#</th>
                <th>Name</th>
                <th>Url</th>
                <th>Artist</th>
                <th>Style</th>

                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
</div>
<%@ include file="/templates/partials/_javascript.jsp" %>
</body>
</html>

</html>
