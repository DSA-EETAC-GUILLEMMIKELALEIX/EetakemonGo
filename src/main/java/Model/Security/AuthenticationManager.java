package Model.Security;

import Model.Exceptions.UnauthorizedException;
import Model.Exceptions.NotSuchPrivilegeException;
import Model.User.User;
import io.jsonwebtoken.*;

import javax.ws.rs.core.HttpHeaders;
import java.util.Date;


public class AuthenticationManager {

    private static final String SECRET="023u902ufh3h983";

    public String getToken(User user) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
       /* byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());*/

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder();
        builder.setIssuedAt(now);
        builder.setSubject(Integer.toString(user.getId()));
        builder.claim("Admin", user.getAdmin());
        builder.setIssuer("DSA_Application");
        builder.signWith(signatureAlgorithm, SECRET.getBytes());

        long expMillis = nowMillis + 7200000;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);
        //Builds the JWT and serializes it to a compact, URL-safe string
        //return builder.compact();

        return builder.compact();
    }

    public Verification verify(HttpHeaders headers, Verification v) throws UnauthorizedException {
        Verification verification = new Verification();
        String temp = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).toString();
        String token=temp.substring("[Bearer".length(), temp.length()-1).trim();;
        verifyToken(token,v);
        if(!v.isVerified()){
            throw new UnauthorizedException("Unauthorized: user is not authorized");
        }

        return verification;
    }

    public void verifyAdmin(Verification v) throws NotSuchPrivilegeException{
        if(v.getAdmin()!=1){
            throw new NotSuchPrivilegeException("Forbidden: User has not admin privileges");
        }
    }

    public void verifyCorrectUser(Verification v, int id) throws NotSuchPrivilegeException{
        if(v.getAdmin()!=1 && v.getIdUser()!=id){
            throw new NotSuchPrivilegeException("Forbidden: User has not privileges");
        }
    }


    /*PRIVATE METHODS*/
    public void verifyToken(String token, Verification v){
        try {
            int admin;
            int idUser;
            Claims claims=Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token).getBody();
            admin=(Integer)claims.get("Admin");
            idUser=(Integer.parseInt(claims.getSubject()));
            v.setVerified(true);
            v.setAdmin(admin);
            v.setIdUser(idUser);
        } catch (SignatureException | UnsupportedJwtException | ExpiredJwtException | MalformedJwtException
                | IllegalArgumentException ex) {

            v.setVerified(false);
            v.setAdmin(0);
            v.setIdUser(0);
            System.out.println("expired token");
        }
    }

}
