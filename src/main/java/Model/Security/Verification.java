package Model.Security;


public class Verification {
    private boolean verified;
    private int admin;
    private int idUser;

    public Verification(){}

    public Verification (boolean verified, int admin, int idUser){
        this.verified=verified;
        this.admin=admin;
        this.idUser=idUser;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
