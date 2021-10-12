package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Dtos.EmpleadoIndexDto;
import com.sedback.SEDBack.Dtos.EmpleadoVerDto;
import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Dtos.NuevoEmpleadoDto;
import com.sedback.SEDBack.Mappers.EmpleadoVerDtoMapper;
import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Modelo.Usuario;
import com.sedback.SEDBack.Persistencia.EmpleadoRepositorio;
import com.sedback.SEDBack.Persistencia.UsuarioRepositorio;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EmpleadoServicio {
    @Autowired
    private EmpleadoRepositorio repositorioEmpleado;
    
    @Autowired
    private UsuarioRepositorio repositorioUsuario;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    public String guardar(NuevoEmpleadoDto nuevoEmpleado){
        Empleado empleadoGuardado = repositorioEmpleado.save(nuevoEmpleado.getEmpleado());
        Usuario usuario = nuevoEmpleado.getUsuario();
        usuario.setHabilitado(true);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setEmpleado(empleadoGuardado);
        repositorioUsuario.save(usuario);
        return "Empleado Registrado Correctamente";
    }
    
    public ResponseEntity<EmpleadoVerDto> getEmpleadoById(Integer id){
        return ResponseEntity.ok().body(EmpleadoVerDtoMapper.INSTANCE.empleadotoEmpleadoVerDto(repositorioEmpleado.getEmpleadoById(id)));
    }
    
    public ResponseEntity<Page<EmpleadoIndexDto>> getEmpleados(String filtro,Pageable page){
        return ResponseEntity.ok().body(repositorioEmpleado.getEmpleados(filtro, page));
    }
    
    public ResponseEntity<List<Empleado>> getEmpleadosEvaluadores(){
        return ResponseEntity.ok().body(repositorioEmpleado.getEmpleadosEvaluadores());
    }
    
    public boolean existeNumeroDni(String dni){
        return repositorioEmpleado.existeNumeroDni(dni).isEmpty();
    }
    
    public boolean existeEmail(String email){
        return repositorioEmpleado.existeEmail(email).isEmpty();
    }
    
    public boolean existeLegajo(String legajo){
        return repositorioEmpleado.existeLegajo(legajo).isEmpty();
    }
}
