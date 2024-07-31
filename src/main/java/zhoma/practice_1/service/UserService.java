package zhoma.practice_1.service;


import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zhoma.practice_1.DTO.RegisterDto;
import zhoma.practice_1.model.Role;
import zhoma.practice_1.model.UserEntity;
import zhoma.practice_1.repository.RoleRepository;
import zhoma.practice_1.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

        private final  UserRepository repository;
        private final RoleRepository roleRepository;
        private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository repository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean existingByUsername(String username) {
return repository.existsByUsername(username);

    }
    @Transactional
    public void addUser(UserEntity userEntity) {
        repository.save(userEntity);
    }
    public UserEntity mapRegisterDtoToUser(RegisterDto registerDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Role role = roleRepository.findByName("USER").get();
        userEntity.setRoles(Collections.singletonList(role));
        return userEntity;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(user.getUsername(), user.getPassword(),mapRolesToAuthorities(user.getRoles()));

    }


    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
