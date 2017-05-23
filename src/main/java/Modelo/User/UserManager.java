package Modelo.User;

import Modelo.TrippleDes;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;


public class UserManager {
    private TrippleDes td;
    private final static Logger logger = Logger.getLogger(UserManager.class);//

    public UserManager(){
        try{
            td=new TrippleDes();
        }
        catch (Exception e){

        }
    }
    public User login(User user){
        Boolean a;
        String e,c;
        e=user.getEmail();
        c=user.getContrasena();
        String encriptedpass=td.encrypt(c);
        a=user.login(e,encriptedpass);
        if(a) {
            user.selectUserByMail(user.getEmail());
        }else{user=null;}
        return user;
    }

    /*
    Funcion registrarse
    code 0 = registrado
    code 1 = eusuario ya utilizado
    code 2 = error al registrarse
     */
    public int register(User user){
        boolean a=false,b=true;
        int code=2;
        a=checkNullFields(user);
        if(!a) {
            b = user.checkUserExistent(user.getEmail());
            if (!b) {
                user.setAdmin(0);
                String encriptedpass = td.encrypt(user.getContrasena());
                user.setContrasena(encriptedpass);
                user.insertUser();
                code=0;
            }
            else{
                code=1;
            }
        }

        return code;
    }

    public boolean updateUser(int id, User user){
        Boolean a=false;
        String encriptedpass;
        try {
            if(user.getNombre()==null)
                throw new NullPointerException();
            encriptedpass = td.encrypt(user.getContrasena());
            user.setId(id);
            user.setContrasena(encriptedpass);
            a = user.updatetUser();
        }catch(Exception e){
            logger.info("INFO: error al modificar usuario");
            a=false;
        }
        return a;
    }

    public User getUserById(int id){
        User u= new User();
        u.selectUserById(id);
        return u;
    }

    public User getUserByEmail(String email){
        User u= new User();
        u.selectUserByMail(email);
        return u;
    }

    public User deleteUser(int id){
        User u = new User();
        u.selectUserById(id);
        u.deleteUser();

        return u;
    }

    public List listAllUsers(){
        List<User> list;
        list = new User().findAllUsers();

        return list;
    }

    public boolean resetPassword(User u) {
        boolean bool;
        u.selectUserByMail(u.getEmail());
        final String username = u.getEmail();
        final String password = td.decrypt(u.getContrasena());

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(u.getEmail()));
            message.setSubject("Recuperar contraseña");
            message.setText("Hola, " + u.getNombre()
                    + "\n\n Tu contraseña es: " + password);

            Transport transport = session.getTransport("smtp");

            // Enter your correct gmail UserID and Password
            // if you have 2FA enabled then provide App Specific Password

            transport.connect("smtp.gmail.com", "DSAproyecto@gmail.com", "aleixguillemmikel");
            message.setFrom(new InternetAddress("DSAproyecto@gmail.com"));
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            System.out.println("Done");
            bool = true;

        } catch (MessagingException e) {
            bool = false;
            throw new RuntimeException(e);
        }
        return bool;

    }

    private boolean checkNullFields(User u){
        if(u.getNombre().equals("")||u.getContrasena().equals("")||u.getEmail().equals(""))
            return true;

        return false;
    }

}
