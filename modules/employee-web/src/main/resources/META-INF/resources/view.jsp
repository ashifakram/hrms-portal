<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@ include file="/init.jsp" %>
<%@ page import="com.esquare.hrms.model.Employee" %>
<%@ page import="com.esquare.hrms.service.EmployeeLocalServiceUtil" %>
<%@ page import="java.util.List" %>



<style>
/* Base Styling */

/* Add Button */
.aui-button {
    background-color: #006400;
    color: #fff;
    padding: 0.5rem 1.2rem;
    border-radius: 5px;
    font-weight: 600;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.aui-button:hover {
    background-color: #004d00;
}

/* Form Search */
form[name="fm"] {
    display: flex;
    flex-wrap: wrap;
    gap: 0.5rem;
    margin-bottom: 2rem;
    align-items: center;
}

form[name="fm"] .aui-field-input {
    width: 300px;
    padding: 0.5rem;
    font-size: 1rem;
}

/* Table Container */
.taglib-search-container {
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
    overflow-x: auto;
}

/* Table */
.taglib-search-container table {
    width: 100%;
    border-collapse: collapse;
    min-width: 600px;
}

.taglib-search-container th,
.taglib-search-container td {
    padding: 1rem;
    text-align: left;
    border-bottom: 1px solid #e1e4e8;
}

.taglib-search-container th {
    background-color: #f1f1f1;
    color: #006400;
    font-size: 1rem;
    text-transform: uppercase;
    letter-spacing: 0.5px;
}

.taglib-search-container td {
    color: #333;
    font-size: 0.95rem;
}

/* Actions */
.taglib-search-container td .aui-button {
    margin-right: 0.5rem;
    padding: 0.4rem 0.9rem;
    font-size: 0.85rem;
}

/* Heading */
h3 {
    text-align: center;
    font-size: 2rem;
    color: #006400;
    margin-bottom: 1.5rem;
}

/* Pagination */
.taglib-search-iterator-page-iterator {
    margin-top: 1rem;
    text-align: center;
}

.taglib-search-iterator-page-iterator a {
    padding: 0.4rem 0.75rem;
    margin: 0 0.25rem;
    background-color: #006400;
    color: #fff;
    border-radius: 4px;
    text-decoration: none;
    font-weight: 500;
}

.taglib-search-iterator-page-iterator a:hover {
    background-color: #004d00;
}

/* Responsive Styling */
@media (max-width: 768px) {
    form[name="fm"] {
        flex-direction: column;
        align-items: stretch;
    }

    form[name="fm"] .aui-field-input {
        width: 100%;
    }

    .taglib-search-container table {
        font-size: 0.9rem;
    }

    .taglib-search-container th,
    .taglib-search-container td {
        padding: 0.75rem;
    }

    .taglib-search-container td .aui-button {
        margin-bottom: 0.25rem;
        display: inline-block;
    }

    h3 {
        font-size: 1.5rem;
    }
}

@media (max-width: 480px) {
    .taglib-search-container table {
        min-width: unset;
    }

    form[name="fm"] .aui-field-input {
        font-size: 0.9rem;
    }

    .taglib-search-container th,
    .taglib-search-container td {
        font-size: 0.85rem;
    }

    h3 {
        font-size: 1.3rem;
    }
}
</style>





<portlet:renderURL var="addEmployeeURL">
    <portlet:param name="mvcPath" value="/edit_employee.jsp" />
</portlet:renderURL>

<aui:button value="+ Add Employee" onClick="location.href='${addEmployeeURL}'" /></br></br>


<%


  List<Employee> employees = null;
  if( request.getAttribute("employees") != null){
	  
	  employees = (List<Employee>) request.getAttribute("employees");  
  }
  else{
	  employees = EmployeeLocalServiceUtil.getEmployees(-1, -1);
  }
//List<Employee> employees = EmployeeLocalServiceUtil.getEmployees(-1, -1);

%>

<portlet:actionURL name="searchEmployee" var="searchURL" />
<aui:form name="fm" method="POST" action="<%= searchURL.toString()%>">
	<aui:input type="text" label="" name="employeeName" placeholder="Search By Employee Name Or Designation Or Address" />
	<aui:button type="submit" value="Search" />
</aui:form>


<h3>Employee Details</h3>

<liferay-ui:search-container
    total="<%= employees.size() %>"
    delta="4" deltaConfigurable="true"
    emptyResultsMessage="No Employees Found"
>

    <liferay-ui:search-container-results
        results="<%= ListUtil.subList(employees, searchContainer.getStart(), searchContainer.getEnd()) %>"
    />

    <liferay-ui:search-container-row
        className="com.esquare.hrms.model.Employee"
        modelVar="employee"
    >
        <liferay-ui:search-container-column-text property="name" />
        <liferay-ui:search-container-column-text property="email" />
        <liferay-ui:search-container-column-text property="designation" name="Designation"/>
        <liferay-ui:search-container-column-text property="address" name="Address" />
        <liferay-ui:search-container-column-text name="Onboarding Status">PENDING</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text name="Actions">
	        <portlet:renderURL var="editURL">
	            <portlet:param name="mvcPath" value="/edit_employee.jsp" />
	            <portlet:param name="employeeId" value="${employee.employeeId}" />
	        </portlet:renderURL>
	
	        <portlet:actionURL var="deleteURL" name="deleteEmployee">
	            <portlet:param name="employeeId" value="${employee.employeeId}" />
	        </portlet:actionURL>
	        <portlet:renderURL var="detailsURL">
    			<portlet:param name="mvcPath" value="/employee_details.jsp" />
    			<portlet:param name="employeeId" value="${employee.employeeId}" />
			</portlet:renderURL>

			<aui:button type="button" value="View Details" onClick="location.href='${detailsURL}'" />
	        <aui:button type="button" value="Edit" onClick="location.href='${editURL}'" />
	        <aui:button type="button" value="Delete" onClick="if(confirm('Are you sure you want to delete?')) location.href='${deleteURL}'" />
   		 </liferay-ui:search-container-column-text>

 <%--        <liferay-ui:search-container-column-jsp path="/actions.jsp" /> --%>
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>