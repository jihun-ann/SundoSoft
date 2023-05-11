package business.com;

import common.file.FileDirectory;

/**
 * 다운로드 양식 파일 정보 클래스
 * 
 * @class   : CommFormFile
 * @author  : LSH
 * @since   : 2021.11.30
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 */
public enum CommFormFile {
	
	SAMPLE("SAMPLE", "양식파일샘플.xlsx", "sample.xlsx")
	;

	private String formType;
	private String fileName;
	private String saveName;
	
	private CommFormFile(String formType, String fileName, String saveName) {
		this.formType = formType;
		this.fileName = fileName;
		this.saveName = saveName;
	}
	
	public static CommFormFile get(String formType) {
		for (CommFormFile cf : values()) {
			if (formType.equals(cf.getFormType()))
				return cf;
		}
		return null;
	}

	public String getFormType() {
		return formType;
	}

	public String getSaveName() {
		return saveName;
	}

	public String getFileName() {
		return fileName;
	}
	public String getFullPath() {
		return FileDirectory.FORMFILE.getRealPath();
	}
	
	public String getFullName() {
		return FileDirectory.FORMFILE.getRealName(saveName);
	}
	
}
