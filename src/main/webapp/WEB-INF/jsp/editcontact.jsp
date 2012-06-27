<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <script language="javascript">
        function returnHome() {
            document.location.href = "mycontacts.html"
        }
    </script>
</head>

<body>
    <form:form method="post" commandName="editContact">
        <br>
        <br>
        <fieldset>
            <legend>Contact Info</legend>
            <table width="100%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
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
                    <td>Organization:</td>
                    <td><form:input path="org" maxlength="255"/></td>
                </tr>
                <tr>
                    <td>Title:</td>
                    <td><form:input path="title" maxlength="255"/></td>
                </tr>
                <tr>
                    <td>PhotoUrl:</td>
                    <td><form:input path="photoUrl" maxlength="255"/></td>
                </tr>
                <tr>
                    <td><label class="required"> * </label>Telephone(work):</td>
                    <td><form:input path="telWork" maxlength="255"/></td>
                    <td><form:errors path="telWork" cssClass="error"/></td>
                </tr>
                <tr>
                    <td>Telephone(Home):</td>
                    <td><form:input path="telHome" maxlength="255"/></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><form:input path="address" maxlength="255"/></td>
                </tr>
                <tr>
                    <td>E-mail:</td>
                    <td><form:input path="email"/></td>
                    <td><form:errors path="email" cssClass="error"/></td>
                </tr>
            </table>
        </fieldset>
        <br>
        <input type="submit" align="center" value="Save">
        <input type="button" align="right" value="Cancel" onClick="returnHome()">
    </form:form>
</body>

</html>