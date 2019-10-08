package Clases.Models;

import java.time.LocalDate;

public class Provider {
    private int id;
    private String name;
    private String telephone;
    private String contact;
    private String providerCode;
    private String service;
    private int rating;
    private boolean critical;
    private boolean approved;
    private LocalDate approvalDate;
    private LocalDate revalidationDate;

    public Provider(int id, String name, String telephone, String contact, String providerCode, String service, int rating, boolean critical, boolean approved, LocalDate approvalDate, LocalDate revalidationDate) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.contact = contact;
        this.providerCode = providerCode;
        this.service = service;
        this.rating = rating;
        this.critical = critical;
        this.approved = approved;
        this.approvalDate = approvalDate;
        this.revalidationDate = revalidationDate;
    }

    public Provider(String name, String telephone, String contact, String providerCode, String service, int rating, boolean critical, boolean approved, LocalDate approvalDate, LocalDate revalidationDate) {
        this.name = name;
        this.telephone = telephone;
        this.contact = contact;
        this.providerCode = providerCode;
        this.service = service;
        this.rating = rating;
        this.critical = critical;
        this.approved = approved;
        this.approvalDate = approvalDate;
        this.revalidationDate = revalidationDate;
    }

    public Provider(String name, String telephone, String contact, String providerCode) {
        this.name = name;
        this.telephone = telephone;
        this.contact = contact;
        this.providerCode = providerCode;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public LocalDate getRevalidationDate() {
        return revalidationDate;
    }

    public void setRevalidationDate(LocalDate revalidationDate) {
        this.revalidationDate = revalidationDate;
    }

}
