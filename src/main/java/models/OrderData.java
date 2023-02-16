package models;

import dao.MedicamentDao;
import dao.UserDao;

import java.util.ArrayList;
import java.util.List;

public class OrderData {
    private final UserDao userDao = new UserDao();
    private final MedicamentDao medicamentDao = new MedicamentDao();

    private Long id;
    private String adress;
    private Long user;
    private List<Long> medicaments;

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

    public List<Medicament> getMedicaments() {
        List<Medicament> list = new ArrayList<>();
        for (Long id : medicaments) {
            list.add(medicamentDao.getMedicament(id));
        }
        return list;
    }

    public void setMedicaments(List<Long> medicaments) {
        this.medicaments = medicaments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
