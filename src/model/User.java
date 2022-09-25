package model;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "users_all")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

   //  unique = true, was in hooks
    @Column(name = "name",  updatable = false)
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name="isBanned")
    private boolean ban;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public User() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public User(long id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public User(String name, String password) {
        this.setId(-1);
        this.setName(name);
        this.setPassword(password);
        this.ban=false;
    }


    @SuppressWarnings("UnusedDeclaration")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void makeBan(){
        this.ban = true;
    }

    public boolean isBanned(){
        return this.ban;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", name='" + name +
                ", password='" + password + '\'' +
                '}';
    }

}