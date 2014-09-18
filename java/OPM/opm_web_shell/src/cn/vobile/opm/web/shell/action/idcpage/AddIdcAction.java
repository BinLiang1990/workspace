/*
 * @(#)AddIdcAction.java    1.0 2012-8-13
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.shell.action.idcpage;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.vobile.opm.web.shell.service.IdcService;
import cn.vobile.opm.web.shell.service.impl.IdcServiceImple;

import com.opensymphony.xwork2.ActionSupport;

/**
 * AddIdcAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-8-13
 * @since 1.0
 */
@SuppressWarnings("serial")
public class AddIdcAction extends ActionSupport {
	private String idc_name;
	private String idc_local;
	private String idc_description;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		IdcService idcService = new IdcServiceImple();
		if (idc_name == null || idc_name.equals("") || idc_local == null
				|| idc_local.equals("")) {
		} else {
			if (idc_description == null) {
				idc_description = "";
			}
			if (uploadFileName == null || uploadFileName.equals("")) {
			} else {
				int idc_id = idcService.addAnIdc(idc_name, idc_local);

				String image_name = idc_id + "."
						+ uploadFileName.split("\\.")[1];
				String imageDir = ServletActionContext.getServletContext()
						.getRealPath("/");
				File target = new File(imageDir + "/idc_image", image_name);
				FileUtils.copyFile(upload, target);

				idcService.updateIdc(idc_id, image_name, idc_description);
			}
		}
		return super.execute();
	}

	/**
	 * @return the idc_name
	 */
	public String getIdc_name() {
		return idc_name;
	}

	/**
	 * @param idc_name
	 *            the idc_name to set
	 */
	public void setIdc_name(String idc_name) {
		this.idc_name = idc_name;
	}

	/**
	 * @return the idc_local
	 */
	public String getIdc_local() {
		return idc_local;
	}

	/**
	 * @param idc_local
	 *            the idc_local to set
	 */
	public void setIdc_local(String idc_local) {
		this.idc_local = idc_local;
	}

	/**
	 * @return the idc_description
	 */
	public String getIdc_description() {
		return idc_description;
	}

	/**
	 * @param idc_description
	 *            the idc_description to set
	 */
	public void setIdc_description(String idc_description) {
		this.idc_description = idc_description;
	}

	/**
	 * @return the upload
	 */
	public File getUpload() {
		return upload;
	}

	/**
	 * @param upload
	 *            the upload to set
	 */
	public void setUpload(File upload) {
		this.upload = upload;
	}

	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName
	 *            the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * @return the uploadContentType
	 */
	public String getUploadContentType() {
		return uploadContentType;
	}

	/**
	 * @param uploadContentType
	 *            the uploadContentType to set
	 */
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
}
