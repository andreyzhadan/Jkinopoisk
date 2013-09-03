<%@ attribute name="movie" type="com.zhadan.bean.Movie" %>
<%@ tag import="com.zhadan.bean.Movie" %>
<div class="control-group">
    <label class="control-label" for="id">Id</label>

    <div class="controls">
        <input type="text" id="id" name="id" placeholder="" class="myInput"
               value="<%if (movie!=null) out.println(movie.getId());%>" disabled="true">
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="name">Name</label>

    <div class="controls">
        <input type="text" id="name" name="name" placeholder="" class="myInput"
               value="<%if (movie!=null) out.println(movie.getName());%>">
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="russianName">Russian Name</label>

    <div class="controls">
        <input type="text" id="russianName" name="russianName" placeholder="" class="myInput"
               value="<%if (movie!=null) out.println(movie.getRussianName());%>">
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="rating">Rating</label>

    <div class="controls">
        <input type="text" id="rating" name="rating" placeholder="" class="myInput"
               value="<%if (movie!=null) out.println(movie.getRating());%>">
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="slogan">Slogan</label>

    <div class="controls">
        <input type="text" id="slogan" name="slogan" placeholder="" class="myInput"
               value="<%if (movie!=null) out.println(movie.getSlogan());%>">
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="year">Year</label>

    <div class="controls">
        <input type="text" id="year" name="year" placeholder="" class="myInput"
               value="<%if (movie!=null) out.println(movie.getYear());%>">
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="country">Country</label>

    <div class="controls">
        <input type="text" id="country" name="country" placeholder="" class="myInput"
               value="<%if (movie!=null) out.println(movie.getCountry());%>">
    </div>
</div>