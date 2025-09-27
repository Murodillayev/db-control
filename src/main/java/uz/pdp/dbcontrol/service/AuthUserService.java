package uz.pdp.dbcontrol.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.dbcontrol.config.jwt.JwtUtil;
import uz.pdp.dbcontrol.criteria.AuthUserCriteria;
import uz.pdp.dbcontrol.mapper.AuthUserMapper;
import uz.pdp.dbcontrol.model.dto.*;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.repository.AuthUserRepository;
import uz.pdp.dbcontrol.service.base.AbstractService;
import uz.pdp.dbcontrol.service.base.CrudService;
import uz.pdp.dbcontrol.validator.AuthUserValidator;

import java.util.List;

@Service
public class AuthUserService
        extends AbstractService<AuthUserRepository, AuthUserMapper, AuthUserValidator>
        implements CrudService<AuthUserDto, AuthUserSaveDto, AuthUserSaveDto, AuthUserCriteria, String> {

    private final AuthUserRepository repository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final NotifyService notifyService;

    public AuthUserService(AuthUserRepository repository, AuthUserMapper mapper, AuthUserValidator validator, AuthUserRepository repository1, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, NotifyService notifyService) {
        super(repository, mapper, validator);
        this.repository = repository1;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.notifyService = notifyService;
    }


    public LoginResponse login(LoginRequest request) {

        AuthUser authUser = repository.findByUsernameAndDeletedFalse(request.getUsername())
                .orElseThrow(
                        () -> new BadCredentialsException("Bad credentials")
                );

        // match password
        if (!passwordEncoder.matches(request.getPassword(), authUser.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        TokenDto accessToken = jwtUtil.generateAccessToken(authUser);
        TokenDto refreshToken = jwtUtil.generateRefreshToken(authUser);
        return LoginResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiration(accessToken.getExpiry())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiration(refreshToken.getExpiry())
                .build();

    }

    public LoginResponse refreshToken(String refreshToken) {
        Claims claims = jwtUtil.validateTokenAndExtract("Bearer " + refreshToken);
        String username = claims.getSubject();
        AuthUser authUser = repository.findByUsernameAndDeletedFalse(username).orElseThrow(
                () -> new BadCredentialsException("Bad credentials")
        );
        TokenDto accessToken = jwtUtil.generateAccessToken(authUser);

        return LoginResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiration(accessToken.getExpiry())
                .refreshToken(refreshToken)
                .refreshTokenExpiration(claims.getExpiration())
                .build();
    }

    @Override
    public AuthUserDto create(AuthUserSaveDto dto) {
        AuthUser authUser = mapper.fromDto(dto);
        AuthUser save = repository.save(authUser);
        notifyService.sendUserCredentials(save);
        // send notify username and password  to user
        return mapper.toDto(save);
    }

    @Override
    public AuthUserDto get(String id) {
        return null;
    }

    @Override
    public List<AuthUserDto> getAll(AuthUserCriteria criteria) {
        return List.of();
    }

    @Override
    public AuthUserDto update(AuthUserSaveDto dto, String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
