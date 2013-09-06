<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="control-group">
    <form:label cssClass="control-label" path="name">
        Name:
    </form:label>
    <div class="controls">
        <form:input path="name" class="myInput"/>
        <form:errors path="name" cssStyle="color: red;"/>
    </div>
</div>
<div class="control-group">
    <form:label cssClass="control-label" path="russianName">
        Russian Name:
    </form:label>
    <div class="controls">
        <form:input path="russianName" class="myInput"/>
        <form:errors path="russianName" cssStyle="color: red;"/>
    </div>
</div>
<div class="control-group">
    <form:label cssClass="control-label" path="rating">
        Rating:
    </form:label>
    <div class="controls">
        <form:input path="rating" class="myInput"/>
        <form:errors path="rating" cssStyle="color: red;"/>
    </div>
</div>
<div class="control-group">
    <form:label cssClass="control-label" path="slogan">
        Slogan:
    </form:label>
    <div class="controls">
        <form:input path="slogan" class="myInput"/>
        <form:errors path="slogan" cssStyle="color: red;"/>
    </div>
</div>
<div class="control-group">
    <form:label cssClass="control-label" path="year">
        Year:
    </form:label>
    <div class="controls">
        <form:input path="year" class="myInput"/>
        <form:errors path="year" cssStyle="color: red;"/>
    </div>
</div>
<div class="control-group">
    <form:label cssClass="control-label" path="country">
        Country:
    </form:label>
    <div class="controls">
        <form:input path="country" class="myInput"/>
        <form:errors path="country" cssStyle="color: red;"/>
    </div>
</div>
<div class="control-group">
    <form:label cssClass="control-label" path="picture">
        Picture url:
    </form:label>
    <div class="controls">
        <form:input path="picture" class="myInput"/>
        <form:errors path="picture" cssStyle="color: red;"/>
    </div>
</div>
<div class="control-group">
    <form:label cssClass="control-label" path="actors">
        List of actors:
    </form:label>
    <div class="controls">
        <form:select path="actors" class="myInput" cssStyle="height: 200px !important;">
            <form:options items="${availableActors}" itemLabel="fullName" itemValue="id"/>
        </form:select>
        <form:errors path="actors" cssStyle="color: red;"/>
    </div>
</div>
