package org.springframework.mvc.extensions.ajax;

import org.springframework.web.context.request.WebRequest;

public class AjaxUtils {

	public static boolean isAjaxRequest(WebRequest webRequest) {
		String requestedWith = webRequest.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

	public static boolean isAjaxUploadRequest(WebRequest webRequest) {
		return webRequest.getParameter("ajaxUpload") != null;
	}
	
	private AjaxUtils() {}

}
//create a new method in the AjaxUtils class that checks for an AJAX request that is also a file upload request.

