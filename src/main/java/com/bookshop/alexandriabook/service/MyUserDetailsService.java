package com.bookshop.alexandriabook.service;

import com.bookshop.alexandriabook.entidade.Cliente;
import com.bookshop.alexandriabook.entidade.MyUserDetailsPrincipal;
import com.bookshop.alexandriabook.repository.ClienteRepository;
import com.bookshop.alexandriabook.repository.MyUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MyUserRepo myUserRepo;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
        Cliente user = clienteRepository.findClienteByUsername(username);

        if(user == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }

        return new MyUserDetailsPrincipal(user);
    }
}
