package com.blackcode.springbootv3simpleapidbh2.company.impl;

import com.blackcode.springbootv3simpleapidbh2.company.Company;
import com.blackcode.springbootv3simpleapidbh2.company.CompanyRepository;
import com.blackcode.springbootv3simpleapidbh2.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setName(company.getName());
            companyToUpdate.setJobs(company.getJobs());
            companyRepository.save(companyToUpdate);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public void craeteCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if (companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }else{
            return false;
        }


    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);

    }
}
