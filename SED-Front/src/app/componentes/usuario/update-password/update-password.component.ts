import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CambioPassword } from 'src/app/HttpMensajes/cambio-password';
import { UsuarioService } from 'src/app/servicios/usuario.service';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']
})
export class UpdatePasswordComponent implements OnInit {
  mensaje = '';
  guardado = false;
  error = false;
  updatePasswordForm : FormGroup;

  constructor(private usuarioServicio:UsuarioService,private fb : FormBuilder) { }

  ngOnInit(): void {
    this.validaciones();
  }

  validaciones() : void{
    this.updatePasswordForm = this.fb.group({
      passwordActual : ['',Validators.required],
      passwordNew1 : ['',Validators.required],
      passwordNew2 : ['',Validators.required],
    },{
      validator: this.ConfirmedValidator('passwordNew1', 'passwordNew2')
    });
  }

  guardar() : void {
    this.mensaje = '';
    this.guardado = false;
    this.error = false;
    this.usuarioServicio.actualizarPassword(this.updatePasswordForm.value).subscribe(data => {
      this.mensaje = data.mensaje;
      this.guardado = true;
      this.error = false;
      this.updatePasswordForm.reset()
    },
      (err: any) => {
        this.mensaje = err.error.mensaje;
        this.guardado = false;
        this.error = true;
      }
    );
  }

  ConfirmedValidator(controlName: string, matchingControlName: string){
    return (formGroup: FormGroup) => {
        const control = formGroup.controls[controlName];
        const matchingControl = formGroup.controls[matchingControlName];
        if (matchingControl.errors && !matchingControl.errors.confirmedValidator) {
            return;
        }
        if (control.value !== matchingControl.value) {
            matchingControl.setErrors({ confirmedValidator: true });
        } else {
            matchingControl.setErrors(null);
        }
    }
  }

}
