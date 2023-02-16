package models;

import dao.MedicamentDao;
import dao.UserDao;

public class OrderData {
    private final UserDao userDao = new UserDao();
    private final MedicamentDao medicamentDao = new MedicamentDao();

    private Long id;
    private String adress;
    private Long user;
    private Long medicament;

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public User getUser() {
        return userDao.getUser(user);
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Medicament getMedicament() {
        return medicamentDao.getMedicament(medicament);
    }

    public void setMedicament(Long medicament) {
        this.medicament = medicament;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
