<%@ page import="com.liferay.portal.kernel.portlet.LiferayPortletURL" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="portlet" %>
<%@ include file="/init.jsp" %>

<portlet:renderURL var="editURL">
    <portlet:param name="mvcPath" value="/edit_employee.jsp" />
    <portlet:param name="employeeId" value="${employee.employeeId}" />
</portlet:renderURL>

<portlet:actionURL var="deleteURL" name="deleteEmployee">
    <portlet:param name="employeeId" value="${employee.employeeId}" />
</portlet:actionURL>

<aui:button-row>
    <aui:button type="submit" value="Edit" onClick="location.href='${editURL}'" />
    <aui:button type="submit" value="Delete" onClick="if (confirm('Are you sure?')) location.href='${deleteURL}'" />
</aui:button-row>
