<%@ include file="/init.jsp" %>
<%@ page import="com.esquare.hrms.model.Employee" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.esquare.hrms.service.EmployeeLocalServiceUtil" %>

<%
    long employeeId = ParamUtil.getLong(request, "employeeId");
    Employee employee = EmployeeLocalServiceUtil.fetchEmployee(employeeId);
%>

<style>
.profile-card {
    max-width: 600px;
    margin: 2rem auto;
    background: #fff;
    border-radius: 12px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    padding: 2rem;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.profile-image {
    width: 120px;
    height: 120px;
    border-radius: 50%;
    background-color: #e0e0e0;
    margin-bottom: 1.5rem;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 2rem;
    color: #555;
}

.profile-details {
    text-align: center;
}

.profile-details h2 {
    color: #006400;
    margin-bottom: 0.5rem;
}

.profile-details p {
    margin: 0.3rem 0;
    font-size: 1rem;
    color: #444;
}
</style>

<% if (employee != null) { %>
<div class="profile-card">
    <div class="profile-image">
        <% if (employee.getPhoto() != null && !employee.getPhoto().isEmpty()) { %>
        	<img src="<%= employee.getPhoto() %>" alt="Employee Photo" style="width: 100%; height: 100%; border-radius: 50%; object-fit: cover;" />
    	<% } else { %>
        	<span>👤</span>
    	<% } %>
    </div>
    <div class="profile-details">
        <h2><%= employee.getName() %></h2>
        <p><strong>Employee Id : </strong><%= employee.getEmployeeId() %></p>
        <p><strong>Email : </strong> <%= employee.getEmail() %></p>
        <p><strong>Designation : </strong> <%= employee.getDesignation() %></p>
        <p><strong>Contact : </strong> <%= employee.getContactNumber() %></p>
        <p><strong>Address : </strong> <%= employee.getAddress() %></p>
        <p><strong>Created Date : </strong> <%= employee.getCreateDate() %></p>
        
        <portlet:renderURL var="viewURL">
    		<portlet:param name="mvcPath" value="/view.jsp" />
		</portlet:renderURL>

		<aui:button type="button" value="Go Back" onClick="location.href='${viewURL}'" />    
    </div>
</div>
<% } else { %>
    <p>Employee not found.</p>
<% } %>
