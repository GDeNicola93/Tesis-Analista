package com.sedback.SEDBack.Controlador;

import com.sedback.SEDBack.HttpMensajes.HttpMensaje;
import com.sedback.SEDBack.Logica.UsuarioServicio;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*") //Para poder acceder desde cualquier lado a mi backend
@RestController
public class UploadImagenPerfilControlador {
    @Value("${UPLOAD_DIR}")
    String UPLOAD_DIR;
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @RequestMapping(value = "/imagen_perfil/upload",method = RequestMethod.POST)
    public ResponseEntity<HttpMensaje> guardar_imagen(@RequestParam(value = "file") MultipartFile file,@RequestHeader("authorization") String language) {
        try{
            String fileExtension = getFileExtension(file);
            String filename = getRandomString();

            File targetFile = getTargetFile(fileExtension, filename);

            byte[] bytes = file.getBytes();
            file.transferTo(targetFile);
            
            String token = language.replace("Bearer ", "");
            if(usuarioServicio.setImagenPerfil(usuarioServicio.getDatosUsuarioLogeadoToken(token).getBody(), filename).getStatusCode().isError()){
                return ResponseEntity.badRequest().body(new HttpMensaje("No fue posible subir la imagen. Intente nuevamente!"));
            }
            
            return ResponseEntity.ok().body(new HttpMensaje("La imagen de perfil ha sido subida exitosamente!"));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new HttpMensaje("No fue posible subir la imagen. Intente nuevamente!"));
        }
    }
    
    @RequestMapping(value = "/imagen_perfil/ver/{galleryId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getFile(@PathVariable("galleryId")String galleryId) throws IOException {

	byte[] bFile = Files.readAllBytes(new File(UPLOAD_DIR+galleryId+".png").toPath());

	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.IMAGE_PNG);
	headers.setContentLength(bFile.length);

	ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(bFile, headers, HttpStatus.OK);
	return responseEntity;		
    }
    
    
    
    private String getRandomString() {
        return new Random().nextInt(999999) + "_" + System.currentTimeMillis();
    }

    private File getTargetFile(String fileExtn, String fileName) {
	File targetFile = new File(UPLOAD_DIR + fileName + fileExtn);
	return targetFile;
    }

    private String getFileExtension(MultipartFile inFile) {
	String fileExtention = inFile.getOriginalFilename().substring(inFile.getOriginalFilename().lastIndexOf('.'));
	return fileExtention;
    }
}
