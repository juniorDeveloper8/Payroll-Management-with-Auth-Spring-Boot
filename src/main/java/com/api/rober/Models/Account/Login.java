package com.api.rober.Models.Account;

import com.api.rober.DTO.Account.DatosActualizarPassword;
import com.api.rober.DTO.Account.DatosRegistroAccount;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@Table(name = "login")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Login implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String password;

    @Column(columnDefinition = "TINYINT(1)", nullable = false)
    private boolean Activo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Activo;
    }

    public Login(DatosRegistroAccount datosRegistroAccount) {
        this.Activo = true;
        this.username = datosRegistroAccount.username();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(datosRegistroAccount.password());
    }

    // recuperar cuenta
    public ResponseEntity<String> updatePassword(DatosActualizarPassword datosActualizarPassword) {

        // Verificar que el username no esté en blanco
        if (username == null || username.isBlank()) {
            return ResponseEntity.badRequest().body("Username not found");
        }

        // Encriptamos el nuevo password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(datosActualizarPassword.password());

        // Responder con éxito
        return ResponseEntity.ok("Password updated successfully");
    }
}
