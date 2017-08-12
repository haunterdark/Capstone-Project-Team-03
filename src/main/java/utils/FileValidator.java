package utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import entities.FileBucker;

@Component
public class FileValidator implements Validator {
	
	public boolean supports(Class<?> clazz) {
		return FileBucker.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		FileBucker file = (FileBucker) obj;
		
		if(file.getFile()!=null){
			if (file.getFile().getSize() == 0) {
				errors.rejectValue("file", "missing.file");
			}
		}
	}
}