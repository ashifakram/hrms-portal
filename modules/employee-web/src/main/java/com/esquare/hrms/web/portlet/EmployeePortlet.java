package com.esquare.hrms.web.portlet;

import com.esquare.hrms.model.Employee;
import com.esquare.hrms.service.EmployeeLocalServiceUtil;
import com.esquare.hrms.web.constants.EmployeePortletKeys;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;
import org.osgi.service.component.annotations.Component;

/**
 * @author CSC
 */
@Component(property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Employee", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + EmployeePortletKeys.EMPLOYEE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class EmployeePortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub

		List<Employee> employees = EmployeeLocalServiceUtil.getEmployees(-1, -1);
		renderRequest.setAttribute("employees", employees);

		super.doView(renderRequest, renderResponse);
	}

	@ProcessAction(name = "saveEmployee")
	public void saveEmployee(ActionRequest request, ActionResponse response)
			throws Exception {

		long employeeId = CounterLocalServiceUtil.increment(Employee.class.getName());

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		String name = ParamUtil.getString(request, "name");
		String email = ParamUtil.getString(request, "email");
		String designation = ParamUtil.getString(request, "designation");
		String contact = ParamUtil.getString(request, "contactNumber");
		String address = ParamUtil.getString(request, "address");
		String panNumber = ParamUtil.getString(request, "panNumber");
		String aadhaarNumber = ParamUtil.getString(request, "aadhaarNumber");
		String passportNumber = ParamUtil.getString(request, "passportNumber");

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);

		InputStream photoStream = uploadRequest.getFileAsStream("photoFile");
		InputStream panImageStream = uploadRequest.getFileAsStream("panImageFile");
		InputStream aadhaarImageStream = uploadRequest.getFileAsStream("aadhaarImageFile");

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long userId = themeDisplay.getUserId();
		long companyId = themeDisplay.getCompanyId();
		long groupId = themeDisplay.getScopeGroupId();
		String userName = UserLocalServiceUtil.getUser(userId).getFullName();

		Employee emp = EmployeeLocalServiceUtil.createEmployee(employeeId);
		emp.setName(name);
		emp.setEmail(email);
		emp.setAddress(address);
		emp.setDesignation(designation);
		emp.setContactNumber(contact);
		emp.setCompanyId(companyId);
		emp.setGroupId(groupId);
		emp.setUserId(userId);
		emp.setUserName(userName);
		emp.setCreateDate(new Date());
		emp.setModifiedDate(new Date());
		emp.setPanNumber(panNumber);
		emp.setAadhaarNumber(aadhaarNumber);
		emp.setPassportNumber(passportNumber);

		Folder hrmsRootFolder = createFolderIfNotExists(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "HRMS",
				serviceContext);

		Folder hrmsSpecificFolder = createFolderIfNotExists(groupId, hrmsRootFolder.getFolderId(),
				String.valueOf(employeeId), serviceContext);
		
		if (photoStream != null && uploadRequest.getSize("photoFile") > 0) {
			byte[] photoBytes = IOUtils.toByteArray(photoStream);
			String photoBase64 = Base64.getEncoder().encodeToString(photoBytes);
			
			String url = uploadImage(groupId, hrmsSpecificFolder.getFolderId(), photoBase64, "image1_" + employeeId +".jpg", serviceContext);
			
			emp.setPhoto(url);
		}
		else {
			emp.setPhoto(null);
		}

		if (panImageStream != null && uploadRequest.getSize("panImageFile") > 0) {
			byte[] panImageBytes = IOUtils.toByteArray(panImageStream);
			String panBase64 = Base64.getEncoder().encodeToString(panImageBytes);
			
			String url = uploadImage(groupId, hrmsSpecificFolder.getFolderId(), panBase64, "panimage1_" + employeeId +".jpg", serviceContext);
			
			emp.setPanImage(url);
		}
		else {
			emp.setPanImage(null);
		}

		if (aadhaarImageStream != null && uploadRequest.getSize("aadhaarImageFile") > 0) {
			byte[] aadhaarImageBytes = IOUtils.toByteArray(aadhaarImageStream);
			String aadhaarBase64 = Base64.getEncoder().encodeToString(aadhaarImageBytes);
			
			String url = uploadImage(groupId, hrmsSpecificFolder.getFolderId(), aadhaarBase64, "aadhaar1_" + employeeId +".jpg", serviceContext);
			
			emp.setAadhaarImage(url);
		}
		else {
			emp.setAadhaarImage(null);
		}


		EmployeeLocalServiceUtil.addEmployee(emp);
		System.out.println("Employee saved successfully");

	}

	@ProcessAction(name = "updateEmployee")
	public void updateEmployee(ActionRequest request, ActionResponse response) throws Exception {

		long employeeId = ParamUtil.getLong(request, "employeeId");
		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		String name = ParamUtil.getString(request, "name");
		String email = ParamUtil.getString(request, "email");
		String designation = ParamUtil.getString(request, "designation");
		String contact = ParamUtil.getString(request, "contactNumber");
		String address = ParamUtil.getString(request, "address");
		String panNumber = ParamUtil.getString(request, "panNumber");
		String aadhaarNumber = ParamUtil.getString(request, "aadhaarNumber");
		String passportNumber = ParamUtil.getString(request, "passportNumber");
		
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
		InputStream photoStream = uploadRequest.getFileAsStream("photoFile");
		InputStream panImageStream = uploadRequest.getFileAsStream("panImageFile");
		InputStream aadhaarImageStream = uploadRequest.getFileAsStream("aadhaarImageFile");

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long userId = themeDisplay.getUserId();
		String userName = UserLocalServiceUtil.getUser(userId).getFullName();

		Employee emp = EmployeeLocalServiceUtil.fetchEmployee(employeeId);

		if (emp != null) {

			emp.setName(name);
			emp.setEmail(email);
			emp.setDesignation(designation);
			emp.setContactNumber(contact);
			emp.setAddress(address);
			emp.setUserId(userId);
			emp.setUserName(userName);
			emp.setModifiedDate(new Date());
			emp.setPanNumber(panNumber);
			emp.setAadhaarNumber(aadhaarNumber);
			emp.setPassportNumber(passportNumber);
			
			Folder hrmsRootFolder = createFolderIfNotExists(emp.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "HRMS",
					serviceContext);

			Folder hrmsSpecificFolder = createFolderIfNotExists(emp.getGroupId(), hrmsRootFolder.getFolderId(),
					String.valueOf(employeeId), serviceContext);
			
			String photoFileName = uploadRequest.getFileName("photoFile");
			
			if (photoStream != null && !photoFileName.isEmpty()) {
				byte[] photoBytes = IOUtils.toByteArray(photoStream);
				String photoBase64 = Base64.getEncoder().encodeToString(photoBytes);
				
				String url = uploadImage(emp.getGroupId(), hrmsSpecificFolder.getFolderId(), photoBase64, "image1_" + employeeId + "_" + System.currentTimeMillis() + ".jpg", serviceContext);
				
				emp.setPhoto(url);
			}
			
			String panImageName = uploadRequest.getFileName("panImageFile");

			if (panImageStream != null && !panImageName.isEmpty()) {
				byte[] panImageBytes = IOUtils.toByteArray(panImageStream);
				String panBase64 = Base64.getEncoder().encodeToString(panImageBytes);
				
				String url = uploadImage(emp.getGroupId(), hrmsSpecificFolder.getFolderId(), panBase64, "panimage1_" + employeeId + "_" + System.currentTimeMillis() + ".jpg", serviceContext);
				
				emp.setPanImage(url);
			}
			

			String aadhaarImageName = uploadRequest.getFileName("aadhaarImageFile");
			
			if (aadhaarImageStream != null && !aadhaarImageName.isEmpty()) {
				byte[] aadhaarImageBytes = IOUtils.toByteArray(aadhaarImageStream);
				String aadhaarBase64 = Base64.getEncoder().encodeToString(aadhaarImageBytes);
				
				String url = uploadImage(emp.getGroupId(), hrmsSpecificFolder.getFolderId(), aadhaarBase64, "aadhaar1_" + employeeId + "_" + System.currentTimeMillis() + ".jpg", serviceContext);
				
				emp.setAadhaarImage(url);
			}
			

			EmployeeLocalServiceUtil.updateEmployee(emp);
			System.out.println("Employee update successfully");
		}

		response.setRenderParameter("mvcPath", "/view.jsp");
	}

	@ProcessAction(name = "deleteEmployee")
	public void deleteEmployee(ActionRequest request, ActionResponse response) throws Exception {
		long employeeId = ParamUtil.getLong(request, "employeeId");

		EmployeeLocalServiceUtil.deleteEmployee(employeeId);

		response.setRenderParameter("mvcPath", "/view.jsp");
	}

	
