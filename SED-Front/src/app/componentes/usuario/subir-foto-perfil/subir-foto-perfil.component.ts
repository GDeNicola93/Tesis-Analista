import { Component, OnInit, ViewChild } from '@angular/core';
import { CropperComponent } from 'angular-cropperjs';
import { UploadImagenPerfilService } from 'src/app/servicios/upload-imagen-perfil.service';

@Component({
  selector: 'app-subir-foto-perfil',
  templateUrl: './subir-foto-perfil.component.html',
  styleUrls: ['./subir-foto-perfil.component.css']
})
export class SubirFotoPerfilComponent implements OnInit {
  @ViewChild('angularCropper') angularCropper : CropperComponent;
  imageURL : string;
  croppedresult : string;
  procesando = false;
  imageBlob : Blob;
  
  constructor(private uploadImagenPerfilServicio : UploadImagenPerfilService) { }

  ngOnInit(): void {
  }

  onSelectFile(event : any){
    this.croppedresult = "";
    this.procesando = false;
    if(event.target.files && event.target.files[0]){
      const reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      reader.onload = () => {
        this.imageURL = reader.result as string;
      }
    }
  }

  getCroppedImage(){
    //this.croppedresult = this.angularCropper.cropper.getCroppedCanvas().toDataURL();
    this.procesando = true;
    this.croppedresult = null;
    this.angularCropper.cropper.getCroppedCanvas().toBlob((blob) => {
      this.imageBlob = blob;
      const reader = new FileReader();
      reader.readAsDataURL(blob);
      reader.onload = () => {
        this.croppedresult = reader.result as string;
        this.procesando = false;
      };
    },'image/png',0.90); //Calidad de imagen en un 90% (Osea se reduce un 10% la calidad) y formato png
  }

  subirImagen() : void{
    const body = new FormData;
    body.append('file',this.imageBlob,'sdfsdsd.png');
    let nombreArchivo : string;

    this.uploadImagenPerfilServicio.subirImagenPerfil(body).subscribe(data =>{
      nombreArchivo = data.mensaje;
      alert(nombreArchivo);
    },
      (err : any)=>{
        alert(err.error.mensaje);
      }
    );

  }

}
