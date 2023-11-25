package br.com.vitalsupport.services;

import br.com.vitalsupport.models.Admin;
import br.com.vitalsupport.repositories.AdminRepository;

import java.util.List;

public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void create(Admin admin) throws Exception {
        this.adminRepository.save(admin);
    }

    public Admin getById(long id) throws Exception {
        return this.adminRepository.findOneById(id);
    }

    public void update(Long id, Admin admin) throws Exception {
        admin.setId(id);
        this.adminRepository.update(admin);
    }

    public void delete(Admin admin) throws Exception {
        this.adminRepository.delete(admin);
    }

    public List<Admin> getAllAdmins(int page, int pageSize) throws Exception {
        return this.adminRepository.findAll(page, pageSize);
    }
}
