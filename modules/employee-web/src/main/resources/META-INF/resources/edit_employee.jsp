<%@ include file="/init.jsp" %>
<%@ page import="com.esquare.hrms.model.Employee" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.esquare.hrms.service.*" %>


<%
    long employeeId = ParamUtil.getLong(request, "employeeId");
    Employee employee = employeeId > 0 ? EmployeeLocalServiceUtil.fetchEmployee(employeeId) : null;
    
    boolean isEdit = (employee != null);
    String actionName = isEdit ? "updateEmployee" : "saveEmployee";
    System.out.println(actionName);
%>


<portlet:renderURL var="cancelURL" >
	<portlet:param name="mvcPath" value="/view.jsp" />
</portlet:renderURL>

<portlet:actionURL var="saveURL" name="<%= actionName%>" />

<aui:form action="<%= saveURL %>" method="post" enctype="multipart/form-data">

    <aui:input type="hidden" name="employeeId" value="<%= employee != null ? employee.getEmployeeId() : "" %>"/>
    <aui:input name="name" label="Name" value="<%= employee != null ? employee.getName() : "" %>" required="true"/>
    <aui:input name="email" label="Email" value="<%= employee != null ? employee.getEmail() : "" %>" required="true"/>
    <aui:input name="designation" label="Designation" value="<%= employee != null ? employee.getDesignation() : "" %>" required="true"/>
    <aui:input name="contactNumber" label="Contact Number" value="<%= employee != null ? employee.getContactNumber() : "" %>" required="true"/>
    <aui:input name="address" label="Address" value="<%= employee != null ? employee.getAddress() : "" %>" required="true"/>
    <aui:input name="panNumber" label="PAN Number" value="<%= employee != null ? employee.getPanNumber() : "" %>" />
	<aui:input name="aadhaarNumber" label="Aadhaar Number" value="<%= employee != null ? employee.getAadhaarNumber() : "" %>" />
	<aui:input name="passportNumber" label="Passport Number" value="<%= employee != null ? employee.getPassportNumber() : "" %>" />

	<% if (isEdit) { %>
	    <aui:button type="button" value="Change Profile Photo" onClick="toggleFileField('photoFileField', this)" />
	    <div id="photoFileField" style="display: none;">
	        <aui:input name="photoFile" type="file" label="Upload Profile Photo" />
	    </div>
	
	    <aui:button type="button" value="Change PAN Image" onClick="toggleFileField('panFileField', this)" />
	    <div id="panFileField" style="display: none;">
	        <aui:input name="panImageFile" type="file" label="Upload PAN Image" />
	    </div>
	
	    <aui:button type="button" value="Change Aadhaar Image" onClick="toggleFileField('aadhaarFileField', this)" />
	    <div id="aadhaarFileField" style="display: none;">
	        <aui:input name="aadhaarImageFile" type="file" label="Upload Aadhaar Image" />
	    </div>
	<% } else { %>
	    <aui:input name="photoFile" type="file" label="Upload Profile Photo" />
	    <aui:input name="panImageFile" type="file" label="Upload PAN Image" />
	    <aui:input name="aadhaarImageFile" type="file" label="Upload Aadhaar Image" />
	<% } %>


    <aui:button type="submit" value="<%= isEdit ? "Update" : "Add" %>" />
    <aui:button type="submit" value="Cancel" onClick="if(confirm('Are you sure you want to Cancel?')) location.href='${cancelURL}'"/>
    
</aui:form>
<script>
    function toggleFileField(id, btn) {
        const field = document.getElementById(id);
        if (field.style.display === "none") {
            field.style.display = "block";
            btn.value = "Cancel";
        } else {
            field.style.display = "none";
            btn.value = btn.value.includes("Cancel") ? "Change" : btn.value.replace("Cancel", "Change");
        }
    }
</script>

