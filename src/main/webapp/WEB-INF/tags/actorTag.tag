<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="control-group">
    <form:label cssClass="control-label" path="firstName">
        First Name:
    </form:label>
    <div class="controls">
        <form:input path="firstName" class="myInput"/>
        <form:errors path="firstName" cssStyle="color: red;"/>
    </div>
</div>
<div class="control-group">
    <form:label cssClass="control-label" path="lastName">
        Last Name:
    </form:label>
    <div class="controls">
        <form:input path="lastName" class="myInput"/>
        <form:errors path="lastName" cssStyle="color: red;"/>
    </div>
</div>
<div class="control-group">
    <form:label cssClass="control-label" path="birthday">
        Birthday:
    </form:label>
    <div class="controls">
        <form:input path="birthday" class="myInput"/>
        <form:errors path="birthday" cssStyle="color: red;"/>
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