//	this method is for search employee based on keyword like(name, email, address);
	@ProcessAction(name = "searchEmployee")
	public void searchEmploee(ActionRequest request, ActionResponse response) {

		String keywords = ParamUtil.getString(request, "employeeName");

		if (keywords.equals("")) {
			response.setRenderParameter("mvcPath", "/view.jsp");
		} else {

			List<Employee> employees = EmployeeLocalServiceUtil.findByKeywords(keywords);
			request.setAttribute("employees", employees);
			response.setRenderParameter("mvcPath", "/view.jsp");
		}
	}

	private Folder createFolderIfNotExists(long groupId, long parentFolderId, String folderName,
			ServiceContext serviceContext) throws Exception {
		try {
			return DLAppServiceUtil.getFolder(groupId, parentFolderId, folderName);
		} catch (NoSuchFolderException e) {
			return DLAppServiceUtil.addFolder(folderName, groupId, parentFolderId, folderName,
					"Auto-created folder for bus images", serviceContext);
		}
	}

	private String uploadImage(long groupId, long folderId, String base64Image, String fileName,
			ServiceContext serviceContext) throws Exception {
		
		byte[] decodedBytes = Base64.getDecoder().decode(base64Image);

		InputStream inputStream = new ByteArrayInputStream(decodedBytes);

		FileEntry fileEntry = DLAppServiceUtil.addFileEntry(
				fileName, groupId,
				folderId,
				fileName,
				ContentTypes.IMAGE_JPEG,
				fileName,
				"",
				"",
				fileName, inputStream,
				decodedBytes.length,
				null, null, null, serviceContext);

		return "/documents/" + fileEntry.getRepositoryId() + "/" + fileEntry.getFolderId() + "/" + fileEntry.getTitle();
	}

}