package com.pretask.stockapi.service;

import com.pretask.stockapi.dto.UserDto;
import com.pretask.stockapi.entity.StockList;
import com.pretask.stockapi.entity.User;
import com.pretask.stockapi.model.StockInfo;
import com.pretask.stockapi.repository.MemberRepository;
import com.pretask.stockapi.repository.StockListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yahoofinance.Stock;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class MemberService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StockListRepository stockListRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StockService stockService;

    @Transactional
    public String signUp(UserDto userDto){
        if (memberRepository.findByUsername(userDto.getUsername()).isPresent()){
            return "equalName";
        }
        else if(memberRepository.findByEmail(userDto.getEmail()).isPresent()){
            return "equalEmail";
        }
        User user= new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("ROLE_USER");
        user.setCreatedTime(LocalDateTime.now());
        user.setUpdatedTime(LocalDateTime.now());
        memberRepository.save(user);
        return "success";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> member=memberRepository.findByUsername(username);
        if(!member.isPresent()){
            return null;
        }
        List<GrantedAuthority> authorityList=new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(member.get().getRole()));

        return new org.springframework.security.core.userdetails.User(username,member.get().getPassword(),authorityList);
    }

    public UserDto returnMemberInfo(String username){
        Optional<User> user=memberRepository.findByUsername(username);
        if(user.isPresent()){
            UserDto userDto=new UserDto().builder().username(user.get().getUsername()).email(user.get().getEmail()).build();
            return userDto;
        }
        return null;
    }
    public Collection<StockList> returnStockList(String username){
        Optional<User> user=memberRepository.findByUsername(username);
        if(user.isPresent()){
            return user.get().getStockList();
        }
        return null;
    }

    public void addFastStockList(String username, String code) throws IOException {
        Optional<User> user=memberRepository.findByUsername(username);
        if(user.isPresent()){
            Map<String ,Stock> stocks=stockService.getStockInfo(code);
            if(stocks==null){
                return;
            }
            ArrayList<String> list= new ArrayList<>();
            for(Stock value : stocks.values()){
                Stock stock=value;
                StockList stockList=new StockList();
                stockList.setStockName(stock.getSymbol());
                stockList.setUserId(user.get().getId());
                stockListRepository.save(stockList);
            }
        }
        return;
    }
}
