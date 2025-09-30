package uz.pdp.dbcontrol.service;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.config.jwt.JwtUtil;
import uz.pdp.dbcontrol.criteria.BaseCriteria;
import uz.pdp.dbcontrol.mapper.AuthUserMapper;
import uz.pdp.dbcontrol.model.dto.*;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.repository.AuthUserRepository;
import uz.pdp.dbcontrol.service.base.AbstractService;
import uz.pdp.dbcontrol.service.base.CrudService;
import uz.pdp.dbcontrol.validator.AuthUserValidator;

import java.util.Date;
import java.util.List;

@Service
public class AuthUserService
        extends AbstractService<AuthUserRepository, AuthUserMapper, AuthUserValidator>
        implements CrudService<AuthUserDto, AuthUserSaveDto, AuthUserSaveDto, BaseCriteria, String> {

    private final AuthUserRepository repository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final NotifyService notifyService;

    public AuthUserService(AuthUserRepository repository, AuthUserMapper mapper, AuthUserValidator validator, AuthUserRepository repository1, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, @Qualifier("email-notify") NotifyService notifyService) {
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
                .token(accessToken.getToken())
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
                .token(accessToken.getToken())
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
        return mapper.toDto(save);
    }

    @Override
    public AuthUserDto get(String id) {
        AuthUser authUser = validator.existAndGet(id);
        return mapper.toDto(authUser);
    }

    @Override
    public DataResponse<List<AuthUserDto>> getAll(BaseCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by("createdAt").descending());
        Page<AuthUser> page = repository.findAllBySearch(criteria.getSearch(), pageable);
        return new DataResponse<>(mapper.toDto(page.getContent()), page.getTotalElements(), page.getTotalPages());
    }

    @Override
    public AuthUserDto update(AuthUserSaveDto dto, String id) {
        AuthUser authUser = validator.existAndGet(id);
        mapper.fromDto(authUser, dto);
        AuthUser save = repository.save(authUser);
        return mapper.toDto(save);
    }

    @Override
    public void delete(String id) {
        AuthUser authUser = validator.existAndGet(id);
        authUser.setDeleted(true);
        repository.save(authUser);
    }
}
