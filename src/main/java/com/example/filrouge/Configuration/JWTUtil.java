package com.example.filrouge.Configuration;

public class JWTUtil {
    public static final long EXPIRE_ACCESS_TOKEN = 5*60*1000;
    public static final String ISSUER = "SpringBootApp";
    public static final  String SECRET_KEY = "Ikram1546548233";
    public static final String BEARER_PRIFIX="Bearer ";
    public static final  long EXPIRE_REFRESH_TOKEN = 120*60*1000;
    public static final String AUTH_HEADER = "Authorization";
}
