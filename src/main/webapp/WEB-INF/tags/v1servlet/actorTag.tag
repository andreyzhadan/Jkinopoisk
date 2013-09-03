<%@ attribute name="actor" type="com.zhadan.bean.Actor" %>
<%@ tag import="com.zhadan.bean.Actor" %>
<div class="control-group">
    <label class="control-label" for="id">Id</label>

    <div class="controls">
        <input type="text" id="id" name="id" placeholder="" class="myInput"
               value="<%if (actor!=null) out.println(actor.getId());%>" disabled="true">
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="firstName">First Name</label>

    <div class="controls">
        <input type="text" id="firstName" name="firstName" placeholder="" class="myInput"
               value="<%if (actor!=null) out.println(actor.getFirstName());%>">
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="lastName">Last Name</label>

    <div class="controls">
        <input type="text" id="lastName" name="lastName" placeholder="" class="myInput"
               value="<%if (actor!=null) out.println(actor.getLastName());%>">
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="birthday">Birthday</label>

    <div class="controls">
        <input type="text" id="birthday" name="birthday" placeholder="" class="myInput"
               value="<%if (actor!=null) out.println(actor.getBirthday());%>">
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="country">Country</label>

    <div class="controls">
        <input type="text" id="country" name="country" placeholder="" class="myInput"
               value="<%if (actor!=null) out.println(actor.getCountry());%>">
    </div>
</div>