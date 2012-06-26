<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
</head>

<body>
    <form:form method="post" commandName="newUser">
        <br>
        <br>
        <fieldset>
            <legend>User Info</legend>
            <table width="100%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
                <tr>
                    <td><label class="required"> * </label>Login Name:</td>
                    <td><form:input path="loginName" maxlength="255"/></td>
                    <td><form:errors path="loginName" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><label class="required"> * </label>Password:</td>
                    <td><form:password path="password" maxlength="255"/></td>
                    <td><form:errors path="password" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><label class="required"> * </label>First Name:</td>
                    <td><form:input path="firstName" maxlength="255"/></td>
                    <td><form:errors path="firstName" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><label class="required"> * </label>Last Name:</td>
                    <td><form:input path="lastName" maxlength="255"/></td>
                    <td><form:errors path="lastName" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><label class="required"> * </label>E-mail:</td>
                    <td><form:input path="email"/></td>
                    <td><form:errors path="email" cssClass="error"/></td>
                </tr>
            </table>
        </fieldset>
        <br>
        <input type="submit" align="center" value="Save">
    </form:form>
</body>

</html>