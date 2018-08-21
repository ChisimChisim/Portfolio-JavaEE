package util;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;


public class BinFileUtil {
	static final Logger log = Logger.getLogger(BinFileUtil.class.getName());

	
	public static byte[] getBinary(String fpath){
    	String	filePath	=	getRealPath(fpath);		
		Path	path		=	Paths.get(filePath);	
		byte[]	binaryData = null;
		try {
			binaryData	= Files.readAllBytes(path);		
		} catch (IOException ex) {
			log.severe("◆"+ex.toString());
		}
		return	binaryData;
    }
	
	public	static	void	putBinary(byte[] binaryData, String outFile){
    	String	filePath	=	getRealPath(outFile);		
		Path	path		=	Paths.get(filePath);		
		try {
			Files.write(path, binaryData);					
		} catch (IOException ex) {
			log.severe("◆"+ex.toString());
		}
	}
	
	public	static String getRealPath(String path){
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		return  ctx.getRealPath(path);
	}
}

