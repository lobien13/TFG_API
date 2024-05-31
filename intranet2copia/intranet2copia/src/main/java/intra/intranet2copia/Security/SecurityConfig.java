package intra.intranet2copia.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(request -> {
                            String path = request.getServletPath();
                            HttpMethod method = HttpMethod.valueOf(request.getMethod());
                            return (method.equals(HttpMethod.GET) && path.startsWith("/api/usuarios/usuarios")) ||
                                    (method.equals(HttpMethod.POST) && path.startsWith("/api/usuarios/añadir")) ||
                                    (method.equals(HttpMethod.DELETE) && path.matches("/api/usuarios/eliminar\\d+")) ||
                                    (method.equals(HttpMethod.GET) && path.startsWith("/api/incidencias/incidencias")) ||
                                    (method.equals(HttpMethod.POST) && path.startsWith("/api/incidencias/incidencias/añadir")) ||
                                    (method.equals(HttpMethod.DELETE) && path.matches("/api/incidencias/eliminar\\d+")) ||
                                    (method.equals(HttpMethod.GET) && path.startsWith("/api/sistemasArduino/sistemasArduino")) ||
                                    (method.equals(HttpMethod.POST) && path.startsWith("/api/sistemasArduino/sistema/añadir")) ||
                                    (method.equals(HttpMethod.DELETE) && path.matches("/api/sistemasArduino/\\d+")) ||
                                    (method.equals(HttpMethod.POST) && path.matches("/api/sistemasArduino/asociar\\d+\\d+")) ||
                                    (method.equals(HttpMethod.POST) && path.matches("/api/v1/sistemasArduino/\\d+/asociar-incidencia")) ||
                                    (method.equals(HttpMethod.PUT) && path.matches("/api/v1/usuarios/\\d+")) ||
                                    (method.equals(HttpMethod.PUT) && path.matches("/api/v1/incidencias/\\d+")) ||
                                    (method.equals(HttpMethod.PUT) && path.matches("/api/v1/sistemasArduino/\\d+")) ||
                                    (method.equals(HttpMethod.POST) && path.matches("/api/datosCalidadAire/datosCalidadAire/añadir")) ||
                                    (method.equals(HttpMethod.DELETE) && path.matches("/api/v1/datosCalidadAire/datosCalidadAire/\\d+")) ||
                                    (method.equals(HttpMethod.PUT) && path.matches("/api/v1/datosCalidadAire/datosCalidadAire/\\d+")) ||
                                    (method.equals(HttpMethod.POST) && path.matches("/api/datosCalidadAire/")) ||
                                    (method.equals(HttpMethod.DELETE) && path.matches("/api/sistemas/\\d+/incidencias"));
                                            //(method.equals(HttpMethod.POST) && path.matches("/api/datosCalidadAire/datosCalidadAire/añadir"))
                        })
                        .authenticated()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .build();
    }
}