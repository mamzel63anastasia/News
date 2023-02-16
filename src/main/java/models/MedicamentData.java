package models;

import dao.ProducerDao;
import dao.SubstanceDao;

public class MedicamentData {
    private final ProducerDao producerDao = new ProducerDao();
    private final SubstanceDao substanceDao = new SubstanceDao();

    private String name;
    private int dose;
    private int number;
    private Long substance;
    private Long producer;
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Substance getSubstance() {
        return substanceDao.getSubstance(substance);
    }

    public void setSubstance(Long substance) {
        this.substance = substance;
    }

    public Producer getProducer() {
        return producerDao.getProducer(producer);
    }

    public void setProducer(Long producer) {
        this.producer = producer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
