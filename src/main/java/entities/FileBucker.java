package entities;

import org.springframework.web.multipart.MultipartFile;

public class FileBucker {
	 
    MultipartFile file;
     
    public MultipartFile getFile() {
        return file;
    }
 
    public void seFile(MultipartFile file) {
        this.file = file;
    }
}
